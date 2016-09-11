package com.nnd.bolon.dynedassesment.dependency.data.user;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by lenovo on 9/8/2016.
 */
public class Geo extends RealmObject{
    String lat;
    String lng;

    public String getLat(){ return this.lat; }
    public void setLat(String lat){ this.lat = lat; }

    public String getLng(){ return this.lng; }
    public void setLng(String lng){this.lng = lng; }
}
