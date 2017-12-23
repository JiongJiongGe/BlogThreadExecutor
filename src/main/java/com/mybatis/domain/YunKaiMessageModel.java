package com.mybatis.domain;

import java.io.Serializable;

/**
 * 用户消息model
 *
 * Created by yunkai on 2017/12/21.
 */
public class YunKaiMessageModel implements Serializable{

    private static final long serialVersionUID = 3800320656888241021L;

    private Integer id;

    private Integer userId;  //用户Id

    private String message; //消息内容

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
