package com.xxxx.cispc.query;

import com.xxxx.cispc.base.BaseQuery;

public class TableQuery extends BaseQuery {

    //用户名称
    private String userName;
    //用户性别
    private String sex;
    //用户身份
    private String identity;

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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "TableQuery{" +
                "username='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", identity='" + identity + '\'' +
                '}';
    }
}
