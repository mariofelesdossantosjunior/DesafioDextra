package com.mario.desafiodextra.presenter.impl;

import com.mario.desafiodextra.model.remote.impl.LanchoneteAPI;
import com.mario.desafiodextra.presenter.IPromotionPresenter;
import com.mario.desafiodextra.view.IPromotionFragment;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mario on 24/07/17.
 */

public class PromotionPresenter implements IPromotionPresenter {
    private IPromotionFragment mIPromotionFragment;
    private LanchoneteAPI mLanchoneteAPI;
    private CompositeSubscription mCompositeSubscription;

    public PromotionPresenter(IPromotionFragment mIPromotionFragment) {
        this.mIPromotionFragment = mIPromotionFragment;
        this.mCompositeSubscription = new CompositeSubscription();
        mLanchoneteAPI = LanchoneteAPI.getInstance();
    }

    @Override
    public void loadPromotions() {
        mCompositeSubscription.add(
                mLanchoneteAPI
                        .getAPI()
                        .promotionFindAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mIPromotionFragment::showPromotionOnIU,Throwable::printStackTrace)
        );
    }

    @Override
    public void onStop() {
        if (!this.mCompositeSubscription.isUnsubscribed()) this.mCompositeSubscription.unsubscribe();
    }
}
