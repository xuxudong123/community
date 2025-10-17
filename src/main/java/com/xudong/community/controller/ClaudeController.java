package com.xudong.community.controller;

import com.xudong.community.dto.CodeGenerationRequest;
import com.xudong.community.dto.ResultDTO;
import com.xudong.community.service.ClaudeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Claude编码助手控制器
 * 为中国用户提供AI编码辅助功能
 */
@Slf4j
@Controller
@RequestMapping("/claude")
public class ClaudeController {
    
    @Autowired
    private ClaudeService claudeService;
    
    /**
     * 显示Claude编码助手页面
     */
    @GetMapping("/coding")
    public String codingPage(Model model) {
        model.addAttribute("title", "Claude编码助手");
        return "claude-coding";
    }
    
    /**
     * 生成代码API
     */
    @PostMapping("/generate")
    @ResponseBody
    public ResultDTO<String> generateCode(@RequestBody CodeGenerationRequest request) {
        try {
            log.info("收到代码生成请求: {}", request.getPrompt());
            
            // 验证输入
            if (request.getPrompt() == null || request.getPrompt().trim().isEmpty()) {
                return ResultDTO.errorOf("请输入代码生成需求");
            }
            
            if (request.getLanguage() == null || request.getLanguage().trim().isEmpty()) {
                request.setLanguage("Java"); // 默认使用Java
            }
            
            String generatedCode = claudeService.generateCode(request);
            
            log.info("代码生成成功，长度: {}", generatedCode.length());
            return ResultDTO.okOf(generatedCode);
            
        } catch (IOException e) {
            log.error("代码生成失败", e);
            return ResultDTO.errorOf("代码生成失败: " + e.getMessage());
        } catch (Exception e) {
            log.error("系统错误", e);
            return ResultDTO.errorOf("系统错误，请稍后重试");
        }
    }
    
    /**
     * 代码审查API
     */
    @PostMapping("/review")
    @ResponseBody
    public ResultDTO<String> reviewCode(@RequestParam String code, 
                                       @RequestParam(defaultValue = "Java") String language) {
        try {
            log.info("收到代码审查请求，语言: {}", language);
            
            if (code == null || code.trim().isEmpty()) {
                return ResultDTO.errorOf("请输入要审查的代码");
            }
            
            String review = claudeService.reviewCode(code, language);
            
            log.info("代码审查完成");
            return ResultDTO.okOf(review);
            
        } catch (IOException e) {
            log.error("代码审查失败", e);
            return ResultDTO.errorOf("代码审查失败: " + e.getMessage());
        } catch (Exception e) {
            log.error("系统错误", e);
            return ResultDTO.errorOf("系统错误，请稍后重试");
        }
    }
    
    /**
     * 代码解释API
     */
    @PostMapping("/explain")
    @ResponseBody
    public ResultDTO<String> explainCode(@RequestParam String code,
                                        @RequestParam(defaultValue = "Java") String language) {
        try {
            log.info("收到代码解释请求，语言: {}", language);
            
            if (code == null || code.trim().isEmpty()) {
                return ResultDTO.errorOf("请输入要解释的代码");
            }
            
            String explanation = claudeService.explainCode(code, language);
            
            log.info("代码解释完成");
            return ResultDTO.okOf(explanation);
            
        } catch (IOException e) {
            log.error("代码解释失败", e);
            return ResultDTO.errorOf("代码解释失败: " + e.getMessage());
        } catch (Exception e) {
            log.error("系统错误", e);
            return ResultDTO.errorOf("系统错误，请稍后重试");
        }
    }
    
    /**
     * 检查Claude API连接状态
     */
    @GetMapping("/status")
    @ResponseBody
    public ResultDTO<String> checkStatus() {
        try {
            // 发送一个简单的测试请求
            CodeGenerationRequest testRequest = new CodeGenerationRequest();
            testRequest.setPrompt("输出Hello World");
            testRequest.setLanguage("Java");
            
            claudeService.generateCode(testRequest);
            
            return ResultDTO.okOf("Claude API连接正常");
            
        } catch (Exception e) {
            log.error("Claude API连接检查失败", e);
            return ResultDTO.errorOf("Claude API连接失败: " + e.getMessage());
        }
    }
}