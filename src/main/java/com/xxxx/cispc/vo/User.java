package com.xxxx.cispc.vo;

import java.util.Date;

public class User {
    private Integer id;

    private String userNumber;

    private String trueName;

    private String nickName;

    private String phone;

    private Date createDate;

    private Date updateDate;

    private Integer isValid;

    private String password;

    private String personalProfile;

    private String identity;
    //图形验证
    private String captcha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile == null ? null : personalProfile.trim();
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userNumber=" + userNumber +
                ", trueName='" + trueName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", isValid=" + isValid +
                ", password='" + password + '\'' +
                ", personalProfile='" + personalProfile + '\'' +
                ", identity='" + identity + '\'' +
                ", captcha='" + captcha + '\'' +
                '}';
    }
}