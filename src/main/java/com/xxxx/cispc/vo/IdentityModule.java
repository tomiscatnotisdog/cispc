package com.xxxx.cispc.vo;

import java.util.Date;

public class IdentityModule {
    private Integer id; //权限id

    private Integer identityId; //身份id

    private Integer code; //资源码

    private Date createDate; //创建时间

    private Date updateDate; //修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdentityId() {
        return identityId;
    }

    public void setIdentityId(Integer identityId) {
        this.identityId = identityId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
        return "IdentityModule{" +
                "id=" + id +
                ", identityId=" + identityId +
                ", code=" + code +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}