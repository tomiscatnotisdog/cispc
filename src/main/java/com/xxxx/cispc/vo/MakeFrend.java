package com.xxxx.cispc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MakeFrend {
    @Override
    public String toString() {
        return "MakeFrend{" +
                "id=" + id +
                ", createMan='" + createMan + '\'' +
                ", maxNumbers=" + maxNumbers +
                ", place='" + place + '\'' +
                ", type='" + type + '\'' +
                ", createDate=" + createDate +
                ", isComplate='" + isComplate + '\'' +
                ", complateDate=" + complateDate +
                ", activityName='" + activityName + '\'' +
                ", isValid=" + isValid +
                ", createManPhone='" + createManPhone + '\'' +
                '}';
    }

    private Integer id;

    private String createMan;

    private Integer maxNumbers;

    private String place;

    private String type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private String isComplate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date complateDate;

    private String activityName;

    private Integer isValid;

    private String createManPhone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public Integer getMaxNumbers() {
        return maxNumbers;
    }

    public void setMaxNumbers(Integer maxNumbers) {
        this.maxNumbers = maxNumbers;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIsComplate() {
        return isComplate;
    }

    public void setIsComplate(String isComplate) {
        this.isComplate = isComplate;
    }

    public Date getComplateDate() {
        return complateDate;
    }

    public void setComplateDate(Date complateDate) {
        this.complateDate = complateDate;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getCreateManPhone() {
        return createManPhone;
    }

    public void setCreateManPhone(String createManPhone) {
        this.createManPhone = createManPhone;
    }
}