package com.suat.app.backend.todo_service.service;

import org.springframework.stereotype.Service;

@Service
public class AiChatService {

    /**
     * 核心：模拟 AI 回复
     * (已升级：现在会检查 context)
     */
    public String getAiReply(String userMessage, String context) {

        // (模拟 AI 思考延迟)
        try {
            Thread.sleep(1500); // 模拟 1.5 秒
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String lowerMsg = userMessage.toLowerCase();
        String lowerContext = (context != null) ? context.toLowerCase() : "";

        // --- ⬇️ (新增) 上下文感知回复 ⬇️ ---
        if (lowerMsg.contains("它") || lowerMsg.contains("这个")) {
            if (lowerContext.equals("viewing: core_oop")) {
                return "你是指“面向对象编程”(OOP) 吗？OOP 是一种编程范式，它使用“对象”来设计软件。三大特性是：封装、继承、多态。";
            }
            if (lowerContext.equals("viewing: proj_ecommerce_api")) {
                return "你是指“电商后端API实战”项目吗？这是一个使用 Spring Boot 构建 RESTful API 的绝佳练习。";
            }
        }
        // --- ⬆️ (新增结束) ⬆️ ---

        if (lowerMsg.contains("你好") || lowerMsg.contains("hello")) {
            return "你好！我是你的 AI 助教，很高兴为你服务。";
        }
        if (lowerMsg.contains("jvm")) {
            return "JVM (Java Virtual Machine) 是 Java 虚拟机。它是一个允许计算机运行 Java 程序的抽象机器。";
        }
        if (lowerMsg.contains("spring boot")) {
            return "Spring Boot 是一个开源的 Java 框架，用于创建“微服务”。它极大地简化了 Spring 应用的搭建和开发过程。";
        }
        if (lowerMsg.contains("oop") || lowerMsg.contains("面向对象")) {
            return "面向对象编程 (OOP) 是一种编程范式，它使用“对象”（包含数据和方法）来设计软件。三大特性是：封装、继承、多态。";
        }

        // 默认回复
        return "抱歉，我对 '" + userMessage + "' 这个问题还在学习中。你可以换个方式问我，比如 '什么是 JVM？'。";
    }
}