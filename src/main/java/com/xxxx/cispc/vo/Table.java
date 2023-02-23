package com.xxxx.cispc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Table {
    private Integer id;

    private String userName;

    private String sex;

    private String city;

    private String phone;

    private String email;

    private String identity;

    private String sign;

    @JsonFormat(pattern = "yyyy-MM-dd hh-mm-ss ",timezone = "GMT-8")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd hh-mm-ss ",timezone = "GMT-8")
    private Date updateDate;

    private int experience; //积分

    private double score; //评分

    private int wealth; //财富

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
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getWealth() {
        return wealth;
    }

    public void setWealth(int wealth) {
        this.wealth = wealth;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", identity='" + identity + '\'' +
                ", sign='" + sign + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", experience=" + experience +
                ", score=" + score +
                ", wealth=" + wealth +
                '}';
    }
}