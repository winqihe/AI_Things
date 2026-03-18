package com.example.springai.skills.stock;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;
import java.util.function.Function;

@Component
@Description("查询股票的实时价格和涨跌信息。当用户询问股票价格、涨跌幅、投资分析时调用此函数。")
public class StockSkill implements Function<StockSkill.Request, StockSkill.Response> {

    public record Request(
        @Description("股票代码，如 'AAPL'、'TSLA'、'600519.SH'") String symbol
    ) {}

    public record Response(
        @Description("股票代码") String symbol,
        @Description("股票当前价格，单位美元") Double price,
        @Description("价格变化，单位美元") Double change,
        @Description("涨跌幅百分比") Double changePercent,
        @Description("对股票走势的简要分析") String summary
    ) {}

    @Override
    public Response apply(Request request) {
        return new Response(
            request.symbol(),
            150.25,
            2.50,
            1.69,
            request.symbol() + " is showing positive momentum today"
        );
    }
}
