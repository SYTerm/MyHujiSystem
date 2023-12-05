package com.huzd.huji.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class connect {
    private int id;
    @JsonProperty("Username")
    private String Username;
    @JsonProperty("Name")
    private String Name;
    private String identityID;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentityID() {
        return identityID;
    }

    public void setIdentityID(String identityID) {
        this.identityID = identityID;
    }
}
