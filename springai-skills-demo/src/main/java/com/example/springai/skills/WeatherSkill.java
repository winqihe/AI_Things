package com.example.springai.skills;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * Weather Skill
 * 提供天气查询功能的技能
 */
@Service
@Description("获取指定城市的天气信息")
public class WeatherSkill {

    private final Random random = new Random();

    /**
     * 查询天气
     *
     * @param city 城市名称
     * @return 天气信息
     */
    @Description("获取指定城市的天气状况和温度")
    public String getWeather(String city) {
        // 模拟天气数据（实际项目中可调用真实天气API）
        String[] conditions = {"晴天", "多云", "阴天", "小雨", "大雨"};
        int temp = 15 + random.nextInt(20); // 15-35度
        String condition = conditions[random.nextInt(conditions.length)];

        return String.format("%s的天气：%s，温度 %d°C", city, condition, temp);
    }

    /**
     * 查询未来天气预报
     *
     * @param city 城市名称
     * @param days 天数
     * @return 未来天气预报
     */
    @Description("获取指定城市未来几天的天气预报")
    public String getForecast(String city, int days) {
        StringBuilder forecast = new StringBuilder();
        forecast.append(city).append("未来").append(days).append("天预报：\n");

        for (int i = 0; i < Math.min(days, 7); i++) {
            String date = LocalDateTime.now().plusDays(i + 1).toLocalDate().toString();
            String[] conditions = {"晴", "多云", "阴", "雨"};
            int temp = 15 + random.nextInt(20);
            String condition = conditions[random.nextInt(conditions.length)];

            forecast.append(String.format("  %s：%s，%d°C\n", date, condition, temp));
        }

        return forecast.toString();
    }
}
