package com.wth.msgmp_api.config;

import com.wth.msgmp_api.utils.JsonUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信属性类
 */
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class WxMpRedisProperties {
    /**
     * 是否使用redis存储access token
     */
    private boolean useRedis;

    /**
     * redis服务器 主机地址
     */
    private String host;

    /**
     * redis服务器 端口号
     */
    private Integer port;

    /**
     * 密码
     */
    private String password;

    /**
     * 时间
     */
    private String timeout;


    private Integer database;


    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
