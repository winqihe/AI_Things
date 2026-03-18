# Spring AI Skills Demo

基于 Spring AI 的函数调用技能演示项目，展示如何将 Java 方法注册为 AI 可调用的工具。

## 📋 目录

- [项目简介](#项目简介)
- [核心特性](#核心特性)
- [技术栈](#技术栈)
- [快速开始](#快速开始)
- [项目结构](#项目结构)
- [可用技能](#可用技能)
- [API 使用](#api-使用)
- [添加新技能](#添加新技能)
- [配置说明](#配置说明)

## 项目简介

这是一个使用 Spring AI 框架构建的演示项目，展示了如何将 Java 方法注册为 AI 可调用的函数工具。当用户的问题需要特定信息（如天气、数学计算、时间查询）时，AI 会自动调用相应的技能方法来获取答案。

## 核心特性

- 🤖 **AI 函数调用** - 自动识别并调用合适的技能方法
- 🧩 **模块化技能** - 每个功能独立封装为 Skill 类
- 🌐 **RESTful API** - 提供 HTTP 接口与 AI 交互
- ⚡ **零配置注册** - 使用 `@Service` 注解自动注册
- 📝 **中文支持** - 针对中文场景优化
- 🔧 **灵活配置** - 支持 OpenAI 及兼容服务

## 技术栈

- **Spring Boot 3.2** - 应用框架
- **Spring AI 1.0** - AI 集成框架
- **Groq / OpenAI API** - 大语言模型（项目默认配置 Groq）
- **Maven** - 项目构建工具
- **Java 17** - 编程语言

## 快速开始

### 前置要求

- Java 17+
- Maven 3.6+
- Groq API Key 或 OpenAI API Key（项目默认配置 Groq）

### 配置 API Key

**方式一：环境变量（推荐）**
```bash
# 使用 Groq API Key
export OPENAI_API_KEY=gsk-your-groq-api-key-here

# 或使用 OpenAI API Key
# export OPENAI_API_KEY=sk-your-openai-api-key-here
```

**方式二：创建 .env 文件**
```bash
cp .env.example .env
# 编辑 .env 文件，填入你的 API Key
```

**方式三：修改 application.yml**
```yaml
spring:
  ai:
    openai:
      api-key: gsk-your-groq-api-key-here
```

**获取 Groq API Key：**
1. 访问 https://console.groq.com/
2. 登录或注册账号
3. 进入 API Keys 页面创建新的 API Key

### 构建运行

```bash
# 克隆或下载项目
cd springai-skills-demo

# 构建
mvn clean install

# 运行
mvn spring-boot:run
```

### 验证运行

访问 `http://localhost:8080/api/chat?message=你好`，应该看到 AI 的回复。

## 项目结构

```
springai-skills-demo/
├── pom.xml                                    # Maven 配置
├── .env.example                               # 环境变量示例
├── README.md                                  # 项目文档
└── src/
    └── main/
        ├── java/com/example/springai/
        │   ├── SpringAiSkillsApplication.java     # 启动类
        │   ├── controller/
        │   │   └── ChatController.java            # 聊天控制器
        │   ├── dto/
        │   │   ├── ChatRequest.java               # 请求 DTO
        │   │   └── ChatResponse.java              # 响应 DTO
        │   └── skills/
        │       ├── WeatherSkill.java              # 天气技能
        │       ├── CalculatorSkill.java           # 计算技能
        │       └── TimeSkill.java                # 时间技能
        └── resources/
            └── application.yml                    # 应用配置
```

## 可用技能

### 天气技能 (WeatherSkill)

| 方法 | 功能 | 参数 |
|------|------|------|
| `getWeather` | 获取城市天气 | `city: String` |
| `getForecast` | 获取未来天气预报 | `city: String, days: int` |

**示例：**
```
用户：北京今天天气怎么样？
AI 调用：getWeather("北京")
返回：北京的天气：晴天，温度 25°C
```

### 计算技能 (CalculatorSkill)

| 方法 | 功能 | 参数 |
|------|------|------|
| `add` | 加法 | `a: double, b: double` |
| `subtract` | 减法 | `a: double, b: double` |
| `multiply` | 乘法 | `a: double, b: double` |
| `divide` | 除法 | `a: double, b: double` |
| `calculateCircleArea` | 计算圆面积 | `radius: double` |

**示例：**
```
用户：计算 25 加上 37 等于多少？
AI 调用：add(25, 37)
返回：62.0
```

### 时间技能 (TimeSkill)

| 方法 | 功能 |
|------|------|
| `getCurrentTime` | 获取当前时间 |
| `getCurrentDate` | 获取当前日期 |
| `getDayOfWeek` | 获取星期几 |

**示例：**
```
用户：现在几点了？
AI 调用：getCurrentTime()
返回：当前时间：2025年03月16日 14:30:45
    ```

## API 使用

### POST /api/chat

发送聊天消息（推荐）

使用 cURL：
```bash
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "北京今天天气怎么样？"}'
```

使用 JavaScript fetch：
```javascript
fetch('http://localhost:8080/api/chat', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ message: '北京今天天气怎么样？' })
})
.then(res => res.json())
.then(data => console.log(data.response));
```

### GET /api/chat

简化接口（快速测试）

```bash
curl "http://localhost:8080/api/chat?message=计算15乘以3"
```

## 添加新技能

### 第一步：创建 Skill 类

```java
package com.example.springai.skills;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

@Service  // 自动注册为 Spring Bean
@Description("股票查询服务")
public class StockSkill {

    @Description("获取股票的当前价格")
    public double getStockPrice(String symbol) {
        // 调用股票 API 或数据库
        return 128.5;
    }

    @Description("获取股票的公司名称")
    public String getCompanyName(String symbol) {
        return "示例公司";
    }
}
```

### 第二步：在 ChatController 中注册

编辑 `ChatController.java`，在 `defaultFunctions()` 中添加方法名：

```java
public ChatController(ChatClient.Builder chatClientBuilder) {
    this.chatClient = chatClientBuilder
            .defaultFunctions("getWeather", "getForecast",
                    "add", "subtract", "multiply", "divide",
                    "calculateCircleArea", "getCurrentTime",
                    "getCurrentDate", "getDayOfWeek",
                    "getStockPrice", "getCompanyName")  // 新增
            .build();
}
```

完成！你的新技能已经可以使用了。

## 配置说明

### application.yml

```yaml
spring:
  ai:
    openai:
      api-key: ${OPENAI_API_KEY}  # 从环境变量读取
      # base-url: https://api.openai.com  # 自定义 API endpoint
      # chat:
      #   options:
      #     model: gpt-4          # 模型选择
      #     temperature: 0.7       # 温度参数

server:
  port: 8080  # 服务端口
```

### 使用兼容 OpenAI 的服务

如果你想使用兼容 OpenAI API 的服务（如 Azure OpenAI、本地模型等）：

```yaml
spring:
  ai:
    openai:
      api-key: your-api-key
      base-url: https://your-endpoint.com
```

### Groq 模型选择

项目默认使用 Groq，可用的模型包括：

| 模型 | 说明 |
|------|------|
| `llama3-8b-8192` | Llama 3 8B（默认，速度快） |
| `llama3-70b-8192` | Llama 3 70B（更强能力） |
| `mixtral-8x7b-32768` | Mixtral 8x7B（长上下文） |
| `gemma-7b-it` | Gemma 7B 指令微调版 |

切换模型（修改 `application.yml`）：
```yaml
spring:
  ai:
    openai:
      chat:
        options:
          model: llama3-70b-8192
```

## 常见问题

### Q: 提示 "Invalid API Key"

A: 请检查 API Key 是否正确配置，环境变量优先级最高。

### Q: 如何使用不同的模型？

A: 在 `application.yml` 中配置：

Groq 模型：
```yaml
spring:
  ai:
    openai:
      chat:
        options:
          model: llama3-70b-8192
```

OpenAI 模型（需要修改 base-url）：
```yaml
spring:
  ai:
    openai:
      base-url: https://api.openai.com
      chat:
        options:
          model: gpt-4
```

### Q: 如何添加更多参数验证？

A: 在技能方法中添加参数校验：
```java
public double divide(double a, double b) {
    if (b == 0) {
        throw new IllegalArgumentException("除数不能为零");
    }
    return a / b;
}
```

## License

MIT
