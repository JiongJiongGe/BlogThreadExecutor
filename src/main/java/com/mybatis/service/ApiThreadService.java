package com.mybatis.service;

import com.mybatis.domain.YunKaiMessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yunkai on 2017/12/22.
 */
@Service("apiThreadService")
public class ApiThreadService {

    private final Logger logger = LoggerFactory.getLogger(ApiThreadService.class);

    @Autowired
    private YunKaiUserService yunKaiUserService;

    @Autowired
    private YunKaiMessageService yunKaiMessageService;

    private static ThreadPoolExecutor messageExecutor = new ThreadPoolExecutor(4, 8, 50,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.DiscardOldestPolicy());

    /**
     * 不采用多线程群发消息
     *
     * @return
     */
    public void directSend(){
        try {
            long startTime = System.currentTimeMillis();
            String info = "给所有用户发消息";
            //获取所有用户Id，测试用例为1w用户量
            List<Integer> userIds = yunKaiUserService.findAllUserId();
            logger.info("userIds size = {}", userIds.size());
            if (userIds != null && userIds.size() > 0) {
                for (Integer userId : userIds) {
                    YunKaiMessageModel message = new YunKaiMessageModel();
                    message.setUserId(userId);
                    message.setMessage(info);
                    yunKaiMessageService.create(message);
                }
            }
            long endTime = System.currentTimeMillis();
            //运行时间(running time) = 21739
            logger.info("运行时间(running time) = {}", endTime - startTime);
        }catch (Exception e){
            logger.error("error = {}", e);
        }
    }

    /**
     * 多线程方式一
     *
     * @return
     */
    public void threadSendFirst(){
        long startTime = System.currentTimeMillis();
        String info = "给所有用户发消息";
        //获取所有用户Id，测试用例为1w用户量
        List<Integer> userIds = yunKaiUserService.findAllUserId();
        logger.info("userIds size = {}", userIds.size());
        if(userIds != null && userIds.size() > 0){
            for(Integer userId : userIds){
                messageExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        YunKaiMessageModel message = new YunKaiMessageModel();
                        message.setUserId(userId);
                        message.setMessage(info);
                        yunKaiMessageService.create(message);
                    }
                });
            }
        }
        long endTime = System.currentTimeMillis();
        //运行时间(running time) = 30
        logger.info("运行时间(running time) = {}", endTime - startTime);
    }

    /**
     * 多线程方式二
     *
     * @return
     */
    public void threadSendSecond(){
        long startTime = System.currentTimeMillis();
        String info = "给所有用户发消息";
        //获取所有用户Id，测试用例为1w用户量
        List<Integer> userIds = yunKaiUserService.findAllUserId();
        logger.info("userIds size = {}", userIds.size());
        if(userIds != null && userIds.size() > 0){
            int userNum = userIds.size();
            int limit = 1000;
            Double limitDouble = 1000.0;
            DecimalFormat df = new DecimalFormat("#");
            int loop = Integer.valueOf(df.format(Math.ceil(userNum/limitDouble)));
            for(int i = 1; i < loop; i++){
                int num = (i-1) * limit;
                messageExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        for(int j = num; j < num + limit; j++) {
                            YunKaiMessageModel message = new YunKaiMessageModel();
                            message.setUserId(userIds.get(j));
                            message.setMessage(info);
                            yunKaiMessageService.create(message);
                        }
                    }
                });
            }
        }
        long endTime = System.currentTimeMillis();
        //运行时间(running time) = 29
        logger.info("运行时间(running time) = {}", endTime - startTime);
    }
}
