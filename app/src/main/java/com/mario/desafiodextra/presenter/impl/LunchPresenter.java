package com.mario.desafiodextra.presenter.impl;

import com.mario.desafiodextra.model.entity.builder.LancheBuilder;
import com.mario.desafiodextra.model.remote.impl.LanchoneteAPI;
import com.mario.desafiodextra.presenter.ILunchPresenter;
import com.mario.desafiodextra.view.ILunchFragment;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mario on 23/07/17.
 */

public class LunchPresenter  implements ILunchPresenter{
    private ILunchFragment mILunchFragment;
    private LanchoneteAPI mLanchoneteAPI;
    private CompositeSubscription mCompositeSubscription;

    public LunchPresenter(ILunchFragment mILunchFragment) {
        this.mILunchFragment = mILunchFragment;
        this.mCompositeSubscription = new CompositeSubscription();
        mLanchoneteAPI = LanchoneteAPI.getInstance();
    }

    @Override
    public void loadLunchs() {
        mCompositeSubscription.add(
                mLanchoneteAPI
                        .getAPI()
                        .lunchFindAll()
                        .flatMapIterable(lanches -> lanches)
                        .flatMap(lanche -> mLanchoneteAPI
                                .getAPI()
                                .ingredientFindById(lanche.getId())
                                .subscribeOn(Schedulers.io())
                                .map(ingredientes -> new LancheBuilder()
                                        .withId(lanche.getId())
                                        .withName(lanche.getName())
                                        .withImage(lanche.getImage())
                                        .withIngredients(ingredientes)
                                        .builder()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .toSortedList((l1, l2) -> l1.getName().compareTo(l2.getName()))
                        .subscribe(mILunchFragment::showLunchOnIU,Throwable::printStackTrace)
        );
    }

    @Override
    public void onStop() {
        if (!this.mCompositeSubscription.isUnsubscribed()) this.mCompositeSubscription.unsubscribe();
    }
}
