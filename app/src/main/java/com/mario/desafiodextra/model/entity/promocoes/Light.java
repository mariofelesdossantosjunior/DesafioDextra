package com.mario.desafiodextra.model.entity.promocoes;

import com.annimon.stream.Stream;
import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;

import java.util.List;

/**
 * Created by mario on 24/07/17.
 */

public class Light implements Promocao {
    @Override
    public Double calcTotal(List<IngredienteAPI> ingredienteAPIs) {
        //Se o lanche tem alface e não tem bacon, ganha 10% de desconto.
        boolean isAlface = Stream.of(ingredienteAPIs).anyMatch(ingrediente -> ingrediente.getName().contains("Alface"));
        boolean isNotBacon =  Stream.of(ingredienteAPIs).noneMatch(ingrediente -> ingrediente.getName().contains("Bacon"));

        if(isAlface && isNotBacon){
            return Stream.of(ingredienteAPIs).mapToDouble(IngredienteAPI::getPrice).sum() * 0.10;
        }else{
            return 0.0;
        }
    }
}
