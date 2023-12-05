package com.huzd.huji.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;

public class hujiFormUpd {
    private int id;
    @JsonProperty("Name")
    private String Name;
    private String identityID;
    @JsonProperty("Sex")
    private String Sex;
    @JsonProperty("Age")
    private String Age;
    @JsonProperty("PhoneNumber")
    private String PhoneNumber;
    @JsonProperty("locationNew")
    private String Location;
    @JsonProperty("locationOld")
    private String LocationOld;
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

    public String getLocationOld() {
        return LocationOld;
    }

    public void setLocationOld(String locationOld) {
        LocationOld = locationOld;
    }

    public String getMovingTypes() {
        return movingTypes;
    }

    public void setMovingTypes(String movingTypes) {
        this.movingTypes = movingTypes;
    }

    @Override
    public String toString() {
        return "hujiFormUpd{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", identityID='" + identityID + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Age='" + Age + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Location='" + Location + '\'' +
                ", LocationOld='" + LocationOld + '\'' +
                ", movingTypes='" + movingTypes + '\'' +
                '}';
    }
}
