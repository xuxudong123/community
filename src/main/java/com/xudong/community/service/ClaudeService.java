package com.xudong.community.service;

import com.alibaba.fastjson.JSON;
import com.xudong.community.config.ClaudeConfig;
import com.xudong.community.dto.ClaudeRequest;
import com.xudong.community.dto.ClaudeResponse;
import com.xudong.community.dto.CodeGenerationRequest;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Claude API服务类
 * 专为中国用户设计，支持代理访问
 */
@Slf4j
@Service
public class ClaudeService {
    
    @Autowired
    private ClaudeConfig claudeConfig;
    
    private OkHttpClient httpClient;
    
    @PostConstruct
    public void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);
        
        // 如果启用了代理（针对中国用户）
        if (claudeConfig.getProxy().isEnabled()) {
            Proxy proxy = new Proxy(
                Proxy.Type.HTTP,
                new InetSocketAddress(
                    claudeConfig.getProxy().getHost(),
                    claudeConfig.getProxy().getPort()
                )
            );
            builder.proxy(proxy);
            log.info("已启用代理: {}:{}", 
                claudeConfig.getProxy().getHost(), 
                claudeConfig.getProxy().getPort());
        }
        
        this.httpClient = builder.build();
    }
    
    /**
     * 生成代码
     */
    public String generateCode(CodeGenerationRequest request) throws IOException {
        String systemPrompt = buildSystemPrompt(request);
        String userPrompt = buildUserPrompt(request);
        
        ClaudeRequest claudeRequest = new ClaudeRequest();
        claudeRequest.setModel(claudeConfig.getApi().getModel());
        claudeRequest.setMax_tokens(claudeConfig.getApi().getMaxTokens());
        claudeRequest.setTemperature(claudeConfig.getApi().getTemperature());
        claudeRequest.setMessages(Arrays.asList(
            new ClaudeRequest.Message("user", systemPrompt + "\n\n" + userPrompt)
        ));
        
        return callClaudeApi(claudeRequest);
    }
    
    /**
     * 代码审查
     */
    public String reviewCode(String code, String language) throws IOException {
        String prompt = String.format(
            "请审查以下%s代码，提供改进建议，包括：\n" +
            "1. 代码质量和最佳实践\n" +
            "2. 性能优化建议\n" +
            "3. 安全性问题\n" +
            "4. 可读性和维护性\n\n" +
            "代码：\n```%s\n%s\n```",
            language, language, code
        );
        
        ClaudeRequest claudeRequest = new ClaudeRequest();
        claudeRequest.setModel(claudeConfig.getApi().getModel());
        claudeRequest.setMax_tokens(claudeConfig.getApi().getMaxTokens());
        claudeRequest.setTemperature(0.3); // 代码审查使用较低的温度
        claudeRequest.setMessages(Arrays.asList(
            new ClaudeRequest.Message("user", prompt)
        ));
        
        return callClaudeApi(claudeRequest);
    }
    
    /**
     * 代码解释
     */
    public String explainCode(String code, String language) throws IOException {
        String prompt = String.format(
            "请详细解释以下%s代码的功能和工作原理：\n\n" +
            "```%s\n%s\n```\n\n" +
            "请用中文回答，包括：\n" +
            "1. 代码的主要功能\n" +
            "2. 关键逻辑的解释\n" +
            "3. 使用的设计模式或技术\n" +
            "4. 可能的使用场景",
            language, language, code
        );
        
        ClaudeRequest claudeRequest = new ClaudeRequest();
        claudeRequest.setModel(claudeConfig.getApi().getModel());
        claudeRequest.setMax_tokens(claudeConfig.getApi().getMaxTokens());
        claudeRequest.setTemperature(0.5);
        claudeRequest.setMessages(Arrays.asList(
            new ClaudeRequest.Message("user", prompt)
        ));
        
        return callClaudeApi(claudeRequest);
    }
    
    private String callClaudeApi(ClaudeRequest request) throws IOException {
        MediaType JSON_TYPE = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON_TYPE, JSON.toJSONString(request));
        
        Request httpRequest = new Request.Builder()
                .url(claudeConfig.getApi().getBaseUrl() + "/v1/messages")
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", claudeConfig.getApi().getKey())
                .addHeader("anthropic-version", "2023-06-01")
                .post(body)
                .build();
        
        try (Response response = httpClient.newCall(httpRequest).execute()) {
            if (!response.isSuccessful()) {
                String errorBody = response.body() != null ? response.body().string() : "未知错误";
                log.error("Claude API调用失败: {} - {}", response.code(), errorBody);
                throw new IOException("Claude API调用失败: " + response.code() + " - " + errorBody);
            }
            
            String responseBody = response.body().string();
            ClaudeResponse claudeResponse = JSON.parseObject(responseBody, ClaudeResponse.class);
            
            if (claudeResponse.getContent() != null && !claudeResponse.getContent().isEmpty()) {
                return claudeResponse.getContent().get(0).getText();
            } else {
                throw new IOException("Claude API返回了空响应");
            }
        }
    }
    
    private String buildSystemPrompt(CodeGenerationRequest request) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("你是一个专业的").append(request.getLanguage()).append("开发工程师。");
        
        if (request.getFramework() != null && !request.getFramework().isEmpty()) {
            prompt.append("你精通").append(request.getFramework()).append("框架。");
        }
        
        prompt.append("请根据用户需求生成高质量的代码。");
        
        if (request.isIncludeComments()) {
            prompt.append("代码应该包含详细的中文注释。");
        }
        
        if (request.isIncludeTests()) {
            prompt.append("请同时提供相应的单元测试代码。");
        }
        
        prompt.append("请确保代码遵循最佳实践和编码规范。");
        
        return prompt.toString();
    }
    
    private String buildUserPrompt(CodeGenerationRequest request) {
        StringBuilder prompt = new StringBuilder();
        
        if (request.getDescription() != null && !request.getDescription().isEmpty()) {
            prompt.append("需求描述：").append(request.getDescription()).append("\n\n");
        }
        
        prompt.append("请生成").append(request.getLanguage()).append("代码：\n");
        prompt.append(request.getPrompt());
        
        return prompt.toString();
    }
}