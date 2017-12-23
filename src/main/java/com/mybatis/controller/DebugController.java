package com.mybatis.controller;

import com.mybatis.domain.YunKaiMessageModel;
import com.mybatis.domain.YunKaiUserModel;
import com.mybatis.service.ApiThreadService;
import com.mybatis.service.YunKaiMessageService;
import com.mybatis.service.YunKaiUserService;
import com.sun.deploy.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yunkai on 2017/12/22.
 */
@RestController
@RequestMapping("/debug")
public class DebugController {

    private final Logger logger = LoggerFactory.getLogger(DebugController.class);

    @Autowired
    private YunKaiUserService yunKaiUserService;

    @Autowired
    private ApiThreadService apiThreadService;

    /**
     * 创建预模拟的用户
     *
     * @return
     */
    @RequestMapping(value = "/create/user")
    public String createUser(){
        for(int i = 0; i < 9999; i++){
            YunKaiUserModel user = new YunKaiUserModel();
            user.setUserName("yunkai_" + i);
            user.setUserPhone("13588219834");
            yunKaiUserService.create(user);
        }
        return "create user is running ... ";
    }

    /**
     * 不采用多线程群发消息
     *
     * @return
     */
    @RequestMapping(value = "/direct/send/message")
    public String directSendMessage(){
        apiThreadService.directSend();
        return "direct send message is running ... ";
    }

    /**
     * 多线程方式一
     *
     * @return
     */
    @RequestMapping(value = "/thread/send/first")
    public String threadSendFirst(){
        apiThreadService.threadSendFirst();
        return "thread first send message is running ... ";
    }

    /**
     * 多线程方式二
     *
     * @return
     */
    @RequestMapping(value = "/thread/send/second")
    public String threadSendSecond(){
        apiThreadService.threadSendSecond();
        return "thread second send message is running ... ";
    }
}
