package com.xxxx.cispc.enums;

public enum DevResult {
    //未接单
    UNDEV(0),
    //进行中
    DEVING(1),
    //已完成
    DEV_SUCCESS(2),
    //失败
    DEV_FAILED(3);

    private  Integer status;

    DevResult(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
