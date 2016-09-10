package com.nnd.bolon.dynedassesment.dependency.data.user;

import io.realm.RealmObject;

/**
 * Created by lenovo on 9/8/2016.
 */
public class Address extends RealmObject {
    String street;
    String suite;
    String city;
    String zipcode;
    Geo geo;

    public Geo getGeo() {
        return this.geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return this.suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
