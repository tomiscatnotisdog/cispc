package com.xxxx.cispc.query;

import com.xxxx.cispc.base.BaseQuery;

public class IdentityModuleQuery extends BaseQuery {

    private String identityName; //身份名称

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    @Override
    public String toString() {
        return "IdentityModuleQuery{" +
                "identityName='" + identityName + '\'' +
                '}';
    }
}
