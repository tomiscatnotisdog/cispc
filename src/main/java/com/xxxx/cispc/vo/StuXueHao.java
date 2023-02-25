package com.xxxx.cispc.vo;

public class StuXueHao {
    private Integer id;

    private String studentname; //明细

    private String student;//学号

    private String department;//系部

    private String specialized;//专业

    private String clasnam;//班级

    private String reason;//审批人

    private String approver;//理由

    private String refuse;

    private String devresult;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname == null ? null : studentname.trim();
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student == null ? null : student.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getSpecialized() {
        return specialized;
    }

    public void setSpecialized(String specialized) {
        this.specialized = specialized == null ? null : specialized.trim();
    }

    public String getClasnam() {
        return clasnam;
    }

    public void setClasnam(String clasnam) {
        this.clasnam = clasnam == null ? null : clasnam.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
    }

    public String getRefuse() {
        return refuse;
    }

    public void setRefuse(String refuse) {
        this.refuse = refuse == null ? null : refuse.trim();
    }

    public String getDevresult() {
        return devresult;
    }

    public void setDevresult(String devresult) {
        this.devresult = devresult == null ? null : devresult.trim();
    }

    @Override
    public String toString() {
        return "StuXueHao{" +
                "id=" + id +
                ", studentname='" + studentname + '\'' +
                ", student='" + student + '\'' +
                ", department='" + department + '\'' +
                ", specialized='" + specialized + '\'' +
                ", clasnam='" + clasnam + '\'' +
                ", reason='" + reason + '\'' +
                ", approver='" + approver + '\'' +
                ", refuse='" + refuse + '\'' +
                ", devresult='" + devresult + '\'' +
                '}';
    }
}