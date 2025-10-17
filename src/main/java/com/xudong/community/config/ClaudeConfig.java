package com.xudong.community.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Claude API配置类
 * 专为中国用户设计，支持代理配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "claude")
public class ClaudeConfig {
    
    private Api api = new Api();
    private Proxy proxy = new Proxy();
    
    @Data
    public static class Api {
        private String key;
        private String baseUrl = "https://api.anthropic.com";
        private String model = "claude-3-sonnet-20240229";
        private Integer maxTokens = 4000;
        private Double temperature = 0.7;
    }
    
    @Data
    public static class Proxy {
        private boolean enabled = false;
        private String host = "127.0.0.1";
        private Integer port = 7890;
        private String type = "HTTP";
    }
}