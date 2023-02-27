package com.xxxx.cispc.query;

import com.xxxx.cispc.base.BaseQuery;

public class MyReceiveQuery extends BaseQuery {
    private String receiveMan;//接单人
    private String createManLocation;//目的地
    private String receiveManPhone;//接单人联系电话
    private Integer stUserNumber;//学工号
    private Integer isComplete;//订单状态
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

    public String getReceiveMan() {
        return receiveMan;
    }

    public void setReceiveMan(String receiveMan) {
        this.receiveMan = receiveMan;
    }

    public String getCreateManLocation() {
        return createManLocation;
    }

    public void setCreateManLocation(String createManLocation) {
        this.createManLocation = createManLocation;
    }

    public String getReceiveManPhone() {
        return receiveManPhone;
    }

    public void setReceiveManPhone(String receiveManPhone) {
        this.receiveManPhone = receiveManPhone;
    }

    public Integer getStUserNumber() {
        return stUserNumber;
    }

    public void setStUserNumber(Integer stUserNumber) {
        this.stUserNumber = stUserNumber;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }
}
