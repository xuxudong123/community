package com.xudong.community.dto;

import lombok.Data;
import java.util.List;

/**
 * Claude API响应DTO
 */
@Data
public class ClaudeResponse {
    private String id;
    private String type;
    private String role;
    private List<Content> content;
    private String model;
    private String stop_reason;
    private String stop_sequence;
    private Usage usage;
    
    @Data
    public static class Content {
        private String type;
        private String text;
    }
    
    @Data
    public static class Usage {
        private Integer input_tokens;
        private Integer output_tokens;
    }
}