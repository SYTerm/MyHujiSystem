package com.huzd.huji.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class hujiMoving {
    private int id;
    @JsonProperty("name")
    private String Name;
    private String identityID;
    private String locationOld;
    private String locationNew;
    private String movingTypes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIdentityID() {
        return identityID;
    }

    public void setIdentityID(String identityID) {
        this.identityID = identityID;
    }

    public String getLocationOld() {
        return locationOld;
    }

    public void setLocationOld(String locationOld) {
        this.locationOld = locationOld;
    }

    public String getLocationNew() {
        return locationNew;
    }

    public void setLocationNew(String locationNew) {
        this.locationNew = locationNew;
    }

    public String getMovingTypes() {
        return movingTypes;
    }

    public void setMovingTypes(String movingTypes) {
        this.movingTypes = movingTypes;
    }
}
