package com.xxxx.cispc.vo;

import java.util.Date;

public class CreateOrder {
    private Integer id;

    private String createMan;

    private Date createDate;

    private String createManPhone;

    private String createManLocation;

    private Integer isComplete;

    private Date completeDate;

    private String context;

    private Integer stUserNumber;

    private Integer isValid;

    private String receiveMan;

    private String receiveManPhone;

    private Date receiveDate;

    private Integer stReceiveUserNumber;

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
        this.createMan = createMan == null ? null : createMan.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateManPhone() {
        return createManPhone;
    }

    public void setCreateManPhone(String createManPhone) {
        this.createManPhone = createManPhone == null ? null : createManPhone.trim();
    }

    public String getCreateManLocation() {
        return createManLocation;
    }

    public void setCreateManLocation(String createManLocation) {
        this.createManLocation = createManLocation == null ? null : createManLocation.trim();
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public Integer getStUserNumber() {
        return stUserNumber;
    }

    public void setStUserNumber(Integer stUserNumber) {
        this.stUserNumber = stUserNumber;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getReceiveMan() {
        return receiveMan;
    }

    public void setReceiveMan(String receiveMan) {
        this.receiveMan = receiveMan == null ? null : receiveMan.trim();
    }

    public String getReceiveManPhone() {
        return receiveManPhone;
    }

    public void setReceiveManPhone(String receiveManPhone) {
        this.receiveManPhone = receiveManPhone == null ? null : receiveManPhone.trim();
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Integer getStReceiveUserNumber() {
        return stReceiveUserNumber;
    }

    public void setStReceiveUserNumber(Integer stReceiveUserNumber) {
        this.stReceiveUserNumber = stReceiveUserNumber;
    }
}