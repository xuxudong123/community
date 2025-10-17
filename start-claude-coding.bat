@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion

:: Claude编码助手启动脚本 - Windows版本
:: 作者: Claude AI Assistant
:: 版本: 1.0.0

echo 🚀 Claude编码助手启动脚本 (Windows)
echo ================================

:: 检查Java环境
echo 检查Java环境...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ 未找到Java环境，请先安装Java 8或更高版本
    pause
    exit /b 1
) else (
    echo ✅ Java环境检查通过
)

:: 检查Maven环境
echo 检查Maven环境...
mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ⚠️  未找到Maven，尝试使用项目自带的Maven Wrapper
    if exist "mvnw.cmd" (
        echo ✅ 找到Maven Wrapper
        set MVN_CMD=mvnw.cmd
    ) else (
        echo ❌ 未找到Maven或Maven Wrapper
        pause
        exit /b 1
    )
) else (
    echo ✅ Maven环境检查通过
    set MVN_CMD=mvn
)

:: 检查配置文件
echo 检查配置文件...
set CONFIG_FILE=src\main\resources\application-dev.properties

if not exist "%CONFIG_FILE%" (
    echo ❌ 配置文件不存在: %CONFIG_FILE%
    pause
    exit /b 1
)

:: 检查API密钥配置
findstr /C:"your_claude_api_key_here" "%CONFIG_FILE%" >nul
if %errorlevel% equ 0 (
    echo ⚠️  请先配置Claude API密钥
    echo    编辑文件: %CONFIG_FILE%
    echo    将 'your_claude_api_key_here' 替换为您的实际API密钥
    echo.
    set /p "configure=是否现在配置API密钥？(y/n): "
    if /i "!configure!"=="y" (
        set /p "api_key=请输入您的Claude API密钥: "
        if not "!api_key!"=="" (
            powershell -Command "(Get-Content '%CONFIG_FILE%') -replace 'your_claude_api_key_here', '!api_key!' | Set-Content '%CONFIG_FILE%'"
            echo ✅ API密钥配置完成
        ) else (
            echo ❌ API密钥不能为空
            pause
            exit /b 1
        )
    ) else (
        echo 请手动配置API密钥后重新运行此脚本
        pause
        exit /b 1
    )
) else (
    echo ✅ 配置文件检查通过
)

:: 检查代理配置
echo 检查代理配置...
netstat -an | findstr ":7890" >nul
if %errorlevel% equ 0 (
    echo ✅ 发现代理服务运行在端口 7890
) else (
    netstat -an | findstr ":1080" >nul
    if %errorlevel% equ 0 (
        echo ✅ 发现代理服务运行在端口 1080
    ) else (
        echo ⚠️  未检测到常见代理服务
        echo    如果您在中国大陆，建议启动VPN或代理服务
        echo    常见代理端口: 7890(Clash), 1080(V2Ray), 8080(HTTP代理)
        echo.
        set /p "continue=是否继续启动？(y/n): "
        if /i not "!continue!"=="y" (
            exit /b 1
        )
    )
)

:: 编译项目
echo 编译项目...
echo 执行: %MVN_CMD% clean compile
%MVN_CMD% clean compile -q
if %errorlevel% neq 0 (
    echo ❌ 项目编译失败
    pause
    exit /b 1
) else (
    echo ✅ 项目编译成功
)

:: 显示使用提示
echo.
echo 🎉 Claude编码助手使用提示:
echo 1. 代码生成: 描述您的需求，AI将生成相应代码
echo 2. 代码审查: 粘贴代码，获得专业的改进建议  
echo 3. 代码解释: 理解复杂代码的功能和原理
echo.
echo 支持的编程语言:
echo Java, Python, JavaScript, TypeScript, C++, Go, Rust
echo.
echo 注意事项:
echo - 确保API密钥有效且有足够余额
echo - 中国用户建议使用代理以确保稳定连接
echo - 不要发送包含敏感信息的代码
echo.

:: 启动应用
echo 启动Claude编码助手...
echo 应用启动后，请访问: http://localhost:8887/claude/coding
echo 按 Ctrl+C 停止应用
echo ================================
echo.

%MVN_CMD% spring-boot:run

pause