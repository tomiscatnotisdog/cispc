package com.xxxx.cispc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Game {
    private Integer id;

    private Integer gId;

    private String gameName;

    private Integer gamePrice;

    private String gameProfile;

    private Integer bond;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateDate;

    private Integer stater;

    private String phone;

    private String trueName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName == null ? null : gameName.trim();
    }

    public Integer getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(Integer gamePrice) {
        this.gamePrice = gamePrice;
    }

    public String getGameProfile() {
        return gameProfile;
    }

    public void setGameProfile(String gameProfile) {
        this.gameProfile = gameProfile == null ? null : gameProfile.trim();
    }

    public Integer getBond() {
        return bond;
    }

    public void setBond(Integer bond) {
        this.bond = bond;
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



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Integer getStater() {
        return stater;
    }

    public void setStater(Integer stater) {
        this.stater = stater;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gId=" + gId +
                ", gameName='" + gameName + '\'' +
                ", gamePrice=" + gamePrice +
                ", gameProfile='" + gameProfile + '\'' +
                ", bond=" + bond +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", stater=" + stater +
                ", phone='" + phone + '\'' +
                ", trueName='" + trueName + '\'' +
                '}';
    }
}