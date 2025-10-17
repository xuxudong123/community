# Claude编码助手配置指南 - 中国用户专用

## 🌟 概述

本指南将帮助中国地区的开发者在Spring Boot项目中集成Claude AI编码助手。由于网络限制，我们提供了完整的代理配置方案。

## 🚀 功能特性

- **代码生成**: 根据需求描述自动生成高质量代码
- **代码审查**: 智能分析代码质量、性能和安全性
- **代码解释**: 详细解释复杂代码的功能和原理
- **多语言支持**: Java、Python、JavaScript、TypeScript等
- **代理支持**: 专为中国用户设计的网络代理配置

## 📋 前置要求

### 1. Claude API密钥获取

1. 访问 [Anthropic官网](https://www.anthropic.com/)（需要VPN）
2. 注册账户并申请API访问权限
3. 获取API密钥（格式：sk-ant-xxx）

### 2. 网络代理设置

由于Claude API服务器在海外，中国用户需要配置代理：

#### 推荐代理工具：
- **Clash**: 支持HTTP/SOCKS5代理
- **V2Ray**: 高性能代理工具
- **Shadowsocks**: 轻量级代理
- **商业VPN**: ExpressVPN、NordVPN等

#### 获取代理信息：
- 代理服务器地址（如：127.0.0.1）
- 代理端口（如：7890）
- 代理类型（HTTP/SOCKS5）

## ⚙️ 配置步骤

### 1. 修改配置文件

编辑 `src/main/resources/application-dev.properties`：

```properties
# Claude API 配置
claude.api.key=sk-ant-your_actual_api_key_here
claude.api.base-url=https://api.anthropic.com
claude.api.model=claude-3-sonnet-20240229
claude.api.max-tokens=4000
claude.api.temperature=0.7

# 代理配置（根据你的实际代理设置修改）
claude.proxy.enabled=true
claude.proxy.host=127.0.0.1
claude.proxy.port=7890
claude.proxy.type=HTTP
```

### 2. 常见代理配置示例

#### Clash代理配置：
```properties
claude.proxy.enabled=true
claude.proxy.host=127.0.0.1
claude.proxy.port=7890
claude.proxy.type=HTTP
```

#### V2Ray代理配置：
```properties
claude.proxy.enabled=true
claude.proxy.host=127.0.0.1
claude.proxy.port=1080
claude.proxy.type=HTTP
```

#### 如果不使用代理：
```properties
claude.proxy.enabled=false
```

### 3. 启动应用

```bash
# 使用Maven启动
mvn spring-boot:run

# 或者使用包装器
./mvnw spring-boot:run
```

### 4. 访问Claude编码助手

打开浏览器访问：`http://localhost:8887/claude/coding`

## 🔧 使用指南

### 代码生成功能

1. 选择编程语言（Java、Python等）
2. 输入框架名称（可选）
3. 详细描述代码需求
4. 选择是否包含注释和测试
5. 点击"生成代码"

**示例需求描述：**
```
创建一个用户注册接口，包括：
- 接收用户名、邮箱、密码
- 验证邮箱格式
- 密码加密存储
- 返回注册结果
```

### 代码审查功能

1. 选择代码语言
2. 粘贴需要审查的代码
3. 点击"审查代码"
4. 查看改进建议

### 代码解释功能

1. 选择代码语言
2. 粘贴需要解释的代码
3. 点击"解释代码"
4. 查看详细解释

## 🛠️ 故障排除

### 常见问题

#### 1. API连接失败
**错误信息**: "Claude API连接失败"

**解决方案**:
- 检查API密钥是否正确
- 确认代理服务正常运行
- 验证代理配置参数
- 检查防火墙设置

#### 2. 代理连接超时
**错误信息**: "连接超时"

**解决方案**:
```properties
# 增加超时时间
claude.api.timeout=60000
```

#### 3. 403 Forbidden错误
**解决方案**:
- 检查API密钥权限
- 确认账户余额充足
- 验证API调用频率限制

#### 4. 代理配置错误
**检查步骤**:
1. 确认代理软件正在运行
2. 测试代理连接：`curl --proxy http://127.0.0.1:7890 https://www.google.com`
3. 检查代理端口是否被占用

### 调试模式

启用详细日志：

```properties
# 启用调试日志
logging.level.com.xudong.community.service.ClaudeService=DEBUG
logging.level.okhttp3=DEBUG
```

## 🔒 安全建议

1. **API密钥安全**:
   - 不要将API密钥提交到代码仓库
   - 使用环境变量存储敏感信息
   - 定期轮换API密钥

2. **网络安全**:
   - 使用可信的代理服务
   - 避免在公共网络下使用
   - 定期更新代理配置

3. **数据保护**:
   - 不要发送敏感代码到Claude API
   - 注意代码中的商业机密
   - 遵守公司安全政策

## 📞 技术支持

如果遇到问题，请检查：

1. **配置文件**: 确认所有参数正确
2. **网络连接**: 测试代理和API连接
3. **日志文件**: 查看详细错误信息
4. **API状态**: 检查Anthropic服务状态

## 🎯 最佳实践

1. **提示词优化**:
   - 使用清晰、具体的描述
   - 提供足够的上下文信息
   - 指定期望的代码风格

2. **性能优化**:
   - 合理设置max_tokens参数
   - 根据需求调整temperature值
   - 使用缓存减少API调用

3. **成本控制**:
   - 监控API使用量
   - 优化提示词长度
   - 设置合理的超时时间

## 📝 更新日志

- **v1.0.0**: 初始版本，支持基本的代码生成、审查和解释功能
- **v1.0.1**: 添加代理支持，优化中国用户体验
- **v1.0.2**: 增加错误处理和调试功能

---

**注意**: 本工具仅供学习和开发使用，请遵守相关法律法规和服务条款。