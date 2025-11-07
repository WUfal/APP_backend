package com.suat.app.backend.todo_service.service;

// 导入 A 路径的 DTO
import com.suat.app.backend.todo_service.dto.BeginnerLevelDto;
import com.suat.app.backend.todo_service.dto.BeginnerLessonSummary;
// 导入 B 路径可复用的 DTO
import com.suat.app.backend.todo_service.dto.ContentBlock;
import com.suat.app.backend.todo_service.dto.ModuleDetail; // 复用！

// 导入 A 路径的 Entity
import com.suat.app.backend.todo_service.entity.BeginnerLesson;
// 导入 A 路径的 Repository
import com.suat.app.backend.todo_service.repository.BeginnerLevelRepository;
import com.suat.app.backend.todo_service.repository.BeginnerLessonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeginnerLearnService {

    @Autowired
    private BeginnerLevelRepository levelRepository;

    @Autowired
    private BeginnerLessonRepository lessonRepository;

    /**
     * API 1 的逻辑：获取所有"大关卡"列表 (用于A路径学习首页)
     */
    @Transactional(readOnly = true)
    public List<BeginnerLevelDto> getAllLevels() {
        // 1. 从数据库读取所有 Level 实体 (已排序)
        List<com.suat.app.backend.todo_service.entity.BeginnerLevel> entityLevels =
                levelRepository.findByOrderBySortOrderAsc();

        // 2. 将 Entity 列表 转换为 DTO 列表
        return entityLevels.stream()
                .map(this::convertLevelEntityToDto)
                .collect(Collectors.toList());
    }

    /**
     * API 2 的逻辑：获取单个"小关卡"详情
     */
    @Transactional(readOnly = true)
    public ModuleDetail getLessonDetail(Long lessonId) {
        // 1. 从数据库获取 Lesson 实体
        BeginnerLesson entityLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + lessonId));

        // 2. 将 Lesson Entity 转换为 ModuleDetail DTO (完美复用)
        return convertLessonEntityToDetailDto(entityLesson);
    }

    // --- 辅助转换方法 ---

    private BeginnerLevelDto convertLevelEntityToDto(com.suat.app.backend.todo_service.entity.BeginnerLevel entity) {
        // 转换其内部的 Lesson 列表
        List<BeginnerLessonSummary> dtoLessons = entity.getLessons().stream()
                .map(this::convertLessonEntityToSummaryDto)
                .collect(Collectors.toList());

        return new BeginnerLevelDto(entity.getId(), entity.getTitle(), dtoLessons);
    }

    private BeginnerLessonSummary convertLessonEntityToSummaryDto(BeginnerLesson entity) {
        return new BeginnerLessonSummary(entity.getId(), entity.getTitle());
    }

    // (关键复用)
    private ModuleDetail convertLessonEntityToDetailDto(BeginnerLesson entity) {
        // 转换其内部的 ContentBlock 列表
        List<ContentBlock> dtoBlocks = entity.getContentBlocks().stream()
                .map(this::convertBlockEntityToDto)
                .collect(Collectors.toList());

        // 我们用 lessonId.toString() 作为 DTO 的 id
        return new ModuleDetail(entity.getId().toString(), entity.getTitle(), dtoBlocks);
    }

    private ContentBlock convertBlockEntityToDto(com.suat.app.backend.todo_service.entity.BeginnerLessonContentBlock entity) {
        return new ContentBlock(
                entity.getType(),
                entity.getContent(),
                entity.getLanguage()
        );
    }
}