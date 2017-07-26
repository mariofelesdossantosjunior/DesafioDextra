package com.mario.desafiodextra.presenter.impl;

import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;
import com.mario.desafiodextra.model.remote.impl.LanchoneteAPI;
import com.mario.desafiodextra.presenter.ICartPresenter;
import com.mario.desafiodextra.view.IDetailLunch;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mario on 24/07/17.
 */

public class CartPresenter implements ICartPresenter {
    private IDetailLunch mIDetailLunch;
    private LanchoneteAPI mLanchoneteAPI;
    private CompositeSubscription mCompositeSubscription;

    public CartPresenter(IDetailLunch mIDetailLunch) {
        this.mIDetailLunch = mIDetailLunch;
        this.mCompositeSubscription = new CompositeSubscription();
        mLanchoneteAPI = LanchoneteAPI.getInstance();
    }

    @Override
    public void addItemCart(Long id) {
        mCompositeSubscription.add(
                mLanchoneteAPI
                        .getAPI()
                        .sendOrder(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mIDetailLunch::sendSucess,Throwable::printStackTrace)
        );
    }

    @Override
    public void addItemCartWithExtras(Long id, List<Long> extras) {
        mCompositeSubscription.add(
                mLanchoneteAPI
                        .getAPI()
                        .sendOrderWithExtras(id,extras.toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mIDetailLunch::sendSucess,Throwable::printStackTrace)
        );
    }

    @Override
    public IngredienteAPI findIngredientByKey(Long key) {
        return mLanchoneteAPI
                .getAPI()
                .ingredientFindAll()
                .subscribeOn(Schedulers.io())
                .flatMapIterable(ingredienteAPIs -> ingredienteAPIs)
                .filter(ingredient -> ingredient.getId().equals(key))
                .toBlocking()
                .single();
    }

    @Override
    public void onStop() {
        if (!this.mCompositeSubscription.isUnsubscribed()) this.mCompositeSubscription.unsubscribe();
    }
}
