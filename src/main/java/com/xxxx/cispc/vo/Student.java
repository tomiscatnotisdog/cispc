package com.xxxx.cispc.vo;

import java.util.Date;

public class Student {
    private Integer id;

    private String userName;

    private String trueName;

    private String email;

    private String phone;

    private String clazzName;

    private Integer isValid;

    private Date createDate;

    private Date updateDate;

    private String introducatio;

    private String genderr;

    private String son;

    private String addresss;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName == null ? null : clazzName.trim();
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getIntroducatio() {
        return introducatio;
    }

    public void setIntroducatio(String introducatio) {
        this.introducatio = introducatio == null ? null : introducatio.trim();
    }

    public String getGenderr() {
        return genderr;
    }

    public void setGenderr(String genderr) {
        this.genderr = genderr == null ? null : genderr.trim();
    }

    public String getSon() {
        return son;
    }

    public void setSon(String son) {
        this.son = son == null ? null : son.trim();
    }

    public String getAddresss() {
        return addresss;
    }

    public void setAddresss(String addresss) {
        this.addresss = addresss == null ? null : addresss.trim();
    }
}