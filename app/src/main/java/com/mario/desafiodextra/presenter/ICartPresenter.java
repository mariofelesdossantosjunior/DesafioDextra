package com.mario.desafiodextra.presenter;

import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;

import java.util.List;

/**
 * Created by mario on 24/07/17.
 */

public interface ICartPresenter {
    void addItemCart(Long id);
    void addItemCartWithExtras(Long id, List<Long> extras);
    IngredienteAPI findIngredientByKey(Long key);
    void onStop();
}
