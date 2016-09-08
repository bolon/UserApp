package com.nnd.bolon.dynedassesment.dependency;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nnd.bolon.dynedassesment.dependency.network.NetworkInterface;
import com.nnd.bolon.dynedassesment.splash.SplashActivity;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 9/8/2016.
 */

@Module(
        complete = false,
        library = true,
        injects = {
                SplashActivity.class
        }
)

public final class ApiModule {
    public static final String ENDPOINT = "https://jsonplaceholder.typicode.com";

    private Retrofit.Builder initRetrofit(Call.Factory callFactory, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .callFactory(callFactory);
    }

    @Provides
    Retrofit provideRetrofit(Call.Factory callFactory, Gson gson) {
        return initRetrofit(callFactory, gson)
                .baseUrl(ENDPOINT)
                .build();
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .create();
    }

    @Singleton
    @Provides
    Call.Factory provideCallFactory() {
        return new OkHttpClient().newBuilder()
                .build();
    }

    @Singleton
    @Provides
    NetworkInterface provideService(Retrofit retrofit) {
        return retrofit.create(NetworkInterface.class);
    }
}
