-- 版本 1.10: 创建 A 路径 (小白) 测验模块所需的三张表

-- 1. 测验章节表
CREATE TABLE quiz_chapter (
                              id bigserial NOT NULL,
                              title varchar(255) NOT NULL,
                              sort_order integer NOT NULL,
                              CONSTRAINT quiz_chapter_pkey PRIMARY KEY (id)
);

-- 2. 测验问题表
CREATE TABLE quiz_question (
                               id bigserial NOT NULL,
                               text text NOT NULL,
                               chapter_id bigint NOT NULL,
                               CONSTRAINT quiz_question_pkey PRIMARY KEY (id),
                               CONSTRAINT fk_question_to_chapter FOREIGN KEY (chapter_id) REFERENCES quiz_chapter(id)
);

-- 3. 测验选项表
CREATE TABLE quiz_option (
                             id bigserial NOT NULL,
                             text varchar(255) NOT NULL,
                             is_correct boolean NOT NULL,
                             question_id bigint NOT NULL,
                             CONSTRAINT quiz_option_pkey PRIMARY KEY (id),
                             CONSTRAINT fk_option_to_question FOREIGN KEY (question_id) REFERENCES quiz_question(id)
);