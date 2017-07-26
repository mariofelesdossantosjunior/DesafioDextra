package com.mario.desafiodextra.model.entity.promocoes;

/**
 * Created by mario on 24/07/17.
 */

public enum PromocaoEnum {
    LIGHT {
        @Override
        public Promocao getPromocao() {
            return new Light();
        }
    },
    MUITA_CARNE {
        @Override
        public Promocao getPromocao() {
            return new MuitaCarne();
        }
    },
    MUITO_QUEIJO {
        @Override
        public Promocao getPromocao() {
            return new MuitoQueijo();
        }
    };

    public abstract Promocao getPromocao();
}
