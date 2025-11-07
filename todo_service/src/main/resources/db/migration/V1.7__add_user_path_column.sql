-- 版本 1.7: 为 app_user 表添加 "selected_path" 列

ALTER TABLE app_user
    ADD COLUMN selected_path VARCHAR(20) NULL;