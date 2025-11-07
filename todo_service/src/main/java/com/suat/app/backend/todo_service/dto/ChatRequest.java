package com.suat.app.backend.todo_service.dto;

public record ChatRequest(
        String message, // 用户的完整消息
        String context  // (可以为 null) e.g., "viewing: core_oop"
) {}