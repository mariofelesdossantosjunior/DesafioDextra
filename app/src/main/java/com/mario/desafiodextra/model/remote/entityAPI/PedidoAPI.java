
package com.mario.desafiodextra.model.remote.entityAPI;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PedidoAPI implements Parcelable{

    @SerializedName("id")
    private Long id;
    @SerializedName("date")
    private Long date;
    @SerializedName("extras")
    private List<Long> extras;
    @SerializedName("id_sandwich")
    private String id_sandwich;

    public PedidoAPI() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public List<Long> getExtras() {
        return extras;
    }

    public void setExtras(List<Long> extras) {
        this.extras = extras;
    }

    public String getId_sandwich() {
        return id_sandwich;
    }

    public void setId_sandwich(String id_sandwich) {
        this.id_sandwich = id_sandwich;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.date);
        dest.writeList(this.extras);
        dest.writeString(this.id_sandwich);
    }

    protected PedidoAPI(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.date = (Long) in.readValue(Long.class.getClassLoader());
        this.extras = new ArrayList<Long>();
        in.readList(this.extras, Long.class.getClassLoader());
        this.id_sandwich = in.readString();
    }

    public static final Creator<PedidoAPI> CREATOR = new Creator<PedidoAPI>() {
        @Override
        public PedidoAPI createFromParcel(Parcel source) {
            return new PedidoAPI(source);
        }

        @Override
        public PedidoAPI[] newArray(int size) {
            return new PedidoAPI[size];
        }
    };

    @Override
    public String toString() {
        return "PedidoAPI{" +
                "id=" + id +
                ", date=" + date +
                ", extras=" + extras +
                ", id_sandwich='" + id_sandwich + '\'' +
                '}';
    }
}
