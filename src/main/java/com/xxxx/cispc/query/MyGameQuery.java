package com.xxxx.cispc.query;

import com.xxxx.cispc.base.BaseQuery;

public class MyGameQuery extends BaseQuery {
    private String gameName;
    private Integer stater;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Integer getStater() {
        return stater;
    }

    public void setStater(Integer stater) {
        this.stater = stater;
    }
}
