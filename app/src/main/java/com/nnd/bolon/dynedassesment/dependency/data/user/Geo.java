package com.nnd.bolon.dynedassesment.dependency.data.user;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by lenovo on 9/8/2016.
 */
public class Geo extends RealmObject{
    String lat;
    @SerializedName("long")
    String position_long;

    public String getLat(){ return this.lat; }
    public void setLat(String lat){ this.lat = lat; }

    public String getPosition_long(){ return this.position_long; }
    public void setPosition_long(String position_long){this.position_long = position_long; }
}
