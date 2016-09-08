package com.nnd.bolon.dynedassesment.dependency;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 9/8/2016.
 */

@Module(
        includes = {
                ApiModule.class,
        },
        library = true
)
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }


    @Provides
    Application provideApplication() {
        return application;
    }
}
