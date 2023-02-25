package com.xxxx.cispc.query;

import com.xxxx.cispc.base.BaseQuery;

public class GameQuery extends BaseQuery {
    private String gameName;
    private Integer gamePrice;
    private String gameProfile;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Integer getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(Integer gamePrice) {
        this.gamePrice = gamePrice;
    }

    public String getGameProfile() {
        return gameProfile;
    }

    public void setGameProfile(String gameProfile) {
        this.gameProfile = gameProfile;
    }
}
