-- 版本 1.13: 填充 A 路径 (小白) 的编程逻辑题数据

-- 1. 插入题目 (BeginnerLogicProblem)
-- (我们手动指定 ID，以便后续引用)
INSERT INTO beginner_logic_problem (id, title, sort_order) VALUES
                                                               (1, 'P1. 打印 1 到 10', 1),
                                                               (2, 'P2. 数组求和', 2);

-- 2. 插入 "P1. 打印 1 到 10" 的内容块 (problem_id = 1)

-- 2a. 题目描述 (category = 'DESCRIPTION')
INSERT INTO beginner_logic_content_block (problem_id, category, type, content, language, sort_order) VALUES
                                                                                                         (1, 'DESCRIPTION', 'text', '请使用 `for` 循环，在控制台从 1 打印到 10，每个数字占一行。', NULL, 1),
                                                                                                         (1, 'DESCRIPTION', 'sub-header', '预期输出:', NULL, 2),
                                                                                                         (1, 'DESCRIPTION', 'code', E'1\n2\n3\n4\n5\n6\n7\n8\n9\n10', 'plaintext', 3);

-- 2b. 初始代码 (category = 'STUB') - 这是用户在编辑器里看到的模板
INSERT INTO beginner_logic_content_block (problem_id, category, type, content, language, sort_order) VALUES
    (1, 'STUB', 'code', E'public class Solution {\n    public static void main(String[] args) {\n        // TODO: 在这里编写你的 for 循环\n        \n    }\n}', 'java', 1);

-- 3. 插入 "P2. 数组求和" 的内容块 (problem_id = 2)
-- (暂时只放占位符)
INSERT INTO beginner_logic_content_block (problem_id, category, type, content, language, sort_order) VALUES
    (2, 'DESCRIPTION', 'text', '题目内容建设中...', NULL, 1);

-- 4. (重要) 更新所有序列号
SELECT setval('beginner_logic_problem_id_seq', (SELECT MAX(id) FROM beginner_logic_problem));
SELECT setval('beginner_logic_content_block_id_seq', (SELECT MAX(id) FROM beginner_logic_content_block));