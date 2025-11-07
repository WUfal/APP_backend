package com.suat.app.backend.todo_service.Controller;

import com.suat.app.backend.todo_service.dto.CodeRunRequest;
import com.suat.app.backend.todo_service.dto.CodeRunResponse;
import com.suat.app.backend.todo_service.service.SandboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sandbox") // API 前缀
public class SandboxController {

    @Autowired
    private SandboxService sandboxService;

    /**
     * API: 运行代码
     * (这个 API 会被 /api/v1/** 规则自动保护)
     */
    @PostMapping("/run")
    public ResponseEntity<CodeRunResponse> runCode(@RequestBody CodeRunRequest request) {

        // 将工作委托给 Service
        CodeRunResponse response = sandboxService.compileAndRun(request.code());

        return ResponseEntity.ok(response);
    }
}