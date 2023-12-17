package com.huzd.huji.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class hujiMovingqueryData {
    private int id;
    @JsonProperty("Username")
    private String Username;
    private String processingTime;
    @JsonProperty("Name")
    private String Name;
    private String identityID;
    @JsonProperty("Sex")
    private String Sex;
    @JsonProperty("Age")
    private String Age;
    @JsonProperty("PhoneNumber")
    private String PhoneNumber;
    private String locationOld;
    private String locationNew;
    private String movingTypes;

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(String processingTime) {
        this.processingTime = processingTime;
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
