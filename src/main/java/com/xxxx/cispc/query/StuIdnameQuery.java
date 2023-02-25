package com.xxxx.cispc.query;

import com.xxxx.cispc.base.BaseQuery;

public class StuIdnameQuery extends BaseQuery{
    private String student; //学号
    private String studentname; //姓名
    private String devresult; //请假状态

    public String getDevresult() {
        return devresult;
    }

    public void setDevresult(String devresult) {
        this.devresult = devresult;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
}
