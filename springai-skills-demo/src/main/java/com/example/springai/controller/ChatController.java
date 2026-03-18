package com.example.springai.controller;

import com.example.springai.dto.ChatRequest;
import com.example.springai.dto.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Chat Controller
 * 提供聊天API接口，自动调用注册的skills
 */
@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        ChatMemory chatMemory = new InMemoryChatMemory();
        this.chatClient = chatClientBuilder
                .defaultSystem("""
                        你是一个智能助手，可以回答用户的问题并使用各种工具来获取信息。
                        你有以下工具可以使用：
                        - 天气查询：getWeather, getForecast
                        - 数学计算：add, subtract, multiply, divide, calculateCircleArea
                        - 时间查询：getCurrentTime, getCurrentDate, getDayOfWeek

                        请用中文回答用户的问题。
                        """)
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                .build();
    }

    /**
     * 发送聊天消息
     *
     * @param request 聊天请求
     * @return 聊天响应
     */
    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String response = chatClient.prompt()
                .user(request.message())
                .call()
                .content();

        return new ChatResponse(response);
    }

    /**
     * 简化的GET接口
     */
    @GetMapping
    public Map<String, String> chatGet(@RequestParam String message) {
        String response = chatClient.prompt()
                .user(message)
                .call()
                .content();

        return Map.of("response", response);
    }
}
