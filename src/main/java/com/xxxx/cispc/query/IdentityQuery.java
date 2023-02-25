package com.xxxx.cispc.query;

import com.xxxx.cispc.base.BaseQuery;

public class IdentityQuery extends BaseQuery {

    private String nameIdentity; //角色名称

    private String remarks; //备注



    public String getNameIdentity() {
        return nameIdentity;
    }

    public void setNameIdentity(String nameIdentity) {
        this.nameIdentity = nameIdentity;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "IdentityQuery{" +
                "nameIdentity='" + nameIdentity + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
