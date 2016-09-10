package com.nnd.bolon.dynedassesment.dependency.modules;

import android.app.Application;

import com.nnd.bolon.dynedassesment.dependency.App;
import com.nnd.bolon.dynedassesment.function.MainActivity;
import com.nnd.bolon.dynedassesment.function.about.AboutActivity;
import com.nnd.bolon.dynedassesment.function.showlistuser.ListUsersFragment;
import com.nnd.bolon.dynedassesment.function.splash.SplashActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 9/8/2016.
 */

@Module(
        includes = {
                ApiModule.class,
                DataModule.class
        },
        library = true,
        injects = {
                SplashActivity.class,
                MainActivity.class,
                ListUsersFragment.class,
        }
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
