package com.nnd.bolon.dynedassesment.dependency.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by lenovo on 9/8/2016.
 */
@Module(
        complete = false,
        library = true
)
public class DataModule {
    @Singleton
    @Provides
    Realm provideRealm(Application application) {
        return Realm.getDefaultInstance();
    }
}
