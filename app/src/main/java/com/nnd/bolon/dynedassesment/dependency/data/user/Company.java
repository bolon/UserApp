package com.nnd.bolon.dynedassesment.dependency.data.user;

import io.realm.RealmObject;

/**
 * Created by lenovo on 9/8/2016.
 */
public class Company extends RealmObject{
    String name;
    String catchPhrase;
    String bs;

    public String getName(){ return this.name; }
    public void setName(String name){ this.name = name; }

    public String getCatchPhrase(){ return this.catchPhrase; }
    public void setCatchPhrase(String catchPhrase){ this.catchPhrase = catchPhrase; }

    public String getBs(){ return this.getBs(); }
    public void setBs(String bs){ this.bs = bs; }
}
