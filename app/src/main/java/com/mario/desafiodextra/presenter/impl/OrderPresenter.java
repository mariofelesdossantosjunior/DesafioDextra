package com.mario.desafiodextra.presenter.impl;

import com.annimon.stream.Stream;
import com.mario.desafiodextra.model.entity.Lanche;
import com.mario.desafiodextra.model.entity.builder.LancheBuilder;
import com.mario.desafiodextra.model.entity.builder.PedidoBuilder;
import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;
import com.mario.desafiodextra.model.remote.impl.LanchoneteAPI;
import com.mario.desafiodextra.presenter.IOrderPresenter;
import com.mario.desafiodextra.view.ICartFragment;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mario on 24/07/17.
 */

public class OrderPresenter implements IOrderPresenter {
    private ICartFragment mICartFragment;
    private LanchoneteAPI mLanchoneteAPI;
    private CompositeSubscription mCompositeSubscription;

    public OrderPresenter(ICartFragment mICartFragment) {
        this.mICartFragment = mICartFragment;
        this.mCompositeSubscription = new CompositeSubscription();
        mLanchoneteAPI = LanchoneteAPI.getInstance();
    }

    @Override
    public void loadOrders() {
        mCompositeSubscription.add(
                mLanchoneteAPI
                        .getAPI()
                        .orderFindAll()
                        .flatMapIterable(pedidos -> pedidos)
                        .map(pedido -> new PedidoBuilder()
                                .withId(pedido.getId())
                                .withDate(pedido.getDate())
                                .withSandwich(findLunch(pedido.getId_sandwich(),pedido.getExtras()))
                                .builder()
                        )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .toList()
                        .subscribe(mICartFragment::showOrderOnIU, Throwable::printStackTrace));
    }

    private List<IngredienteAPI> findIngredientsByIds(List<Long> extras) {
        return Stream.of(extras).map(this::findIngreByKey).toList();
    }

    private IngredienteAPI findIngreByKey(Long id) {
        return mLanchoneteAPI
                .getAPI()
                .ingredientFindAll()
                .subscribeOn(Schedulers.io())
                .flatMapIterable(ingres -> ingres)
                .filter(ingre -> ingre.getId().equals(id))
                .toBlocking()
                .single();
    }

    private Lanche findLunch(String id,List<Long> extras) {
        return mLanchoneteAPI
                .getAPI()
                .lunchFindById(id)
                .subscribeOn(Schedulers.io())
                .map(lancheAPI -> new LancheBuilder()
                        .withId(lancheAPI.getId())
                        .withImage(lancheAPI.getImage())
                        .withName(lancheAPI.getName())
                        .withIngredients(findIngredients(lancheAPI.getId()))
                        .withExtras(findIngredientsByIds(extras))
                        .builder())
                .toBlocking()
                .single();
    }

    private List<IngredienteAPI> findIngredients(Long id) {
        return mLanchoneteAPI.getAPI()
                .ingredientFindById(id)
                .subscribeOn(Schedulers.io())
                .flatMapIterable(ingredienteAPIs -> ingredienteAPIs)
                .toList()
                .toBlocking()
                .single();
    }

    @Override
    public void onStop() {
        if (!this.mCompositeSubscription.isUnsubscribed()) this.mCompositeSubscription.unsubscribe();
    }
}
