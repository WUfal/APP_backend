-- 版本 1.11: 填充 A 路径 (小白) 的测验数据

-- 1. 插入测验章节 (QuizChapter)
-- (我们手动指定 ID，以便后续引用)
INSERT INTO quiz_chapter (id, title, sort_order) VALUES
                                                     (1, '第1关：变量与数据类型 (测验)', 1),
                                                     (2, '第2关：运算符 (测验)', 2);

-- 2. 插入问题 (QuizQuestion) - 关联到 第1关 (chapter_id = 1)
-- (我们同样手动指定 ID)
INSERT INTO quiz_question (id, text, chapter_id) VALUES
                                                     (1, '在Java中，哪种类型用于存储整数（如 10）？', 1),
                                                     (2, '哪一行代码是有效的变量声明和初始化？', 1),
                                                     (3, '`System.out.println()` 的作用是什么？', 1),
                                                     (4,'AAAAAAAAAAAAAAAAAAA',1);

-- 3. 插入选项 (QuizOption)

-- 3a. 问题 1 (question_id = 1)
INSERT INTO quiz_option (text, is_correct, question_id) VALUES
                                                            ('A. String', false, 1),
                                                            ('B. int', true, 1),  -- 正确答案
                                                            ('C. double', false, 1),
                                                            ('D. boolean', false, 1);

-- 3b. 问题 2 (question_id = 2)
INSERT INTO quiz_option (text, is_correct, question_id) VALUES
                                                            ('A. int number = 100;', true, 2), -- 正确答案
                                                            ('B. number = 100;', false, 2),
                                                            ('C. int number;', false, 2),
                                                            ('D. String 100 = number;', false, 2);

-- 3c. 问题 3 (question_id = 3)
INSERT INTO quiz_option (text, is_correct, question_id) VALUES
                                                            ('A. 声明一个变量', false, 3),
                                                            ('B. 计算数学公式', false, 3),
                                                            ('C. 在控制台打印信息', true, 3), -- 正确答案
                                                            ('D. 暂停程序', false, 3);

-- 4. (重要) 更新所有序列号，防止后续插入冲突
SELECT setval('quiz_chapter_id_seq', (SELECT MAX(id) FROM quiz_chapter));
SELECT setval('quiz_question_id_seq', (SELECT MAX(id) FROM quiz_question));
SELECT setval('quiz_option_id_seq', (SELECT MAX(id) FROM quiz_option));