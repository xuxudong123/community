## 咚咚社区 - 集成Claude AI编码助手

这是一个基于Spring Boot的社区项目，现已集成Claude AI编码助手，为中国开发者提供智能编程辅助。

## 🚀 新功能：Claude编码助手

### 功能特性
- **代码生成**: 根据自然语言描述生成高质量代码
- **代码审查**: 智能分析代码质量、性能和安全性
- **代码解释**: 详细解释复杂代码的功能和工作原理
- **多语言支持**: Java、Python、JavaScript、TypeScript、C++、Go、Rust
- **中国用户优化**: 内置代理支持，解决网络访问问题

### 快速开始

#### 方法一：使用启动脚本（推荐）

**Linux/macOS用户：**
```bash
./start-claude-coding.sh
```

**Windows用户：**
```cmd
start-claude-coding.bat
```

#### 方法二：手动配置

1. **获取Claude API密钥**
   - 访问 [Anthropic官网](https://www.anthropic.com/) 申请API密钥
   - 需要VPN访问

2. **配置API密钥**
   - 编辑 `src/main/resources/application-dev.properties`
   - 将 `your_claude_api_key_here` 替换为您的实际API密钥

3. **配置代理（中国用户必需）**
   ```properties
   # 代理配置
   claude.proxy.enabled=true
   claude.proxy.host=127.0.0.1
   claude.proxy.port=7890  # 根据您的代理端口调整
   claude.proxy.type=HTTP
   ```

4. **启动应用**
   ```bash
   mvn spring-boot:run
   ```

5. **访问编码助手**
   - 打开浏览器访问：`http://localhost:8887/claude/coding`

### 使用示例

#### 代码生成示例
```
需求描述：创建一个Spring Boot REST API，实现用户注册功能，包括邮箱验证和密码加密

生成的代码将包括：
- Controller层接口
- Service层业务逻辑  
- 数据验证
- 密码加密
- 异常处理
```

#### 代码审查示例
粘贴您的Java代码，AI将提供：
- 代码质量评估
- 性能优化建议
- 安全漏洞检测
- 最佳实践建议

### 技术架构

```
前端界面 (Thymeleaf + Bootstrap)
    ↓
Spring Boot Controller
    ↓  
Claude Service (OkHttp + 代理支持)
    ↓
Claude API (通过代理访问)
```

### 中国用户网络配置

由于Claude API服务器位于海外，中国用户需要配置代理：

1. **推荐代理工具**
   - Clash (端口通常为7890)
   - V2Ray (端口通常为1080) 
   - Shadowsocks
   - 商业VPN服务

2. **代理测试**
   ```bash
   # 测试代理连接
   curl --proxy http://127.0.0.1:7890 https://api.anthropic.com
   ```

### 故障排除

详细的配置指南和故障排除，请参考：[CLAUDE_SETUP_GUIDE_CN.md](CLAUDE_SETUP_GUIDE_CN.md)

## 原有功能

### 社区功能
- 用户注册/登录（GitHub OAuth）
- 问题发布和回答
- 评论系统
- 通知功能
- 文件上传（UCloud对象存储）

### 技术栈
- Spring Boot 2.2.5
- MyBatis
- MySQL
- Thymeleaf
- Bootstrap
- UCloud对象存储

## 贡献

欢迎提交Issue和Pull Request来改进Claude编码助手功能！