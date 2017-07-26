package com.mario.desafiodextra;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author Mario Feles dos Santos Junior
 * @email <mario_feles@live.com/>
 * @project Movies
 * @since 10/03/17 - 14:05
 */

public class DesafioDextra extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
