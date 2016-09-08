package com.nnd.bolon.dynedassesment.dependency.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 9/8/2016.
 */
//TODO : CHANGE WITH AUTOVALUE CLASS SINCE THERE IS A LOT <<< BOILERPLATE HERE
public class User {
    String id;
    String name;
    String username;
    String email;
    Address address;
    Geo geo;
    String phone;
    String website;
    Company company;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Geo getGeo() {
        return this.geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    class Address {
        String street;
        String suite;
        String city;
        String zipcode;

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

    class Geo {
        String lat;
        @SerializedName("long")
        String position_long;

        public String getLat() {
            return this.lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getPosition_long() {
            return this.position_long;
        }

        public void setPosition_long(String position_long) {
            this.position_long = position_long;
        }
    }

    class Company {
        String name;
        String catchPhrase;
        String bs;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCatchPhrase() {
            return this.catchPhrase;
        }

        public void setCatchPhrase(String catchPhrase) {
            this.catchPhrase = catchPhrase;
        }

        public String getBs() {
            return this.getBs();
        }

        public void setBs(String bs) {
            this.bs = bs;
        }
    }
}
