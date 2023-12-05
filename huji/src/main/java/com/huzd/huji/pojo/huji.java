package com.huzd.huji.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;


public class huji {
    private int id;
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

    public huji() {
    }

    public huji(int id, String name, String identityID, String sex, String age, String phoneNumber, String location) {
        this.id = id;
        Name = name;
        this.identityID = identityID;
        Sex = sex;
        Age = age;
        PhoneNumber = phoneNumber;
        Location = location;
    }

    @Override
    public String toString() {
        return "huji{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", identityID='" + identityID + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Age='" + Age + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Location='" + Location + '\'' +
                '}';
    }
}
