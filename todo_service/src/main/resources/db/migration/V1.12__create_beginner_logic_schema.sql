-- 版本 1.12: 创建 A 路径 (小白) 编程逻辑模块所需的两张表

-- 1. 编程逻辑题目表
CREATE TABLE beginner_logic_problem (
                                        id bigserial NOT NULL,
                                        title varchar(255) NOT NULL,
                                        sort_order integer NOT NULL,
                                        CONSTRAINT beginner_logic_problem_pkey PRIMARY KEY (id)
);

-- 2. 编程逻辑内容块表
CREATE TABLE beginner_logic_content_block (
                                              id bigserial NOT NULL,
                                              category varchar(255) NOT NULL, -- "DESCRIPTION" 或 "STUB"
                                              type varchar(255) NOT NULL,
                                              content text NOT NULL,
                                              language varchar(255) NULL,
                                              sort_order integer NOT NULL,
                                              problem_id bigint NOT NULL,
                                              CONSTRAINT beginner_logic_content_block_pkey PRIMARY KEY (id),
                                              CONSTRAINT fk_block_to_logic_problem FOREIGN KEY (problem_id) REFERENCES beginner_logic_problem(id)
);