-- 版本 1.8: 创建 A 路径 (小白) 学习模块所需的三张表

-- 1. 大关卡表
CREATE TABLE beginner_level (
                                id bigserial NOT NULL,
                                title varchar(255) NOT NULL,
                                sort_order integer NOT NULL,
                                CONSTRAINT beginner_level_pkey PRIMARY KEY (id)
);

-- 2. 小关卡表
CREATE TABLE beginner_lesson (
                                 id bigserial NOT NULL,
                                 title varchar(255) NOT NULL,
                                 sort_order integer NOT NULL,
                                 level_id bigint NOT NULL,
                                 CONSTRAINT beginner_lesson_pkey PRIMARY KEY (id),
                                 CONSTRAINT fk_lesson_to_level FOREIGN KEY (level_id) REFERENCES beginner_level(id)
);

-- 3. 关卡内容块表
CREATE TABLE beginner_lesson_content_block (
                                               id bigserial NOT NULL,
                                               content text NOT NULL,
                                               language varchar(255) NULL,
                                               sort_order integer NOT NULL,
                                               type varchar(255) NOT NULL,
                                               lesson_id bigint NOT NULL,
                                               CONSTRAINT beginner_lesson_content_block_pkey PRIMARY KEY (id),
                                               CONSTRAINT fk_block_to_lesson FOREIGN KEY (lesson_id) REFERENCES beginner_lesson(id)
);