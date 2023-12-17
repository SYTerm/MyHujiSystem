package com.huzd.huji.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class queryData {
    private int id;
    @JsonProperty("Username")
    private String Username;
    private String processingTime;
    @JsonProperty( "Name")
    private String Name;
    private String identityID;
    @JsonProperty("Sex")
    private String Sex;
    @JsonProperty("Age")
    private String Age;
    @JsonProperty("PhoneNumber")
    private String PhoneNumber;
    @JsonProperty("Location")
    private String Location;

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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
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

}
