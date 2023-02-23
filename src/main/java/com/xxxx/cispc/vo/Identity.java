package com.xxxx.cispc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Identity {
    private Integer id; //用户id

    private String nameIdentity; //身份名称

    @JsonFormat(pattern = "yyyy-MM-dd hh-mm-ss ",timezone = "GMT-8")
    private Date createDate; //创建时间

    @JsonFormat(pattern = "yyyy-MM-dd hh-mm-ss ",timezone = "GMT-8")
    private Date updateDate; //修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameIdentity() {
        return nameIdentity;
    }

    public void setNameIdentity(String nameIdentity) {
        this.nameIdentity = nameIdentity == null ? null : nameIdentity.trim();
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

    @Override
    public String toString() {
        return "Identity{" +
                "id=" + id +
                ", nameIdentity='" + nameIdentity + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}