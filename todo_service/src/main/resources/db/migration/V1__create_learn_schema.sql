-- 版本 1: 创建学习模块所需的三张表

-- 1. 课程分组表
CREATE TABLE module_group (
                              id bigserial NOT NULL,
                              sort_order integer NOT NULL,
                              title varchar(255) NOT NULL,
                              CONSTRAINT module_group_pkey PRIMARY KEY (id)
);

-- 2. 课程模块表
CREATE TABLE course_module (
                               id varchar(255) NOT NULL,
                               description text NULL,
                               title varchar(255) NOT NULL,
                               group_id bigint NULL,
                               CONSTRAINT course_module_pkey PRIMARY KEY (id),
                               CONSTRAINT fk_module_to_group FOREIGN KEY (group_id) REFERENCES module_group(id)
);

-- 3. 模块内容块表
CREATE TABLE module_content_block (
                                      id bigserial NOT NULL,
                                      content text NOT NULL,
                                      language varchar(255) NULL,
                                      sort_order integer NOT NULL,
                                      type varchar(255) NOT NULL,
                                      module_id varchar(255) NOT NULL,
                                      CONSTRAINT module_content_block_pkey PRIMARY KEY (id),
                                      CONSTRAINT fk_block_to_module FOREIGN KEY (module_id) REFERENCES course_module(id)
);