package com.mario.desafiodextra.model.entity.builder;

import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;

/**
 * Created by mario on 24/07/17.
 */

public class IngredienteBuilder {
    private IngredienteAPI instance;

    public IngredienteBuilder() {
        this.instance = new IngredienteAPI();
    }

    public IngredienteBuilder withId(Long id){
        this.instance.setId(id);
        return this;
    }

    public IngredienteBuilder withImage(String image){
        this.instance.setImage(image);
        return this;
    }
    public IngredienteBuilder withName(String name){
        this.instance.setName(name);
        return this;
    }
    public IngredienteBuilder withPrice(Double price){
        this.instance.setPrice(price);
        return this;
    }
    public IngredienteAPI builder(){
        return this.instance;
    }
}
