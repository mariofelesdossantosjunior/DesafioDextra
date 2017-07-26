package com.mario.desafiodextra.view;

import com.mario.desafiodextra.model.entity.Pedido;

import java.util.List;

/**
 * Created by mario on 24/07/17.
 */

public interface ICartFragment {
    void showOrderOnIU(List<Pedido> orders);
    void showProgress();
    void hideProgress();
}
