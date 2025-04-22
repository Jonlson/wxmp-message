package com.wth.msgmp_api.service.Impl;


import com.wth.msgmp_api.bean.WxLoginDto;
import com.wth.msgmp_api.service.MgtWxPhoneBindingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MgtWxPhoneBindingServiceImpl implements MgtWxPhoneBindingService {
    /** redis服务 */
    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    /**
     * 公众号登录
     * @return
     */
    @Override
    public Result<?> wxLogin(WxLoginDto wxLoginDto) {
        redisTemplate.opsForValue().set("@mgt@wx_login@scene@" + wxLoginDto.getSceneId(), wxLoginDto.getPhone(), 5, TimeUnit.MINUTES);
        return ResultUtils.success();
    }
}
