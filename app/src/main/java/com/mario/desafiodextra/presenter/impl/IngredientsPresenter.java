package com.mario.desafiodextra.presenter.impl;

import com.mario.desafiodextra.model.remote.impl.LanchoneteAPI;
import com.mario.desafiodextra.presenter.IIngredientPresenter;
import com.mario.desafiodextra.view.IExtrasDialog;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mario on 25/07/17.
 */

public class IngredientsPresenter implements IIngredientPresenter{
    private IExtrasDialog mIExtrasDialog;
    private LanchoneteAPI mLanchoneteAPI;
    private CompositeSubscription mCompositeSubscription;

    public IngredientsPresenter(IExtrasDialog mIExtrasDialog) {
        this.mIExtrasDialog = mIExtrasDialog;
        this.mCompositeSubscription = new CompositeSubscription();
        this.mLanchoneteAPI = LanchoneteAPI.getInstance();
    }

    @Override
    public void loadIngredients() {
        mCompositeSubscription.add(
                mLanchoneteAPI
                        .getAPI()
                        .ingredientFindAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mIExtrasDialog::showIngredientsOnIU,Throwable::printStackTrace)
        );
    }

    @Override
    public void onStop() {
        if (!this.mCompositeSubscription.isUnsubscribed()) this.mCompositeSubscription.unsubscribe();
    }
}
