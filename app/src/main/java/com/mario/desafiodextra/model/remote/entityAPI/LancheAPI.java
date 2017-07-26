
package com.mario.desafiodextra.model.remote.entityAPI;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LancheAPI {

    @SerializedName("id")
    private Long id;
    @SerializedName("image")
    private String image;
    @SerializedName("ingredients")
    private List<Long> ingredients;
    @SerializedName("name")
    private String name;

    public LancheAPI() {
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

    public List<Long> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Long> ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LancheAPI{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", ingredients=" + ingredients +
                ", name='" + name + '\'' +
                '}';
    }
}
