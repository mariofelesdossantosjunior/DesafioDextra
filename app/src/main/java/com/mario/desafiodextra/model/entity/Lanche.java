package com.mario.desafiodextra.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.annimon.stream.Stream;
import com.mario.desafiodextra.model.entity.promocoes.PromocaoEnum;
import com.mario.desafiodextra.model.remote.entityAPI.IngredienteAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario on 24/07/17.
 */

public class Lanche implements Parcelable{
    private Long id;
    private String image;
    private List<IngredienteAPI> ingredients;
    private String name;
    private List<IngredienteAPI> extras;

    public Lanche() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<IngredienteAPI> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredienteAPI> ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredienteAPI> getExtras() {
        return extras;
    }

    public void setExtras(List<IngredienteAPI> extras) {
        this.extras = extras;
    }

    public Double getPriceTotal() {
        double extras = 0.0;
        double ingredients = Stream.of(this.ingredients).mapToDouble(IngredienteAPI::getPrice).sum();

        if(this.extras != null && !this.extras.isEmpty()) {
            this.ingredients.addAll(this.extras);
            extras = Stream.of(this.extras).mapToDouble(IngredienteAPI::getPrice).sum();
        }
        return  ingredients + extras - checkPromotion();
    }

    private Double checkPromotion() {
        return  PromocaoEnum.LIGHT.getPromocao().calcTotal(ingredients)
                + PromocaoEnum.MUITA_CARNE.getPromocao().calcTotal(ingredients)
                + PromocaoEnum.MUITO_QUEIJO.getPromocao().calcTotal(ingredients);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.image);
        dest.writeTypedList(this.ingredients);
        dest.writeString(this.name);
        dest.writeList(this.extras);
    }

    protected Lanche(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.image = in.readString();
        this.ingredients = in.createTypedArrayList(IngredienteAPI.CREATOR);
        this.name = in.readString();
        this.extras = new ArrayList<>();
        in.readList(this.extras, Long.class.getClassLoader());
    }

    public static final Creator<Lanche> CREATOR = new Creator<Lanche>() {
        @Override
        public Lanche createFromParcel(Parcel source) {
            return new Lanche(source);
        }

        @Override
        public Lanche[] newArray(int size) {
            return new Lanche[size];
        }
    };

    @Override
    public String toString() {
        return "Lanche{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", ingredients=" + ingredients +
                ", name='" + name + '\'' +
                ", extras=" + extras +
                '}';
    }
}
