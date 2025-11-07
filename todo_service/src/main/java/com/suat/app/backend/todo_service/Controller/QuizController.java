package com.suat.app.backend.todo_service.Controller;

import com.suat.app.backend.todo_service.dto.*;
import com.suat.app.backend.todo_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quiz") // A 路径的测验 API 前缀
public class QuizController {

    @Autowired
    private QuizService quizService;

    /**
     * API 1: 获取所有测验章节
     * (这个 API 会被 /api/v1/** 规则自动保护)
     */
    @GetMapping("/chapters")
    public List<QuizChapterSummary> getAllQuizChapters() {
        return quizService.getQuizChapters();
    }

    /**
     * API 2: 获取单个章节的所有问题
     */
    @GetMapping("/chapter/{chapterId}")
    public ResponseEntity<List<QuizQuestionDto>> getQuizQuestions(@PathVariable Long chapterId) {
        try {
            List<QuizQuestionDto> questions = quizService.getQuizQuestions(chapterId);
            return ResponseEntity.ok(questions);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * API 3: 提交测验答案
     */
    @PostMapping("/chapter/{chapterId}/submit")
    public ResponseEntity<QuizResultResponse> submitQuiz(
            @PathVariable Long chapterId,
            @RequestBody QuizResultRequest resultRequest
    ) {
        try {
            QuizResultResponse response = quizService.submitQuiz(chapterId, resultRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}