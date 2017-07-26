
package com.mario.desafiodextra.model.remote.entityAPI;

import com.google.gson.annotations.SerializedName;

public class PromocaoAPI {

    @SerializedName("id")
    private Long id;

    @SerializedName("description")
    private String description;

    @SerializedName("name")
    private String name;

    public PromocaoAPI() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PromocaoAPI{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
