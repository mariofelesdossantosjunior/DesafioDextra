package com.mario.desafiodextra.view;

import com.mario.desafiodextra.model.entity.Lanche;
import com.mario.desafiodextra.model.remote.entityAPI.PedidoAPI;

/**
 * Created by mario on 24/07/17.
 */

public interface IDetailLunch {
    void showDetailLunch(Lanche lunch);
    void sendSucess(PedidoAPI pedidoAPI);
    void sendError();
}
