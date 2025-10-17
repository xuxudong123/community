# Claude编码助手 - 快速开始指南

## 🎯 一分钟快速启动

### 1. 获取Claude API密钥
- 访问 [Anthropic官网](https://www.anthropic.com/) （需要VPN）
- 注册并获取API密钥（格式：sk-ant-xxx）

### 2. 配置API密钥
编辑 `src/main/resources/application-dev.properties`，替换：
```properties
claude.api.key=your_claude_api_key_here
```
为您的实际API密钥：
```properties
claude.api.key=sk-ant-your_actual_key_here
```

### 3. 配置代理（中国用户必需）
确保您的代理软件正在运行，然后根据实际情况修改：
```properties
# 代理配置
claude.proxy.enabled=true
claude.proxy.host=127.0.0.1
claude.proxy.port=7890  # Clash默认端口
claude.proxy.type=HTTP
```

常见代理端口：
- Clash: 7890
- V2Ray: 1080
- Shadowsocks: 1080

### 4. 一键启动

**Linux/macOS:**
```bash
./start-claude-coding.sh
```

**Windows:**
```cmd
start-claude-coding.bat
```

**手动启动:**
```bash
./mvnw spring-boot:run
```

### 5. 开始使用
打开浏览器访问：`http://localhost:8887/claude/coding`

## 🚀 功能演示

### 代码生成示例
**输入：**
```
创建一个Spring Boot REST接口，实现用户登录功能，包括JWT令牌生成
```

**输出：**
完整的Controller、Service、DTO类和JWT工具类代码

### 代码审查示例
**输入代码：**
```java
public class UserService {
    public User login(String username, String password) {
        // 直接比较密码
        if (password.equals("123456")) {
            return new User(username);
        }
        return null;
    }
}
```

**输出建议：**
- 安全问题：明文密码比较
- 性能问题：硬编码密码
- 最佳实践：使用密码加密、异常处理等

## 🔧 故障排除

### 常见问题

1. **"Claude API连接失败"**
   - 检查API密钥是否正确
   - 确认代理软件正在运行
   - 测试代理连接：`curl --proxy http://127.0.0.1:7890 https://www.google.com`

2. **"编译失败"**
   - 确保Java版本兼容（推荐Java 8+）
   - 运行：`./mvnw clean compile`

3. **"端口被占用"**
   - 修改 `application-dev.properties` 中的端口：
   ```properties
   server.port=8888
   ```

### 调试模式
启用详细日志：
```properties
logging.level.com.xudong.community.service.ClaudeService=DEBUG
```

## 📞 获取帮助

1. 查看详细文档：[CLAUDE_SETUP_GUIDE_CN.md](CLAUDE_SETUP_GUIDE_CN.md)
2. 检查应用日志：`logs/community.log`
3. 测试API连接：访问 `http://localhost:8887/claude/status`

## 🎉 开始编码！

配置完成后，您就可以享受AI辅助编程的强大功能了：
- 🤖 智能代码生成
- 🔍 专业代码审查  
- 💡 详细代码解释
- 🌐 中国网络优化

祝您编码愉快！🚀