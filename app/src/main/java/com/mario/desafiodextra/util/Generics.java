package com.mario.desafiodextra.util;

import com.annimon.stream.Stream;
import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by mario on 24/07/17.
 */

public class Generics {
    private static final DecimalFormat format = new DecimalFormat("R$ #0.00");

    public static String makeIngredients(List<IngredienteAPI> ingredients){
        if(!ingredients.isEmpty()) {
            StringBuilder st = new StringBuilder();
            Stream.of(ingredients).forEach(ingr ->
                    st.append(ingr.getName().concat(" ,")));
            return st.toString().substring(0, st.length() - 2);
        }else{
            return "";
        }
    }

    public static String formatCurrency(Double value){
       return  format.format(value);
    }

}
