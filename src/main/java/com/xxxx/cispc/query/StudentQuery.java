package com.xxxx.cispc.query;


import com.xxxx.cispc.base.BaseQuery;

public class StudentQuery extends BaseQuery {

    private String userName; //老师姓名
    private String phone; //手机号
    private String clazzName; //班级

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }


}
