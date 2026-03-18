package com.example.springai.agent;

import com.example.springai.skills.weather.WeatherSkill;
import com.example.springai.skills.stock.StockSkill;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    private final ChatClient chatClient;
    private final WeatherSkill weatherSkill;
    private final StockSkill stockSkill;

    public AgentController(
            ChatClient.Builder builder,
            WeatherSkill weatherSkill,
            StockSkill stockSkill) {
        this.chatClient = builder.build();
        this.weatherSkill = weatherSkill;
        this.stockSkill = stockSkill;
    }

    /**
     * 普通对话 - 不调用任何 Skill
     */
    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatClient.prompt()
            .user(message)
            .call()
            .content();
    }

    /**
     * 智能对话 - AI 自动决策调用 Skill
     */
    @GetMapping("/chat/smart")
    public String smartChat(@RequestParam String message) {
        return chatClient.prompt()
            .user(message)
            .functions("weatherSkill", "stockSkill")
            .call()
            .content();
    }

    /**
     * 流式智能对话 - AI 自动决策调用 Skill（流式输出）
     */
    @GetMapping(value = "/chat/smart/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> smartStreamChat(@RequestParam String message) {
        return chatClient.prompt()
            .user(message)
            .functions("weatherSkill", "stockSkill")
            .stream()
            .content();
    }

    /**
     * 流式普通对话
     */
    @GetMapping(value = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamChat(@RequestParam String message) {
        return chatClient.prompt()
            .user(message)
            .stream()
            .content();
    }

    /**
     * 直接调用天气 Skill（调试用）
     */
    @GetMapping("/weather/direct")
    public String queryWeatherDirect(
            @RequestParam String city,
            @RequestParam(required = false, defaultValue = "CN") String country) {
        var result = weatherSkill.apply(new WeatherSkill.Request(city, country));
        return String.format(
            "City: %s | Weather: %s | Temp: %.1f°C | Humidity: %.0f%% | %s",
            result.city(), result.condition(), result.temperature(),
            result.humidity(), result.advice()
        );
    }

    /**
     * 直接调用股票 Skill（调试用）
     */
    @GetMapping("/stock/direct")
    public String queryStockDirect(@RequestParam String symbol) {
        var result = stockSkill.apply(new StockSkill.Request(symbol));
        return String.format(
            "Symbol: %s | Price: $%.2f | Change: $%.2f (%.2f%%) | %s",
            result.symbol(), result.price(), result.change(),
            result.changePercent(), result.summary()
        );
    }
}
