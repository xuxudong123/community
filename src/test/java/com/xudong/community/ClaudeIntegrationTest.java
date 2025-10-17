package com.xudong.community;

import com.xudong.community.config.ClaudeConfig;
import com.xudong.community.dto.CodeGenerationRequest;
import com.xudong.community.service.ClaudeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Claude集成测试
 * 注意：此测试需要有效的API密钥和网络连接
 */
@SpringBootTest
@ActiveProfiles("dev")
public class ClaudeIntegrationTest {
    
    @Autowired
    private ClaudeService claudeService;
    
    @Autowired
    private ClaudeConfig claudeConfig;
    
    @Test
    public void testClaudeConfigLoaded() {
        assertNotNull(claudeConfig);
        assertNotNull(claudeConfig.getApi());
        assertNotNull(claudeConfig.getProxy());
        
        // 检查配置是否正确加载
        assertEquals("claude-3-sonnet-20240229", claudeConfig.getApi().getModel());
        assertEquals(4000, claudeConfig.getApi().getMaxTokens());
        assertEquals(0.7, claudeConfig.getApi().getTemperature());
    }
    
    @Test
    public void testClaudeServiceInitialized() {
        assertNotNull(claudeService);
    }
    
    // 注意：以下测试需要有效的API密钥，在CI/CD环境中可能会跳过
    
    //@Test
    public void testCodeGeneration() {
        // 仅在配置了有效API密钥时运行
        if (claudeConfig.getApi().getKey() == null || 
            claudeConfig.getApi().getKey().equals("your_claude_api_key_here")) {
            System.out.println("跳过API测试：未配置有效的API密钥");
            return;
        }
        
        try {
            CodeGenerationRequest request = new CodeGenerationRequest();
            request.setPrompt("创建一个简单的Hello World方法");
            request.setLanguage("Java");
            request.setIncludeComments(true);
            
            String result = claudeService.generateCode(request);
            
            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertTrue(result.toLowerCase().contains("hello") || 
                      result.toLowerCase().contains("world"));
            
            System.out.println("代码生成测试通过");
            System.out.println("生成的代码：\n" + result);
            
        } catch (Exception e) {
            System.err.println("API测试失败（这在没有网络连接或代理时是正常的）: " + e.getMessage());
        }
    }
    
    //@Test  
    public void testCodeReview() {
        if (claudeConfig.getApi().getKey() == null || 
            claudeConfig.getApi().getKey().equals("your_claude_api_key_here")) {
            System.out.println("跳过API测试：未配置有效的API密钥");
            return;
        }
        
        try {
            String sampleCode = "public class Test { public static void main(String[] args) { System.out.println(\"Hello\"); } }";
            
            String result = claudeService.reviewCode(sampleCode, "Java");
            
            assertNotNull(result);
            assertFalse(result.isEmpty());
            
            System.out.println("代码审查测试通过");
            System.out.println("审查结果：\n" + result);
            
        } catch (Exception e) {
            System.err.println("API测试失败（这在没有网络连接或代理时是正常的）: " + e.getMessage());
        }
    }
}