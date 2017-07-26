package com.mario.desafiodextra.model.entity.builder;

import com.mario.desafiodextra.model.entity.Lanche;
import com.mario.desafiodextra.model.entity.Pedido;
import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;

import java.util.List;

/**
 * Created by mario on 24/07/17.
 */

public class PedidoBuilder {
    private Pedido instance;

    public PedidoBuilder() {
        this.instance = new Pedido();
    }

    public PedidoBuilder withDate(Long date){
        this.instance.setDate(date);
        return this;
    }
    public PedidoBuilder withId(Long id){
        this.instance.setId(id);
        return this;
    }
    public PedidoBuilder withSandwich(Lanche sandwich){
        this.instance.setSandwich(sandwich);
        return this;
    }

    public PedidoBuilder withExtras(List<IngredienteAPI> extras){
        this.instance.setExtras(extras);
        return this;
    }

    public Pedido builder(){
        return this.instance;
    }
}
