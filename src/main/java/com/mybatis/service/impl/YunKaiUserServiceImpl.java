package com.mybatis.service.impl;

import com.mybatis.domain.YunKaiUserModel;
import com.mybatis.mapper.YunKaiUserMapper;
import com.mybatis.service.YunKaiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yunkai on 2017/12/22.
 */
@Service("yunKaiUserService")
public class YunKaiUserServiceImpl implements YunKaiUserService{

    @Autowired
    private YunKaiUserMapper yunKaiUserMapper;

    /**
     * 创建用户
     */
    @Override
    @Transactional
    public void create(YunKaiUserModel user){
        yunKaiUserMapper.create(user);
    }

    /**
     * 获取所有用户的Id
     *
     * @return
     */
    @Override
    public List<Integer> findAllUserId(){
        return yunKaiUserMapper.queryAllUserId();
    }

}
