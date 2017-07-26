package com.mario.desafiodextra.model.entity.promocoes;

import com.annimon.stream.Stream;
import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;

import java.util.List;

/**
 * Created by mario on 24/07/17.
 */

public class MuitaCarne implements Promocao {
    @Override
    public Double calcTotal(List<IngredienteAPI> ingredienteAPIs) {
        //A cada 3 porções de carne o cliente só paga 2. Se o lanche tiver 6 porções, o cliente pagará 4. Assim por diante
        long countCarneDesconto = Stream.of(ingredienteAPIs).filter(ingrediente -> ingrediente.getName().contains("Hamburguer de Carne")).count() / 3;

        double valorCarne = Stream.of(ingredienteAPIs)
                .filter(ingrediente -> ingrediente.getName().contains("Hamburguer de Carne"))
                .mapToDouble(IngredienteAPI::getPrice)
                .max()
                .orElse(0.00);
        return valorCarne * countCarneDesconto;
    }
}