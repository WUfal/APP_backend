package com.suat.app.backend.todo_service.Controller;

import com.suat.app.backend.todo_service.entity.AppUser;
import com.suat.app.backend.todo_service.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user") // 注意：不是 /api/v1，也不是 /api/auth
public class UserController {

    @Autowired
    private AppUserRepository appUserRepository;

    /**
     * API: 保存用户的路径选择
     * (这个 API 会被 /api/v1/** 规则自动保护)
     *
     * @param payload 期望一个 JSON: { "path": "BEGINNER" }
     */
    @PostMapping("/path")
    public ResponseEntity<?> updateUserPath(@RequestBody Map<String, String> payload) {

        // 1. (关键) 从“安全上下文”中获取当前登录的用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();

        // 2. 从数据库中找到这个用户
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 3. 获取前端传来的路径
        String path = payload.get("path");
        if (path == null || !(path.equals("BEGINNER") || path.equals("ADVANCED"))) {
            return ResponseEntity.badRequest().body("Invalid path value.");
        }

        // 4. 更新并保存
        appUser.setSelectedPath(path);
        appUserRepository.save(appUser);

        return ResponseEntity.ok("Path updated successfully.");
    }
}