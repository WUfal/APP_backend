package com.suat.app.backend.todo_service.Controller;

import com.suat.app.backend.todo_service.dto.ContentBlock;
import com.suat.app.backend.todo_service.dto.ModuleDetail;
import com.suat.app.backend.todo_service.dto.ModuleGroup;
import com.suat.app.backend.todo_service.dto.ModuleItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/modules") // API的统一前缀
public class CourseModuleController {

    @GetMapping("/advanced") // 进阶路径的API
    public List<ModuleGroup> getAdvancedModules() {
        // --- MVP阶段：硬编码返回数据 ---

        // 分组一：核心基础
        List<ModuleItem> coreItems = List.of(
                new ModuleItem("core_oop", "面向对象 (OOP)", "类、封装、继承、多态..."),
                new ModuleItem("core_collections", "集合框架", "List, Map, Set, HashMap原理..."),
                new ModuleItem("core_exceptions", "异常处理", "try-catch-finally...")
        );
        ModuleGroup coreGroup = new ModuleGroup("核心基础", coreItems);

        // 分组二：高级主题
        List<ModuleItem> advancedItems = List.of(
                new ModuleItem("adv_concurrency", "并发编程1", "JUC, 线程池, 锁..."),
                new ModuleItem("adv_jvm", "JVM与内存", "GC, 内存模型, 类加载...")
        );
        ModuleGroup advancedGroup = new ModuleGroup("高级主题", advancedItems);

        // 分组三：主流框架 (我们讨论过的)
        List<ModuleItem> frameworkItems = List.of(
                new ModuleItem("frm_springboot", "Spring Boot", "自动配置, Starter..."),
                new ModuleItem("frm_mybatis", "MyBatis", "动态SQL, 缓存...")
        );
        ModuleGroup frameworkGroup = new ModuleGroup("主流框架与生态", frameworkItems);

        // 返回所有分组
        return List.of(coreGroup, advancedGroup, frameworkGroup);
    }
    /**
     * 新增：获取单个模块的详细内容
     * @param moduleId 路径变量，来自URL
     * @return 模块详情
     */
    @GetMapping("/advanced/{moduleId}")
    public ModuleDetail getModuleDetail(@PathVariable String moduleId) {
        // MVP阶段：根据 moduleId 硬编码返回不同的数据
        switch (moduleId) {
            case "core_oop":
                return createOopDetail();
            case "core_collections":
                return createCollectionsDetail();
            // TODO: 为其他moduleId添加case
            default:
                // 如果没有匹配到，返回一个默认的“未找到”内容
                return new ModuleDetail(
                        moduleId,
                        "内容未找到",
                        List.of(new ContentBlock("text", "抱歉，该模块的内容正在建设中..."))
                );
        }
    }

    // --- 辅助方法：创建硬编码数据 ---

    private ModuleDetail createOopDetail() {
        List<ContentBlock> blocks = List.of(
                new ContentBlock("text", "面向对象编程 (OOP) 是Java的核心。它基于三个主要原则：封装、继承和多态。"),
                new ContentBlock("sub-header", "1. 封装 (Encapsulation)"),
                new ContentBlock("text", "封装是将数据（变量）和操作数据的方法（函数）捆绑在一起的机制。"),
                new ContentBlock("code",
                        "public class Car {\n" +
                                "    private int speed; // 'private' 实现了封装\n\n" +
                                "    public void setSpeed(int speed) {\n" +
                                "        this.speed = speed;\n" +
                                "    }\n" +
                                "}",
                        "java") // 指定语言为java
        );
        return new ModuleDetail("core_oop", "面向对象 (OOP)", blocks);
    }

    private ModuleDetail createCollectionsDetail() {
        List<ContentBlock> blocks = List.of(
                new ContentBlock("sub-header", "HashMap 原理"),
                new ContentBlock("text", "HashMap 是基于哈希表的Map接口实现。它允许null键和null值。"),
                new ContentBlock("code",
                        "Map<String, String> map = new HashMap<>();\n" +
                                "map.put(\"key1\", \"value1\");\n" +
                                "String value = map.get(\"key1\");",
                        "java")
        );
        return new ModuleDetail("core_collections", "集合框架", blocks);
    }
}
