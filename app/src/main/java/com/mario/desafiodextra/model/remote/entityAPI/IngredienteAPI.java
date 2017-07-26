
package com.mario.desafiodextra.model.remote.entityAPI;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class IngredienteAPI implements Parcelable{

    @SerializedName("id")
    private Long id;
    @SerializedName("image")
    private String image;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private Double price;

    public IngredienteAPI() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.image);
        dest.writeString(this.name);
        dest.writeValue(this.price);
    }

    protected IngredienteAPI(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.image = in.readString();
        this.name = in.readString();
        this.price = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<IngredienteAPI> CREATOR = new Creator<IngredienteAPI>() {
        @Override
        public IngredienteAPI createFromParcel(Parcel source) {
            return new IngredienteAPI(source);
        }

        @Override
        public IngredienteAPI[] newArray(int size) {
            return new IngredienteAPI[size];
        }
    };

    @Override
    public String toString() {
        return "IngredienteAPI{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
