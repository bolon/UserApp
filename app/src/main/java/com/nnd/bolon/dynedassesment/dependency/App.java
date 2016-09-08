package com.nnd.bolon.dynedassesment.dependency;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;
import timber.log.Timber;

/**
 * Created by lenovo on 9/8/2016.
 */
public class App extends Application {
    private ObjectGraph injector;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        //TODO : APIMODULE & DATAMODULE (USE REALM MAYBE)
        injector = ObjectGraph.create(new AppModule(this));
    }

    public ObjectGraph getInjector() {
        return injector;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }
}
