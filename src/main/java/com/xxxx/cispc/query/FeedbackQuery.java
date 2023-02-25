package com.xxxx.cispc.query;

import com.xxxx.cispc.base.BaseQuery;

/*查询类*/
public class FeedbackQuery extends BaseQuery {

    //分页参数


    //条件查询
    private String trueName;//真实姓名
    private String nickName;//昵称
    private Integer results;//分配装太

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }
}
