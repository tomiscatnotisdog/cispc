package com.xxxx.cispc.model;

public class CommentModel {

    @Override
    public String toString() {
        return "CommentQuery{" +
                "id='" + id + '\'' +
                ", joinMan='" + joinMan + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJoinMan() {
        return joinMan;
    }

    public void setJoinMan(String joinMan) {
        this.joinMan = joinMan;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String id;
    private String joinMan;
    private String comment;
}
