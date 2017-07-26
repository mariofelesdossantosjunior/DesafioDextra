package com.mario.desafiodextra.view;

import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;

import java.util.List;

/**
 * Created by mario on 25/07/17.
 */

public interface IExtrasDialog {
    void showIngredientsOnIU(List<IngredienteAPI> ingredienteAPIs);
}
