package com.huzd.huji.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

public class idCard {
    private String id ;
    @JsonProperty("Name")
    private String Name;
    private String identityID;
    private String processingTime;
    private String collectionTime;
    private String status;
    @JsonProperty("Sex")
    private String Sex;
    @JsonProperty("Age")
    private String Age;

    private String nation;

    private String types;

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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



    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(String processingTime) {
        this.processingTime = processingTime;
    }

    public String getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "idCard{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", identityID='" + identityID + '\'' +
                ", processingTime='" + processingTime + '\'' +
                ", collectionTime='" + collectionTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
