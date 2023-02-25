package com.xxxx.cispc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Module {
    private Integer id; //资源id

    private String code; //资源码

    private String moduleName; //资源名称

    private String parentId; //所属资源

    private String url; //资源路径

    private String originator; //资源作者

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator == null ? null : originator.trim();
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
        return "Module{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", parentId=" + parentId +
                ", url='" + url + '\'' +
                ", originator='" + originator + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", isValid=" + isValid +
                '}';
    }
}