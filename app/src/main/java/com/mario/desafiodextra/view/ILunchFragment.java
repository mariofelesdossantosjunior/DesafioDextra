package com.mario.desafiodextra.view;

import com.mario.desafiodextra.model.entity.Lanche;

import java.util.List;

/**
 * Created by mario on 23/07/17.
 */

public interface ILunchFragment {
    void showLunchOnIU(List<Lanche> lunchs);
    void showProgress();
    void hideProgress();
}
