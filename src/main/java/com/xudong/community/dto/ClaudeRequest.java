package com.xudong.community.dto;

import lombok.Data;
import java.util.List;

/**
 * Claude API请求DTO
 */
@Data
public class ClaudeRequest {
    private String model;
    private Integer max_tokens;
    private Double temperature;
    private List<Message> messages;
    
    @Data
    public static class Message {
        private String role;
        private String content;
        
        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}