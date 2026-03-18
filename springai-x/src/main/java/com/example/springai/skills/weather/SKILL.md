---
name: "weather"
description: "查询指定城市的实时天气信息。触发条件：当用户询问天气、气温、湿度、是否需要带伞、出行建议等问题时使用。"
version: "1.0.0"
---

# Weather Skill

## 触发条件

当用户询问以下问题时自动触发：
- "北京今天天气怎么样？"
- "明天去上海需要带伞吗？"
- "东京的温度是多少？"
- "这周适合户外活动吗？"

## 输入参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| city | string | 是 | 城市名称（中文或英文） |
| country | string | 否 | 国家代码，默认 "CN" |

## 输出格式

```json
{
  "city": "城市名",
  "condition": "天气状况",
  "temperature": 25.5,
  "humidity": 60.0,
  "advice": "出行建议"
}
```

## 执行步骤

1. 接收城市名称和国家代码参数
2. 调用天气 API 获取实时数据
3. 根据天气状况生成出行建议
4. 返回格式化结果

## 示例

**输入**: `city="北京", country="CN"`

**输出**:
```
City: 北京
Weather: Sunny
Temperature: 25.5°C
Humidity: 60%
Advice: Perfect weather for outdoor activities!
```

## 注意事项

- 城市名称需尽量准确
- 建议同时提供国家和城市以提高准确性
- 返回的温度单位为摄氏度
