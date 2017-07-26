package com.mario.desafiodextra.model.entity.promocoes;

import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;

import java.util.List;

/**
 * Created by mario on 24/07/17.
 */

public interface Promocao {
    Double calcTotal(List<IngredienteAPI> ingredienteAPIs);
}
