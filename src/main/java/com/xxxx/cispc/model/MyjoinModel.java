package com.xxxx.cispc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MyjoinModel {

    private Integer makeFrendId;

    private String joinMan;

    private String comment;

    private String activityName;
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yy-MM-dd HH:mm:ss")
    private Date joinDate;

    private String createManPhone;

    private String createMan;

    private String place;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date complateDate;

    private String type;



    public Integer getMakeFrendId() {
        return makeFrendId;
    }

    public void setMakeFrendId(Integer makeFrendId) {
        this.makeFrendId = makeFrendId;
    }

    public String getJoinMan() {
        return joinMan;
    }

    public void setJoinMan(String joinMan) {
        this.joinMan = joinMan;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getCreateManPhone() {
        return createManPhone;
    }

    public void setCreateManPhone(String createManPhone) {
        this.createManPhone = createManPhone;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getComplateDate() {
        return complateDate;
    }

    public void setComplateDate(Date complateDate) {
        this.complateDate = complateDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
