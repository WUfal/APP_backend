-- 版本 1.4: 创建算法模块所需的两张表

-- 1. 算法题目表
CREATE TABLE algorithm_problem (
                                   id varchar(255) NOT NULL,
                                   title varchar(255) NOT NULL,
                                   difficulty varchar(255) NOT NULL,
                                   CONSTRAINT algorithm_problem_pkey PRIMARY KEY (id)
);

-- 2. 算法内容块表
CREATE TABLE algorithm_content_block (
                                         id bigserial NOT NULL,
                                         category varchar(255) NOT NULL, -- "DESCRIPTION" 或 "SOLUTION"
                                         type varchar(255) NOT NULL,
                                         content text NOT NULL,
                                         language varchar(255) NULL,
                                         sort_order integer NOT NULL,
                                         problem_id varchar(255) NOT NULL,
                                         CONSTRAINT algorithm_content_block_pkey PRIMARY KEY (id),
                                         CONSTRAINT fk_block_to_problem FOREIGN KEY (problem_id) REFERENCES algorithm_problem(id)
);