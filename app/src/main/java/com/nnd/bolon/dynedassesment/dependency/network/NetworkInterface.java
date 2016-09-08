package com.nnd.bolon.dynedassesment.dependency.network;

import com.nnd.bolon.dynedassesment.dependency.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lenovo on 9/8/2016.
 */
public interface NetworkInterface {
    @GET("/users")
    Call<List<User>> getUser();
}
