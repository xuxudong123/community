#!/bin/bash

# Claude编码助手启动脚本 - 中国用户专用
# 作者: Claude AI Assistant
# 版本: 1.0.0

echo "🚀 Claude编码助手启动脚本"
echo "================================"

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# 检查Java环境
check_java() {
    echo -e "${BLUE}检查Java环境...${NC}"
    if command -v java &> /dev/null; then
        JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
        echo -e "${GREEN}✓ Java已安装: $JAVA_VERSION${NC}"
    else
        echo -e "${RED}✗ 未找到Java环境，请先安装Java 8或更高版本${NC}"
        exit 1
    fi
}

# 检查Maven环境
check_maven() {
    echo -e "${BLUE}检查Maven环境...${NC}"
    if command -v mvn &> /dev/null; then
        MVN_VERSION=$(mvn -version | head -n 1)
        echo -e "${GREEN}✓ Maven已安装: $MVN_VERSION${NC}"
    else
        echo -e "${YELLOW}! 未找到Maven，尝试使用项目自带的Maven Wrapper${NC}"
        if [ -f "./mvnw" ]; then
            echo -e "${GREEN}✓ 找到Maven Wrapper${NC}"
            MVN_CMD="./mvnw"
        else
            echo -e "${RED}✗ 未找到Maven或Maven Wrapper${NC}"
            exit 1
        fi
    fi
}

# 检查配置文件
check_config() {
    echo -e "${BLUE}检查配置文件...${NC}"
    CONFIG_FILE="src/main/resources/application-dev.properties"
    
    if [ ! -f "$CONFIG_FILE" ]; then
        echo -e "${RED}✗ 配置文件不存在: $CONFIG_FILE${NC}"
        exit 1
    fi
    
    # 检查API密钥配置
    if grep -q "your_claude_api_key_here" "$CONFIG_FILE"; then
        echo -e "${YELLOW}⚠️  请先配置Claude API密钥${NC}"
        echo -e "${YELLOW}   编辑文件: $CONFIG_FILE${NC}"
        echo -e "${YELLOW}   将 'your_claude_api_key_here' 替换为您的实际API密钥${NC}"
        
        read -p "是否现在配置API密钥？(y/n): " -n 1 -r
        echo
        if [[ $REPLY =~ ^[Yy]$ ]]; then
            read -p "请输入您的Claude API密钥: " API_KEY
            if [ ! -z "$API_KEY" ]; then
                # 使用sed替换API密钥
                if [[ "$OSTYPE" == "darwin"* ]]; then
                    # macOS
                    sed -i '' "s/your_claude_api_key_here/$API_KEY/g" "$CONFIG_FILE"
                else
                    # Linux
                    sed -i "s/your_claude_api_key_here/$API_KEY/g" "$CONFIG_FILE"
                fi
                echo -e "${GREEN}✓ API密钥配置完成${NC}"
            else
                echo -e "${RED}✗ API密钥不能为空${NC}"
                exit 1
            fi
        else
            echo -e "${YELLOW}请手动配置API密钥后重新运行此脚本${NC}"
            exit 1
        fi
    else
        echo -e "${GREEN}✓ 配置文件检查通过${NC}"
    fi
}

# 检查代理配置
check_proxy() {
    echo -e "${BLUE}检查代理配置...${NC}"
    
    # 检查常见代理端口
    COMMON_PORTS=(7890 1080 8080 10809)
    PROXY_FOUND=false
    
    for port in "${COMMON_PORTS[@]}"; do
        if nc -z 127.0.0.1 $port 2>/dev/null; then
            echo -e "${GREEN}✓ 发现代理服务运行在端口 $port${NC}"
            PROXY_FOUND=true
            break
        fi
    done
    
    if [ "$PROXY_FOUND" = false ]; then
        echo -e "${YELLOW}⚠️  未检测到常见代理服务${NC}"
        echo -e "${YELLOW}   如果您在中国大陆，建议启动VPN或代理服务${NC}"
        echo -e "${YELLOW}   常见代理端口: 7890(Clash), 1080(V2Ray), 8080(HTTP代理)${NC}"
        
        read -p "是否继续启动？(y/n): " -n 1 -r
        echo
        if [[ ! $REPLY =~ ^[Yy]$ ]]; then
            exit 1
        fi
    fi
}

# 测试网络连接
test_network() {
    echo -e "${BLUE}测试网络连接...${NC}"
    
    # 测试是否能访问Claude API
    if curl -s --connect-timeout 5 https://api.anthropic.com > /dev/null; then
        echo -e "${GREEN}✓ 可以直接访问Claude API${NC}"
    else
        echo -e "${YELLOW}⚠️  无法直接访问Claude API，请确保代理正常工作${NC}"
    fi
}

# 编译项目
compile_project() {
    echo -e "${BLUE}编译项目...${NC}"
    
    if [ -z "$MVN_CMD" ]; then
        MVN_CMD="mvn"
    fi
    
    echo "执行: $MVN_CMD clean compile"
    if $MVN_CMD clean compile -q; then
        echo -e "${GREEN}✓ 项目编译成功${NC}"
    else
        echo -e "${RED}✗ 项目编译失败${NC}"
        exit 1
    fi
}

# 启动应用
start_application() {
    echo -e "${BLUE}启动Claude编码助手...${NC}"
    echo -e "${GREEN}应用启动后，请访问: http://localhost:8887/claude/coding${NC}"
    echo -e "${YELLOW}按 Ctrl+C 停止应用${NC}"
    echo "================================"
    
    if [ -z "$MVN_CMD" ]; then
        MVN_CMD="mvn"
    fi
    
    $MVN_CMD spring-boot:run
}

# 显示使用提示
show_usage_tips() {
    echo -e "${GREEN}🎉 Claude编码助手使用提示:${NC}"
    echo "1. 代码生成: 描述您的需求，AI将生成相应代码"
    echo "2. 代码审查: 粘贴代码，获得专业的改进建议"
    echo "3. 代码解释: 理解复杂代码的功能和原理"
    echo ""
    echo -e "${BLUE}支持的编程语言:${NC}"
    echo "Java, Python, JavaScript, TypeScript, C++, Go, Rust"
    echo ""
    echo -e "${YELLOW}注意事项:${NC}"
    echo "- 确保API密钥有效且有足够余额"
    echo "- 中国用户建议使用代理以确保稳定连接"
    echo "- 不要发送包含敏感信息的代码"
    echo ""
}

# 主函数
main() {
    echo "开始检查环境..."
    
    check_java
    check_maven
    check_config
    check_proxy
    test_network
    compile_project
    
    show_usage_tips
    
    echo -e "${GREEN}所有检查完成，准备启动应用...${NC}"
    sleep 2
    
    start_application
}

# 脚本入口
main "$@"