package com.xudong.community.dto;

import lombok.Data;

/**
 * 代码生成请求DTO
 */
@Data
public class CodeGenerationRequest {
    private String prompt;
    private String language;
    private String framework;
    private String description;
    private boolean includeComments = true;
    private boolean includeTests = false;
}