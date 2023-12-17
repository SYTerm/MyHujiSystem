package com.huzd.huji.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class notice {
    private int id;
    @JsonProperty("Username")
    private String Username;
    private String info;
    private String processingTime;

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(String processingTime) {
        this.processingTime = processingTime;
    }
}
