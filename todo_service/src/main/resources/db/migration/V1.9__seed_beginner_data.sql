-- 版本 1.9: 填充 A 路径 (小白) 的初始数据

-- 1. 插入大关卡 (Level)
-- (我们手动指定 ID，以便后续引用)
INSERT INTO beginner_level (id, title, sort_order) VALUES
                                                       (1, '第1关：变量与数据类型', 1),
                                                       (2, '第2关：运算符', 2);

-- 2. 插入小关卡 (Lesson) - 关联到 第1关 (level_id = 1)
-- (我们同样手动指定 ID)
INSERT INTO beginner_lesson (id, title, sort_order, level_id) VALUES
                                                                  (1, '1.1 什么是 Java?', 1, 1),
                                                                  (2, '1.2 第一个程序: Hello World', 2, 1),
                                                                  (3, '1.3 什么是变量?', 3, 1);

-- 3. 插入内容块 (Content Blocks)

-- 3a. 关卡 1.1 (lesson_id = 1)
INSERT INTO beginner_lesson_content_block (content, language, sort_order, type, lesson_id) VALUES
                                                                                               ('Java 是一种高级编程语言，由 Sun Microsystems 公司于1995年推出。', NULL, 1, 'text', 1),
                                                                                               ('它被设计为“一次编写，到处运行”(Write Once, Run Anywhere)，这意味着编译后的Java代码可以不经修改地在所有支持Java的平台上运行。', NULL, 2, 'text', 1);

-- 3b. 关卡 1.2 (lesson_id = 2)
INSERT INTO beginner_lesson_content_block (content, language, sort_order, type, lesson_id) VALUES
                                                                                               ('在Java中，打印 "Hello, World!" 到控制台是所有学习的开始。', NULL, 1, 'text', 2),
-- (注意：我们使用 E'...' 来处理换行符)
                                                                                               (E'public class HelloWorld {\n    public static void main(String[] args) {\n        // 这是打印语句\n        System.out.println("Hello, World!");\n    }\n}', 'java', 2, 'code', 2),
                                                                                               ('点击“运行”按钮，看看会发生什么！', NULL, 3, 'text', 2);

-- 3c. 关卡 1.3 (lesson_id = 3)
INSERT INTO beginner_lesson_content_block (content, language, sort_order, type, lesson_id) VALUES
    ('变量是用来存储数据的“容器”。在Java中，你必须先声明一个变量，才能使用它。', NULL, 1, 'text', 3);

-- 4. (重要) 更新所有序列号，防止后续插入冲突
SELECT setval('beginner_level_id_seq', (SELECT MAX(id) FROM beginner_level));
SELECT setval('beginner_lesson_id_seq', (SELECT MAX(id) FROM beginner_lesson));
SELECT setval('beginner_lesson_content_block_id_seq', (SELECT MAX(id) FROM beginner_lesson_content_block));