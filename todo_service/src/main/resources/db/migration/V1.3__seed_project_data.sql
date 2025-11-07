-- 版本 1.3: 填充项目模块的初始数据

INSERT INTO project (id, title, description, tech_stack) VALUES
                                                             ('proj_ecommerce_api', '电商后端API实战', '使用Spring Boot构建一个简单的电商API', 'Spring Boot, JPA'),
                                                             ('proj_chat_app', '简易聊天室', '使用WebSocket和Spring构建实时聊天', 'Spring WebSocket');

INSERT INTO project_step (id, step_title, sort_order, project_id) VALUES
                                                                      (1, 'Step 1: 项目设置与依赖', 1, 'proj_ecommerce_api'),
                                                                      (2, 'Step 2: 配置数据库连接', 2, 'proj_ecommerce_api');

-- 修复点：使用 E'...' 来处理 \n
INSERT INTO project_content_block (content, language, sort_order, type, step_id) VALUES
                                                                                     ('访问 start.spring.io 并添加以下关键依赖：''Spring Web'', ''Spring Data JPA'', ''MySQL Driver''。', NULL, 1, 'text', 1),
                                                                                     (E'<dependency>\n    <groupId>org.springframework.boot</groupId>\n    <artifactId>spring-boot-starter-web</artifactId>\n</dependency>', 'xml', 2, 'code', 1);

-- 修复点：使用 E'...' 来处理 \n
INSERT INTO project_content_block (content, language, sort_order, type, step_id) VALUES
                                                                                     ('在 `application.properties` 中添加你的MySQL数据库连接信息。', NULL, 1, 'text', 2),
                                                                                     (E'spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db\nspring.datasource.username=root\nspring.datasource.password=your_password\nspring.jpa.hibernate.ddl-auto=update', 'properties', 2, 'code', 2);

SELECT setval('project_step_id_seq', (SELECT MAX(id) FROM project_step));
SELECT setval('project_content_block_id_seq', (SELECT MAX(id) FROM project_content_block));