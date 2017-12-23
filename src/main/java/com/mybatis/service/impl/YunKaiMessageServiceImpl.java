package com.mybatis.service.impl;

import com.mybatis.domain.YunKaiMessageModel;
import com.mybatis.mapper.YunKaiMessageMapper;
import com.mybatis.service.YunKaiMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yunkai on 2017/12/22.
 */
@Service("yunKaiMessageService")
public class YunKaiMessageServiceImpl implements YunKaiMessageService{

    @Autowired
    private YunKaiMessageMapper yunKaiMessageMapper;

    /**
     * 创建消息
     *
     * @param message
     */
    @Override
    @Transactional
    public void create(YunKaiMessageModel message){
        yunKaiMessageMapper.create(message);
    }

}
