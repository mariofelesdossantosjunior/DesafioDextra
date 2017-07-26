package com.mario.desafiodextra.model.remote.impl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.mario.desafiodextra.model.remote.ILanchoneteAPI;
import com.mario.desafiodextra.util.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Mario Feles dos Santos Junior
 * @email <mario_feles@live.com/>
 * @project Movies
 * @since 10/03/17 - 14:12
 */

public class LanchoneteAPI {
    private static LanchoneteAPI sInstance;
    private ILanchoneteAPI mILanchoneteAPI;

    private LanchoneteAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setFieldNamingStrategy(
                                FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        this.mILanchoneteAPI = retrofit.create(ILanchoneteAPI.class);
    }

    public static LanchoneteAPI getInstance() {
        if (sInstance == null) {
            synchronized (LanchoneteAPI.class) {
                if (sInstance == null) sInstance = new LanchoneteAPI();
            }
        }
        return sInstance;
    }

    public ILanchoneteAPI getAPI() {
        return mILanchoneteAPI;
    }
}
