package com.suat.app.backend.todo_service.dto;

import java.util.List;

public record AlgorithmDetail(
        String id,
        String title,
        String difficulty,
        List<ContentBlock> descriptionBlocks, // 题目描述 (复用 ContentBlock)
        List<ContentBlock> solutionBlocks   // 题解 (复用 ContentBlock)
) {}