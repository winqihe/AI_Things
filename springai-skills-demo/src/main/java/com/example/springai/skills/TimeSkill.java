package com.example.springai.skills;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Time Skill
 * 提供时间查询功能的技能
 */
@Service
@Description("获取当前时间和日期信息")
public class TimeSkill {

    /**
     * 获取当前时间
     *
     * @return 当前时间字符串
     */
    @Description("获取当前日期和时间")
    public String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        return "当前时间：" + now.format(formatter);
    }

    /**
     * 获取当前日期
     *
     * @return 当前日期字符串
     */
    @Description("获取当前日期")
    public String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        return "当前日期：" + now.format(formatter);
    }

    /**
     * 获取星期几
     *
     * @return 星期几
     */
    @Description("获取今天是星期几")
    public String getDayOfWeek() {
        LocalDateTime now = LocalDateTime.now();
        String[] weekdays = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        return "今天是：" + weekdays[now.getDayOfWeek().getValue() - 1];
    }
}
