# Skills Agent MVP

一个基于技能注册表模式的轻量级代理系统，能够将用户请求路由到专门的技能模块进行处理。

## 📋 目录

- [项目简介](#项目简介)
- [核心特性](#核心特性)
- [快速开始](#快速开始)
- [使用示例](#使用示例)
- [项目结构](#项目结构)
- [架构设计](#架构设计)
- [添加新技能](#添加新技能)
- [技能接口规范](#技能接口规范)
- [开发指南](#开发指南)

## 项目简介

Skills Agent 是一个最小可行性产品（MVP），展示了如何构建一个模块化的代理系统。它通过技能注册表模式，将不同功能解耦为独立的技能模块，易于扩展和维护。

## 核心特性

- 🧩 **模块化技能系统** - 每个技能都是独立模块，互不干扰
- 📝 **集中式技能管理** - 通过注册表统一管理所有技能
- 🖥️ **交互式 CLI 界面** - 友好的命令行交互体验
- 🚀 **零外部依赖** - 纯 Node.js 实现，无需安装额外依赖
- ⚡ **异步支持** - 技能支持异步操作（如 API 调用）
- 🔄 **易于扩展** - 添加新技能只需三步

## 快速开始

### 前置要求

- Node.js >= 18

### 安装

```bash
cd skills_project
npm install
```

### 运行

```bash
npm start
```

## 使用示例

启动后，你可以在 CLI 中输入以下命令：

### 问候
```
hello
hi there
good morning
```
**输出：** 随机返回友好的问候语

### 时间查询
```
what time is it?
what day is today?
current time
```
**输出：** 格式化的当前日期和时间

### 数学计算
```
2 + 2
10 * 5
100 / 4
3.5 * 2
```
**输出：** 计算结果

### 查看帮助
```
help
```
**输出：** 显示所有可用技能及其描述

### 退出
```
exit
quit
```

## 项目结构

```
skills_project/
├── index.js               # CLI 入口，提供交互式界面
├── agent.js               # 核心代理逻辑
├── package.json            # 项目配置文件
├── README.md              # 项目文档（本文件）
└── skills/                # 技能目录
    ├── registry.js        # 技能注册表
    ├── greeting.js        # 问候技能
    ├── time.js            # 时间技能
    └── calculate.js       # 计算技能
```

## 架构设计

### 工作流程

```
用户输入
    ↓
Agent.process(input)
    ↓
查找匹配的技能 (findSkill)
    ↓
skill.execute(input)
    ↓
返回结果
```

### 核心组件

#### Agent (代理核心)
- 管理所有注册的技能
- 将用户请求路由到合适的技能
- 支持技能优先级（按注册顺序）

#### Skill Registry (技能注册表)
- 使用 Map 数据结构存储技能
- 提供统一的技能访问接口

#### Skill (技能)
- 每个技能实现特定的功能
- 通过 `canHandle()` 判断是否能处理请求
- 通过 `execute()` 执行具体逻辑

## 添加新技能

### 第一步：创建技能文件

在 `skills/` 目录下创建新文件，例如 `skills/weather.js`：

```javascript
/**
 * Weather Skill
 * 获取天气信息
 */

export const weatherSkill = {
  name: 'weather',
  description: '获取当前天气信息',

  // 判断是否能处理该输入
  canHandle(input) {
    const keywords = ['weather', '天气预报', '天气'];
    const lowerInput = input.toLowerCase();
    return keywords.some(keyword => lowerInput.includes(keyword));
  },

  // 执行技能逻辑
  async execute(input) {
    // 这里可以调用天气 API
    return '北京：晴，温度 22°C';
  }
};
```

### 第二步：在注册表中导入

编辑 `skills/registry.js`：

```javascript
import {w greetingSkill } from './greeting.js';
import { timeSkill } from './time.js';
import { calculateSkill } from './calculate.js';
import { weatherSkill } from './weather.js';  // 新增
```

### 第三步：注册技能

在 `skills/registry.js` 的 Map 中添加：

```javascript
export const skillRegistry = new Map([
  ['greeting', greetingSkill],
  ['time', timeSkill],
  ['calculate', calculateSkill],
  ['weather', weatherSkill],  // 新增
]);
```

完成！你的新技能已经可以使用了。

## 技能接口规范

每个技能必须实现以下接口：

```typescript
{
  name: string,                  // 技能名称（唯一标识）
  description: string,           // 技能描述
  canHandle(input: string): boolean,   // 判断是否能处理该输入
  execute(input: string): Promise<string>  // 执行技能逻辑
}
```

### 接口说明

| 方法 | 参数 | 返回值 | 说明 |
|------|------|--------|------|
| `canHandle` | `input: string` | `boolean` | 判断该技能是否能处理用户的输入 |
| `execute` | `input: string` | `Promise<string>` | 执行技能逻辑，返回响应 |

## 开发指南

### 常见模式

#### 关键词匹配

```javascript
canHandle(input) {
  return input.toLowerCase().includes('keyword');
}
```

#### 正则表达式匹配

```javascript
canHandle(input) {
  return /\d+\s*[\+\-\*\/]\s*\d+/.test(input);
}
```

#### 多关键词匹配

```javascript
canHandle(input) {
  const keywords = ['hello', 'hi', 'hey'];
  const lowerInput = input.toLowerCase();
  return keywords.some(keyword => lowerInput.startsWith(keyword));
}
```

#### 异步 API 调用

```javascript
async execute(input) {
  const response = await fetch('https://api.example.com');
  const data = await response.json();
  return `结果：${data}`;
}
```

### 最佳实践

1. **命名规范** - 技能文件使用小写字母和连字符
2. **注释** - 添加清晰的注释说明技能用途
3. **错误处理** - 在 `execute` 中添加 try-catch 处理错误
4. **异步支持** - 即使是同步操作，也使用 async/await 保持一致性
5. **输入验证** - 在执行前验证输入的有效性

### 技能优先级

技能按在 `registry.js` 中注册的顺序进行匹配。需要更高优先级的技能应该先注册。

```javascript
export const skillRegistry = new Map([
  ['high-priority-skill', highPrioritySkill],  // 优先匹配
  ['low-priority-skill', lowPrioritySkill],    // 后匹配
]);
```

## License

MIT
