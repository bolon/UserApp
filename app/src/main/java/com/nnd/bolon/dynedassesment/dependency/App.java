package com.nnd.bolon.dynedassesment.dependency;

import android.app.Application;
import android.content.Context;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.nnd.bolon.dynedassesment.dependency.modules.AppModule;

import dagger.ObjectGraph;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

/**
 * Created by lenovo on 9/8/2016.
 */
public class App extends Application {
    private ObjectGraph injector;
    private final static int SCHEMA_VERSION = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        //Timber for logging -> no pun intended
        Timber.plant(new Timber.DebugTree());

        // Configure default configuration for Realm
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(SCHEMA_VERSION)
                .build();
        Realm.setDefaultConfiguration(realmConfig);

        //Iconify
        Iconify.with(new FontAwesomeModule());

        injector = ObjectGraph.create(new AppModule(this));
    }

    public ObjectGraph getInjector() {
        return injector;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }
}
