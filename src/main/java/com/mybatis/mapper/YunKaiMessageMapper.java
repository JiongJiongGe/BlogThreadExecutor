package com.mybatis.mapper;

import com.mybatis.domain.YunKaiMessageModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by yunkai on 2017/12/22.
 */
@Mapper
@Component
public interface YunKaiMessageMapper {

    /**
     * 创建消息
     *
     * @param message
     * @return
     */
    int create(YunKaiMessageModel message);

}
