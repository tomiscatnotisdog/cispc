package com.xxxx.cispc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class JoinActivity {
    private Integer makeFrendId;

    private String joinMan;

    private String comment;

    private String activityName;
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yy-MM-dd HH:mm:ss")
    private Date joinDate;

    private String createManPhone;

    private String createMan;

    public Integer getMakeFrendId() {
        return makeFrendId;
    }

    public void setMakeFrendId(Integer makeFrendId) {
        this.makeFrendId = makeFrendId;
    }

    public String getJoinMan() {
        return joinMan;
    }

    public void setJoinMan(String joinMan) {
        this.joinMan = joinMan == null ? null : joinMan.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getCreateManPhone() {
        return createManPhone;
    }

    public void setCreateManPhone(String createManPhone) {
        this.createManPhone = createManPhone == null ? null : createManPhone.trim();
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }
}