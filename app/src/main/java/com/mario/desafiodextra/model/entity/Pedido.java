
package com.mario.desafiodextra.model.entity;

import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;

import java.util.List;

public class Pedido {

    private Long date;
    private List<IngredienteAPI> extras;
    private Long id;
    private Lanche sandwich;

    public Pedido() {
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public List<IngredienteAPI> getExtras() {
        return extras;
    }

    public void setExtras(List<IngredienteAPI> extras) {
        this.extras = extras;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lanche getSandwich() {
        return sandwich;
    }

    public void setSandwich(Lanche sandwich) {
        this.sandwich = sandwich;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "date=" + date +
                ", extras=" + extras +
                ", id=" + id +
                ", sandwich=" + sandwich +
                '}';
    }
}
