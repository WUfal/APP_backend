-- 版本 1.1: 填充学习模块的初始数据

-- 1. 插入分组
INSERT INTO module_group (id, title, sort_order) VALUES
                                                     (1, '核心基础', 1),
                                                     (2, '高级主题', 2),
                                                     (3, '主流框架与生态', 3);

-- 2. 插入课程模块
INSERT INTO course_module (id, title, description, group_id) VALUES
                                                                 ('core_oop', '面向对象 (OOP)', '类、封装、继承、多态...', 1),
                                                                 ('core_collections', '集合框架', 'List, Map, Set, HashMap原理...', 1),
                                                                 ('adv_jvm', 'JVM与内存', 'GC, 内存模型, 类加载...', 2),
                                                                 ('frm_springboot', 'Spring Boot', '自动配置, Starter...', 3);

-- 3. 插入 OOP 的内容块 (关联 module_id = 'core_oop')
INSERT INTO module_content_block (module_id, sort_order, type, content, language) VALUES
                                                                                      ('core_oop', 1, 'text', '面向对象编程 (OOP) 是Java的核心。它基于三个主要原则：封装、继承和多态。', NULL),
                                                                                      ('core_oop', 2, 'sub-header', '1. 封装 (Encapsulation)', NULL),
                                                                                      ('core_oop', 3, 'text', '封装是将数据（变量）和操作数据的方法（函数）捆绑在一起的机制。', NULL),
-- [修复] 使用 E'...'
                                                                                      ('core_oop', 4, 'code', E'public class Car {\n    private int speed; // ''private'' 实现了封装\n\n    public void setSpeed(int speed) {\n        this.speed = speed;\n    }\n}', 'java');

-- 4. 插入 集合框架 的内容块 (关联 module_id = 'core_collections')
INSERT INTO module_content_block (module_id, sort_order, type, content, language) VALUES
                                                                                      ('core_collections', 1, 'sub-header', 'HashMap 原理', NULL),
                                                                                      ('core_collections', 2, 'text', 'HashMap 是基于哈希表的Map接口实现。它允许null键和null值。', NULL),
-- [修复] 使用 E'...'
                                                                                      ('core_collections', 3, 'code', E'Map<String, String> map = new HashMap<>();\nmap.put("key1", "value1");\nString value = map.get("key1");', 'java');

-- 5. 插入占位内容 (JVM 和 Spring Boot)
INSERT INTO module_content_block (module_id, sort_order, type, content, language) VALUES
                                                                                      ('adv_jvm', 1, 'text', 'JVM 内容建设中...', NULL),
                                                                                      ('frm_springboot', 1, 'text', 'Spring Boot 内容建设中...', NULL);

-- 6. (重要) 更新序列号
SELECT setval('module_group_id_seq', (SELECT MAX(id) FROM module_group));
SELECT setval('module_content_block_id_seq', (SELECT MAX(id) FROM module_content_block));