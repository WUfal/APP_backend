package com.suat.app.backend.todo_service.service;

import com.suat.app.backend.todo_service.dto.CodeRunResponse;
import org.springframework.stereotype.Service;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@Service
public class SandboxService {

    private static final int TIMEOUT_SECONDS = 5; // 限制代码最长运行5秒

    public CodeRunResponse compileAndRun(String code) {

        // 1. 获取 Java 编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            return new CodeRunResponse("", "JDK Compiler not found. Please run on a full JDK.");
        }

        // 2. 创建一个临时目录
        Path tempDir;
        try {
            tempDir = Files.createTempDirectory("java-sandbox-");
        } catch (IOException e) {
            return new CodeRunResponse("", "Failed to create temp directory: " + e.getMessage());
        }

        Path javaFile = null;
        Path classFile = null;

        try {
            // 3. 从代码中提取类名 (这是一个简单的假设，只适用于我们的 Hello World)
            String className = extractClassName(code);
            if (className == null) {
                return new CodeRunResponse("", "Could not find 'public class ...' definition.");
            }

            javaFile = tempDir.resolve(className + ".java");
            classFile = tempDir.resolve(className + ".class");

            // 4. 将代码写入 .java 文件
            Files.writeString(javaFile, code);

            // 5. 编译
            ByteArrayOutputStream compileErrStream = new ByteArrayOutputStream();
            int compileResult = compiler.run(null, null, compileErrStream, javaFile.toString());

            if (compileResult != 0) {
                // 编译失败
                return new CodeRunResponse("", new String(compileErrStream.toByteArray()));
            }

            // 6. 运行
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", tempDir.toString(), className);
            pb.redirectErrorStream(true); // 将 stderr 合并到 stdout
            Process p = pb.start();

            // 7. (关键) 添加超时
            if (!p.waitFor(TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                // 超时
                p.destroy();
                return new CodeRunResponse("", "Execution timed out (5 seconds limit).");
            }

            // 8. 获取输出
            String output = new String(p.getInputStream().readAllBytes());

            return new CodeRunResponse(output, ""); // 成功

        } catch (IOException | InterruptedException e) {
            return new CodeRunResponse("", "Runtime error: " + e.getMessage());
        } finally {
            // 9. (关键) 清理临时文件
            try {
                if (javaFile != null) Files.deleteIfExists(javaFile);
                if (classFile != null) Files.deleteIfExists(classFile);
                if (tempDir != null) Files.deleteIfExists(tempDir);
            } catch (IOException e) {
                System.err.println("Failed to clean up temp files: " + e.getMessage());
            }
        }
    }

    /**
     * 辅助方法：从代码中简单提取 public class name
     */
    private String extractClassName(String code) {
        String[] lines = code.split("\n");
        for (String line : lines) {
            if (line.contains("public class")) {
                String[] parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].equals("class") && i + 1 < parts.length) {
                        return parts[i + 1].replaceAll("\\{", "").trim();
                    }
                }
            }
        }
        return "HelloWorld"; // 默认（但 V1.9 里的代码是 HelloWorld）
    }
}