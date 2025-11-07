-- 版本 1.2: 创建项目模块所需的三张表

-- 1. 项目表
CREATE TABLE project (
                         id varchar(255) NOT NULL,
                         title varchar(255) NOT NULL,
                         description text NULL,
                         tech_stack varchar(255) NOT NULL,
                         CONSTRAINT project_pkey PRIMARY KEY (id)
);

-- 2. 项目步骤表
CREATE TABLE project_step (
                              id bigserial NOT NULL,
                              step_title varchar(255) NOT NULL,
                              sort_order integer NOT NULL,
                              project_id varchar(255) NOT NULL,
                              CONSTRAINT project_step_pkey PRIMARY KEY (id),
                              CONSTRAINT fk_step_to_project FOREIGN KEY (project_id) REFERENCES project(id)
);

-- 3. 项目内容块表
CREATE TABLE project_content_block (
                                       id bigserial NOT NULL,
                                       content text NOT NULL,
                                       language varchar(255) NULL,
                                       sort_order integer NOT NULL,
                                       type varchar(255) NOT NULL,
                                       step_id bigint NOT NULL,
                                       CONSTRAINT project_content_block_pkey PRIMARY KEY (id),
                                       CONSTRAINT fk_block_to_step FOREIGN KEY (step_id) REFERENCES project_step(id)
);