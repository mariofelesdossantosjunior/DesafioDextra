package com.mario.desafiodextra.model.entity.builder;

import com.annimon.stream.Stream;
import com.mario.desafiodextra.model.entity.Lanche;
import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario on 24/07/17.
 */

public class LancheBuilder {
    private Lanche instance;

    public LancheBuilder() {
        this.instance = new Lanche();
    }

    public LancheBuilder withId(Long id){
        this.instance.setId(id);
        return this;
    }
    public LancheBuilder withName(String name){
        this.instance.setName(name);
        return this;
    }
    public LancheBuilder withImage(String image){
        this.instance.setImage(image);
        return this;
    }

    public LancheBuilder withExtras(List<IngredienteAPI> extras){
        this.instance.setExtras(extras);
        return this;
    }
    public LancheBuilder withIngredients(List<IngredienteAPI> ingredients){
        if(this.instance.getIngredients() == null){
            this.instance.setIngredients(new ArrayList<>());
        }

        Stream.of(ingredients).forEach(ingredienteAPI ->
                this.instance.getIngredients()
                        .add(new IngredienteBuilder()
                                .withId(ingredienteAPI.getId())
                                .withImage(ingredienteAPI.getImage())
                                .withName(ingredienteAPI.getName())
                                .withPrice(ingredienteAPI.getPrice())
                                .builder()));

        return this;
    }

    public Lanche builder(){
        return this.instance;
    }
}
