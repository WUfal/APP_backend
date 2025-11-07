-- 版本 1.5: 填充算法模块的初始数据

-- 1. 插入算法题目
INSERT INTO algorithm_problem (id, title, difficulty) VALUES
                                                          ('algo_two_sum', '两数之和', 'Easy'),
                                                          ('algo_reverse_list', '反转链表', 'Easy'),
                                                          ('algo_lru_cache', 'LRU 缓存', 'Medium');

-- 2. 插入 "两数之和" 的内容块 (problem_id = 'algo_two_sum')

-- 2a. 题目描述 (category = 'DESCRIPTION')
INSERT INTO algorithm_content_block (problem_id, category, type, content, language, sort_order) VALUES
                                                                                                    ('algo_two_sum', 'DESCRIPTION', 'text', '给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。', NULL, 1),
                                                                                                    ('algo_two_sum', 'DESCRIPTION', 'sub-header', '示例 1:', NULL, 2),
-- [修复] 补全了前 3 个缺失的列
                                                                                                    ('algo_two_sum', 'DESCRIPTION', 'code', E'输入：nums = [2,7,11,15], target = 9\n输出：[0,1]\n解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。', 'plaintext', 3);

-- 2b. 题解 (category = 'SOLUTION')
INSERT INTO algorithm_content_block (problem_id, category, type, content, language, sort_order) VALUES
                                                                                                    ('algo_two_sum', 'SOLUTION', 'text', '最优解是使用哈希表（HashMap）。我们可以遍历一次数组，对于每个元素 `x`，我们检查 `target - x` 是否已经在哈希表中。', NULL, 1),
-- [修复] 补全了前 3 个缺失的列
                                                                                                    ('algo_two_sum', 'SOLUTION', 'code', E'import java.util.HashMap;\nimport java.util.Map;\n\nclass Solution {\n    public int[] twoSum(int[] nums, int target) {\n        Map<Integer, Integer> map = new HashMap<>();\n        for (int i = 0; i < nums.length; i++) {\n            int complement = target - nums[i];\n            if (map.containsKey(complement)) {\n                return new int[] { map.get(complement), i };\n            }\n            map.put(nums[i], i);\n        }\n        throw new IllegalArgumentException("No two sum solution");\n    }\n}', 'java', 2);

-- 3. (重要) 更新序列号
SELECT setval('algorithm_content_block_id_seq', (SELECT MAX(id) FROM algorithm_content_block));