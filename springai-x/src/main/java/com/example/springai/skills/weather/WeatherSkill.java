package com.example.springai.skills.weather;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;
import java.util.function.Function;

@Component
@Description("查询指定城市的实时天气信息。当用户询问天气、温度、湿度或出行建议时调用此函数。")
public class WeatherSkill implements Function<WeatherSkill.Request, WeatherSkill.Response> {

    public record Request(
        @Description("城市名称，支持中文或英文，如 '北京'、'Shanghai'") String city,
        @Description("国家代码，ISO 3166-1 alpha-2 格式，如 'CN'、'US'，默认为 'CN'") String country
    ) {}

    public record Response(
        @Description("查询的城市名称") String city,
        @Description("天气状况，如 'Sunny'、'Rainy'、'Cloudy'") String condition,
        @Description("温度，单位摄氏度") Double temperature,
        @Description("湿度百分比，0-100") Double humidity,
        @Description("根据天气生成的出行建议") String advice
    ) {}

    @Override
    public Response apply(Request request) {
        return new Response(
            request.city(),
            "Sunny",
            25.5,
            60.0,
            "Perfect weather for outdoor activities!"
        );
    }
}
