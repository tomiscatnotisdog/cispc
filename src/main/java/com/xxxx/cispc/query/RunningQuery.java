package com.xxxx.cispc.query;

import com.xxxx.cispc.base.BaseQuery;


public class RunningQuery extends BaseQuery {
    private String createMan;//发起人
    private String createManLocation;//目的地
    private Integer isComplete;//订单状态
    private String createManPhone;//联系电话
    private Integer stUserNumber;//学工号
    private String trueName;//发起人
    private String userNumber;

    private String phone;//联系电话

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStUserNumber() {
        return stUserNumber;
    }

    public void setStUserNumber(Integer stUserNumber) {
        this.stUserNumber = stUserNumber;
    }

    public String getCreateManPhone() {
        return createManPhone;
    }

    public void setCreateManPhone(String createManPhone) {
        this.createManPhone = createManPhone;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getCreateManLocation() {
        return createManLocation;
    }

    public void setCreateManLocation(String createManLocation) {
        this.createManLocation = createManLocation;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }
}
