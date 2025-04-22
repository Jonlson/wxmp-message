package com.wth.msgmp_api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 对应的url配置
 */
@Data
@ConfigurationProperties(prefix = "spring.url")
public class UrlProperties {

    /**
     * 请求地址URL
     */
    private String requestUrl;
}
