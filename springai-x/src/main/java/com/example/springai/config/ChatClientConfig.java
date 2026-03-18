package com.example.springai.config;

import com.example.springai.skills.weather.WeatherSkill;
import com.example.springai.skills.stock.StockSkill;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public FunctionCallback weatherFunctionCallback(WeatherSkill weatherSkill) {
        return FunctionCallback.builder()
            .function("weatherSkill", weatherSkill)
            .description("查询指定城市的实时天气信息。当用户询问天气、温度、湿度或出行建议时调用此函数。")
            .inputType(WeatherSkill.Request.class)
            .build();
    }

    @Bean
    public FunctionCallback stockFunctionCallback(StockSkill stockSkill) {
        return FunctionCallback.builder()
            .function("stockSkill", stockSkill)
            .description("查询股票的实时价格和涨跌信息。当用户询问股票价格、涨跌幅、投资分析时调用此函数。")
            .inputType(StockSkill.Request.class)
            .build();
    }
}
