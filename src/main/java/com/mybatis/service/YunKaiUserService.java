package com.mybatis.service;

import com.mybatis.domain.YunKaiUserModel;

import java.util.List;

/**
 * Created by yunkai on 2017/12/22.
 */
public interface YunKaiUserService {

    /**
     * 创建用户
     */
    public void create(YunKaiUserModel user);

    /**
     * 获取所有用户的Id
     *
     * @return
     */
    public List<Integer> findAllUserId();
}
