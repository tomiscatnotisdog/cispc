package com.xxxx.cispc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MyCreateModel {
    @Override
    public String toString() {
        return "MyCreateModel{" +
                "id=" + id +
                ", activityName='" + activityName + '\'' +
                ", place='" + place + '\'' +
                ", type='" + type + '\'' +
                ", createMan='" + createMan + '\'' +
                ", createDate=" + createDate +
                ", complateDate=" + complateDate +
                ", isComplate='" + isComplate + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getComplateDate() {
        return complateDate;
    }

    public void setComplateDate(Date complateDate) {
        this.complateDate = complateDate;
    }

    public String getIsComplate() {
        return isComplate;
    }

    public void setIsComplate(String isComplate) {
        this.isComplate = isComplate;
    }

    private Integer id;

    private String activityName;

    private String place;

    private String type;

    private String createMan;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date complateDate;

    private String isComplate;

}
