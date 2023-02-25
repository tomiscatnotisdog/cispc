package com.xxxx.cispc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Identity {
    private Integer id; //角色Id

    private String nameIdentity; //角色名称

    private String remarks; //备注

    @JsonFormat(pattern = "yyyy-MM-dd hh-mm-ss ",timezone = "GMT-8")
    private Date createDate; //创建时间

    @JsonFormat(pattern = "yyyy-MM-dd hh-mm-ss ",timezone = "GMT-8")
    private Date updateDate; //修改时间

    private Integer isValid; //是否有效

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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


    @Override
    public String toString() {
        return "Identity{" +
                "id=" + id +
                ", nameIdentity='" + nameIdentity + '\'' +
                ", remarks='" + remarks + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", isValid=" + isValid +
                '}';
    }
}