package com.xxxx.cispc.query;

import com.xxxx.cispc.base.BaseQuery;

public class JoinActivityQuery extends BaseQuery {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String activityName;
    private String createMan;

    private String joinMan;

    public String getJoinMan() {
        return joinMan;
    }

    public void setJoinMan(String joinMan) {
        this.joinMan = joinMan;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }
}
