-- 版本 1.6: 创建用户表

CREATE TABLE app_user (
                          id bigserial NOT NULL,
                          username varchar(255) NOT NULL,
                          password varchar(255) NOT NULL,
                          CONSTRAINT app_user_pkey PRIMARY KEY (id),
                          CONSTRAINT uk_username UNIQUE (username) -- 用户名唯一约束
);