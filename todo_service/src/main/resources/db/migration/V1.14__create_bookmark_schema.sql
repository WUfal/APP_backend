-- 版本 1.14: 创建收藏夹表

CREATE TABLE bookmark (
                          id bigserial NOT NULL,
                          app_user_id bigint NOT NULL,
                          bookmark_type varchar(50) NOT NULL, -- "COURSE_MODULE", "PROJECT", ...
                          bookmarked_id varchar(255) NOT NULL, -- "core_oop", "proj_ecommerce_api", ...
                          title varchar(255) NOT NULL, -- 冗余存储的标题
                          created_at timestamp with time zone NOT NULL,

                          CONSTRAINT bookmark_pkey PRIMARY KEY (id),
                          CONSTRAINT fk_bookmark_to_user FOREIGN KEY (app_user_id) REFERENCES app_user(id) ON DELETE CASCADE,
                          CONSTRAINT uk_user_bookmark UNIQUE (app_user_id, bookmark_type, bookmarked_id) -- 唯一约束
);