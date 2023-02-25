package com.xxxx.cispc.model;

import java.util.Date;

public class IdentityModuleModel {

    private Integer id; //权限id
    private String identityName; //身份名称
    private String moduleName; //资源名称
    private Date createDate; //创建时间
    private Date updateDate; //更新时间


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
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
        return "IdentityModuleModel{" +
                "id=" + id +
                ", identityName='" + identityName + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
