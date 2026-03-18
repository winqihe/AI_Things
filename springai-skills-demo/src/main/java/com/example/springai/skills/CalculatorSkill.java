package com.example.springai.skills;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

/**
 * Calculator Skill
 * 提供数学计算功能的技能
 */
@Service
@Description("执行数学计算")
public class CalculatorSkill {

    /**
     * 加法
     *
     * @param a 第一个数
     * @param b 第二个数
     * @return 两数之和
     */
    @Description("计算两个数字的和")
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * 减法
     *
     * @param a 被减数
     * @param b 减数
     * @return 两数之差
     */
    @Description("计算两个数字的差")
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * 乘法
     *
     * @param a 第一个数
     * @param b 第二个数
     * @return 两数之积
     */
    @Description("计算两个数字的积")
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * 除法
     *
     * @param a 被除数
     * @param b 除数
     * @return 两数之商
     */
    @Description("计算两个数字的商")
    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("除数不能为零");
        }
        return a / b;
    }

    /**
     * 计算圆的面积
     *
     * @param radius 半径
     * @return 面积
     */
    @Description("计算圆的面积")
    public double calculateCircleArea(double radius) {
        return Math.PI * radius * radius;
    }
}
