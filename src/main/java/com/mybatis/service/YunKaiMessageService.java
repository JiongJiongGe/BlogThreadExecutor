package com.mybatis.service;

import com.mybatis.domain.YunKaiMessageModel;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yunkai on 2017/12/22.
 */
public interface YunKaiMessageService {

    /**
     * 创建消息
     *
     * @param message
     */
    public void create(YunKaiMessageModel message);
}
