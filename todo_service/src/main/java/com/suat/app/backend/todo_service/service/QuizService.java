package com.suat.app.backend.todo_service.service;

// 导入 DTOs
import com.suat.app.backend.todo_service.dto.*;

// 导入 Entities
import com.suat.app.backend.todo_service.entity.QuizChapter;
import com.suat.app.backend.todo_service.entity.QuizOption;
import com.suat.app.backend.todo_service.entity.QuizQuestion;

// 导入 Repositories
import com.suat.app.backend.todo_service.repository.QuizChapterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuizChapterRepository chapterRepository;

    /**
     * API 1 的逻辑：获取所有"测验章节"列表
     */
    @Transactional(readOnly = true)
    public List<QuizChapterSummary> getQuizChapters() {
        return chapterRepository.findByOrderBySortOrderAsc().stream()
                .map(this::convertChapterEntityToSummaryDto)
                .collect(Collectors.toList());
    }

    /**
     * API 2 的逻辑：获取单个"章节"的所有问题 (用于开始测验)
     */
    @Transactional(readOnly = true)
    public List<QuizQuestionDto> getQuizQuestions(Long chapterId) {
        QuizChapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Quiz chapter not found"));

        return chapter.getQuestions().stream()
                .map(this::convertQuestionEntityToDto)
                .collect(Collectors.toList());
    }

    /**
     * API 3 的逻辑：提交答案并评分
     */
    @Transactional(readOnly = true) // 只读，因为我们只是在"核对"答案，不是"修改"
    public QuizResultResponse submitQuiz(Long chapterId, QuizResultRequest resultRequest) {
        QuizChapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Quiz chapter not found"));

        int totalQuestions = chapter.getQuestions().size();
        int correctCount = 0;

        // (Key: questionId, Value: correctOptionId)
        Map<Long, Long> correctAnswersMap = chapter.getQuestions().stream()
                .collect(Collectors.toMap(
                        QuizQuestion::getId, // Key 是问题 ID
                        q -> q.getOptions().stream() // Value 是正确选项的 ID
                                .filter(QuizOption::isCorrect)
                                .findFirst()
                                .map(QuizOption::getId)
                                .orElse(-1L) // (如果没有设置正确答案，返回-1)
                ));

        // 遍历用户的答案
        for (Map.Entry<Long, Long> userAnswer : resultRequest.answers().entrySet()) {
            Long questionId = userAnswer.getKey();
            Long selectedOptionId = userAnswer.getValue();

            // 检查用户的答案是否和我们 Map 里的正确答案一致
            if (selectedOptionId.equals(correctAnswersMap.get(questionId))) {
                correctCount++;
            }
        }

        double score = (totalQuestions > 0) ? ((double) correctCount / totalQuestions) * 100.0 : 0.0;

        return new QuizResultResponse(
                totalQuestions,
                correctCount,
                score,
                correctAnswersMap
        );
    }


    // --- 辅助转换方法 ---

    private QuizChapterSummary convertChapterEntityToSummaryDto(QuizChapter entity) {
        return new QuizChapterSummary(entity.getId(), entity.getTitle());
    }

    private QuizQuestionDto convertQuestionEntityToDto(QuizQuestion entity) {
        // 将 Option 实体 转换为 Option DTO (隐藏 isCorrect)
        List<QuizOptionDto> options = entity.getOptions().stream()
                .map(opt -> new QuizOptionDto(opt.getId(), opt.getText()))
                .collect(Collectors.toList());

        return new QuizQuestionDto(entity.getId(), entity.getText(), options);
    }
}