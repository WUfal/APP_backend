package com.suat.app.backend.todo_service.Controller;

import com.suat.app.backend.todo_service.dto.BeginnerLevelDto;
import com.suat.app.backend.todo_service.dto.ModuleDetail;
import com.suat.app.backend.todo_service.service.BeginnerLearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/beginner") // A 路径的 API 前缀
public class BeginnerLearnController {

    @Autowired
    private BeginnerLearnService beginnerLearnService;

    /**
     * API 1: 获取所有大关卡 (A路径学习首页)
     * (这个 API 会被 /api/v1/** 规则自动保护)
     */
    @GetMapping("/levels")
    public List<BeginnerLevelDto> getAllBeginnerLevels() {
        return beginnerLearnService.getAllLevels();
    }

    /**
     * API 2: 获取单个小关卡详情
     * (这个 API 也会被自动保护)
     */
    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<ModuleDetail> getLessonDetail(@PathVariable Long lessonId) {
        try {
            ModuleDetail detail = beginnerLearnService.getLessonDetail(lessonId);
            return ResponseEntity.ok(detail); // 返回 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}