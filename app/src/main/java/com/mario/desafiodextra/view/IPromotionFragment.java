package com.mario.desafiodextra.view;

import com.mario.desafiodextra.model.remote.entityAPI.PromocaoAPI;

import java.util.List;

/**
 * Created by mario on 23/07/17.
 */

public interface IPromotionFragment {
    void showPromotionOnIU(List<PromocaoAPI> promotions);
    void showProgress();
    void hideProgress();
}
