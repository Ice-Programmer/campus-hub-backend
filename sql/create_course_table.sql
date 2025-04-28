-- 创建库
create database if not exists campus_course;

-- 切换库
use campus_course;

-- 课程分类表
create table course_category
(
    `id`            bigint primary key auto_increment comment '分类id',
    `category_name` varchar(50)                        not null comment '分类名称',
    `parent_id`     bigint                             null comment '父分类id',
    `level`         int      default 1                 not null comment '分类层级',
    `sort`          int      default 0                 not null comment '排序字段',
    `status`        tinyint  default 1                 not null comment '状态：0-禁用，1-启用',
    `create_time`   datetime default current_timestamp not null comment '创建时间',
    `update_time`   datetime default current_timestamp not null on update current_timestamp comment '更新时间',
    index idx_parent_id (`parent_id`),
    index idx_sort (`sort`)
) comment '课程分类表';

-- 课程基本信息表
create table course
(
    `id`               bigint primary key auto_increment comment '课程id',
    `course_name`      varchar(128)                             not null comment '课程名称',
    `category_id`      bigint                                   not null comment '所属分类id',
    `teacher_id`       bigint                                   not null comment '讲师id',
    `cover_img`        varchar(256)                             not null comment '封面图片url',
    `description`      text                                     null comment '课程描述',
    `price`            decimal(10, 2) default 0.00              not null comment '课程价格',
    `original_price`   decimal(10, 2) default 0.00              not null comment '原价',
    `course_hours`     int            default 0                 not null comment '课时数',
    `difficulty_level` tinyint        default 1                 not null comment '难度级别：1-入门，2-初级，3-中级，4-高级，5-专家',
    `status`           tinyint        default 0                 not null comment '状态：0-未发布，1-已发布，2-下架',
    `view_count`       int            default 0                 not null comment '浏览量',
    `buy_count`        int            default 0                 not null comment '购买人数',
    `is_free`          tinyint        default 0                 not null comment '是否免费：0-收费，1-免费',
    `is_recommend`     tinyint        default 0                 not null comment '是否推荐：0-不推荐，1-推荐',
    `create_time`      datetime       default current_timestamp not null comment '创建时间',
    `update_time`      datetime       default current_timestamp not null on update current_timestamp comment '更新时间',
    `is_delete`        tinyint        default 0                 not null comment '是否删除',
    index idx_category_id (`category_id`),
    index idx_teacher_id (`teacher_id`),
    index idx_status (`status`),
    index idx_is_recommend (`is_recommend`)
) comment '课程基本信息表';

-- 课程章节表
create table course_chapter
(
    `id`           int primary key auto_increment comment '章节id',
    `course_id`    bigint                             not null comment '课程id',
    `chapter_name` varchar(128)                       not null comment '章节名称',
    `sort`         int      default 0                 not null comment '排序字段',
    `create_time`  datetime default current_timestamp not null comment '创建时间',
    `update_time`  datetime default current_timestamp not null on update current_timestamp comment '更新时间',
    `is_delete`    tinyint  default 0                 not null comment '是否删除',
    index idx_course_id (`course_id`),
    index idx_sort (`sort`)
) comment '课程章节表';

-- 课程内容表（小节）
create table course_section
(
    `id`             bigint primary key auto_increment comment '小节id',
    `course_id`      bigint                             not null comment '课程id',
    `chapter_id`     bigint                             not null comment '章节id',
    `section_name`   varchar(128)                       not null comment '小节名称',
    `video_url`      varchar(256)                       null comment '视频url',
    `video_duration` int      default 0                 not null comment '视频时长（秒）',
    `is_free`        tinyint  default 0                 not null comment '是否免费：0-不免费，1-免费',
    `sort`           int      default 0                 not null comment '排序字段',
    `status`         tinyint                            not null default 0 comment '状态：0-未发布，1-已发布',
    `create_time`    datetime default current_timestamp not null comment '创建时间',
    `update_time`    datetime default current_timestamp not null on update current_timestamp comment '更新时间',
    `is_delete`      tinyint  default 0                 not null comment '是否删除',
    index idx_course_id (`course_id`),
    index idx_chapter_id (`chapter_id`),
    index idx_sort (`sort`)
) comment '课程内容表（小节）';

-- 课程资料表
create table course_resource
(
    `id`             bigint primary key auto_increment comment '资料id',
    `course_id`      int                                not null comment '课程id',
    `section_id`     int                                null comment '小节id',
    `resource_name`  varchar(128)                       not null comment '资料名称',
    `resource_url`   varchar(256)                       not null comment '资料url',
    `resource_type`  tinyint                            not null comment '资料类型：1-文档，2-视频，3-音频，4-图片，5-其他',
    `file_size`      int      default 0                 not null comment '文件大小（kb）',
    `download_count` int      default 0                 not null comment '下载次数',
    `create_time`    datetime default current_timestamp not null comment '创建时间',
    `update_time`    datetime default current_timestamp not null on update current_timestamp comment '更新时间',
    `is_delete`      tinyint  default 0                 not null comment '是否删除',
    index idx_course_id (`course_id`),
    index idx_section_id (`section_id`)
) comment '课程资料表';

-- 用户选课表（用户与课程关联表）
create table user_course
(
    `id`            bigint primary key auto_increment comment '记录id',
    `user_id`       bigint                             not null comment '用户id',
    `course_id`     bigint                             not null comment '课程id',
    `purchase_time` datetime default current_timestamp not null comment '购买时间',
    `expire_time`   datetime                           null comment '过期时间',
    `progress`      int      default 0                 not null comment '学习进度（百分比）',
    `status`        tinyint  default 1                 not null comment '状态：0-禁用，1-正常',
    `create_time`   datetime default current_timestamp not null comment '创建时间',
    `update_time`   datetime default current_timestamp not null on update current_timestamp comment '更新时间',
    `is_delete`     tinyint  default 0                 not null comment '是否删除',

    unique key uk_user_course (`user_id`, `course_id`),
    index idx_user_id (`user_id`),
    index idx_course_id (`course_id`)
) comment '用户选课表';

-- 课程评价表
create table course_review
(
    `id`           bigint primary key auto_increment comment '评价id',
    `course_id`    bigint                             not null comment '课程id',
    `user_id`      bigint                             not null comment '用户id',
    `content`      text                               null comment '评价内容',
    `rating`       tinyint                            not null comment '评分（1-5）',
    `is_anonymous` tinyint  default 0                 not null comment '是否匿名：0-不匿名，1-匿名',
    `status`       tinyint  default 1                 not null comment '状态：0-隐藏，1-显示',
    `create_time`  datetime default current_timestamp not null comment '创建时间',
    `update_time`  datetime default current_timestamp not null on update current_timestamp comment '更新时间',
    `is_delete`    tinyint  default 0                 not null comment '是否删除',
    index idx_course_id (`course_id`),
    index idx_user_id (`user_id`),
    index idx_rating (`rating`)
) comment '课程评价表';


-- 学习记录表
create table study_record
(
    `id`            bigint primary key auto_increment comment '记录id',
    `user_id`       bigint                             not null comment '用户id',
    `course_id`     bigint                             not null comment '课程id',
    `section_id`    bigint                             not null comment '小节id',
    `study_time`    int      default 0                 not null comment '学习时长（秒）',
    `progress`      tinyint  default 0                 not null comment '进度（百分比）',
    `is_complete`   tinyint  default 0                 not null comment '是否完成：0-未完成，1-已完成',
    `last_position` int      default 0                 not null comment '上次学习位置（秒）',
    `create_time`   datetime default current_timestamp not null comment '创建时间',
    `update_time`   datetime default current_timestamp not null on update current_timestamp comment '更新时间',
    `is_delete`     tinyint  default 0                 not null comment '是否删除',
    unique key uk_user_section (`user_id`, `section_id`),
    index idx_user_id (`user_id`),
    index idx_course_id (`course_id`),
    index idx_section_id (`section_id`)
) comment '学习记录表';


-- 插入语句
insert into course_category (id, category_name, parent_id, level, sort, status)
values (1, '计算机科学', null, 1, 1, 1),
       (2, '商业与管理', null, 1, 2, 1),
       (3, '设计创意', null, 1, 3, 1),
       (4, '语言学习', null, 1, 4, 1),
       (5, '职业技能', null, 1, 5, 1),
       (6, '健康生活', null, 1, 6, 1);

-- 第二级分类（计算机科学下的子分类）
insert into course_category (id, category_name, parent_id, level, sort, status)
values (101, '编程语言', 1, 2, 1, 1),
       (102, '网络开发', 1, 2, 2, 1),
       (103, '数据科学', 1, 2, 3, 1),
       (104, '人工智能', 1, 2, 4, 1),
       (105, '网络安全', 1, 2, 5, 1),
       (106, '操作系统', 1, 2, 6, 1);

-- 第二级分类（商业与管理下的子分类）
insert into course_category (id, category_name, parent_id, level, sort, status)
values (201, '市场营销', 2, 2, 1, 1),
       (202, '财务管理', 2, 2, 2, 1),
       (203, '人力资源', 2, 2, 3, 1),
       (204, '项目管理', 2, 2, 4, 1),
       (205, '创业指南', 2, 2, 5, 1);

-- 第二级分类（设计创意下的子分类）
insert into course_category (id, category_name, parent_id, level, sort, status)
values (301, 'ui/ux设计', 3, 2, 1, 1),
       (302, '平面设计', 3, 2, 2, 1),
       (303, '3d建模', 3, 2, 3, 1),
       (304, '视频制作', 3, 2, 4, 1),
       (305, '动画设计', 3, 2, 5, 1);

-- 第二级分类（语言学习下的子分类）
insert into course_category (id, category_name, parent_id, level, sort, status)
values (401, '英语', 4, 2, 1, 1),
       (402, '日语', 4, 2, 2, 1),
       (403, '法语', 4, 2, 3, 1),
       (404, '西班牙语', 4, 2, 4, 1),
       (405, '德语', 4, 2, 5, 1);

-- 第三级分类（编程语言下的子分类）
insert into course_category (id, category_name, parent_id, level, sort, status)
values (1001, 'java编程', 101, 3, 1, 1),
       (1002, 'python编程', 101, 3, 2, 1),
       (1003, 'c/c++编程', 101, 3, 3, 1),
       (1004, 'javascript编程', 101, 3, 4, 1),
       (1005, 'go语言编程', 101, 3, 5, 1);

-- 第三级分类（网络开发下的子分类）
insert into course_category (id, category_name, parent_id, level, sort, status)
values (1101, '前端开发', 102, 3, 1, 1),
       (1102, '后端开发', 102, 3, 2, 1),
       (1103, '全栈开发', 102, 3, 3, 1),
       (1104, '移动应用开发', 102, 3, 4, 1),
       (1105, '微信小程序开发', 102, 3, 5, 1);

-- 第三级分类（数据科学下的子分类）
insert into course_category (id, category_name, parent_id, level, sort, status)
values (1201, '数据分析', 103, 3, 1, 1),
       (1202, '数据可视化', 103, 3, 2, 1),
       (1203, '大数据处理', 103, 3, 3, 1),
       (1204, '数据挖掘', 103, 3, 4, 1),
       (1205, '商业智能', 103, 3, 5, 1);

-- 课程示例数据

-- 计算机科学类课程
insert into course (id, course_name, category_id, teacher_id, cover_img, description, price, original_price,
                    course_hours, difficulty_level, status, view_count, buy_count, is_free, is_recommend)
values (1, 'java编程入门到精通', 1001, 1001, 'https://example.com/images/java_course.jpg',
        'java编程入门到精通课程，从零基础开始学习java编程语言，掌握java核心语法、面向对象编程、集合框架、多线程、io流等核心知识点。适合零基础学习者。',
        199.00, 299.00, 48, 2, 1, 5680, 1250, 0, 1),
       (2, 'python数据分析实战', 1201, 1002, 'https://example.com/images/python_data.jpg',
        '本课程将带你掌握python数据分析的核心技能，包括numpy、pandas、matplotlib等库的使用，以及数据清洗、转换、可视化等实用技能，通过实际项目案例提升数据分析能力。',
        299.00, 399.00, 36, 3, 1, 4280, 980, 0, 1),
       (3, '前端开发全栈工程师', 1101, 1003, 'https://example.com/images/frontend.jpg',
        '全面学习前端开发技术栈，包括html5、css3、javascript、vue.js、react等，从入门到精通，打造完整的前端开发技能体系。',
        399.00, 599.00, 72, 3, 1, 3560, 820, 0, 1),
       (4, '人工智能与机器学习基础', 104, 1004, 'https://example.com/images/ai_ml.jpg',
        '介绍人工智能和机器学习的基本概念、算法和应用，包括监督学习、无监督学习、深度学习等内容，适合ai入门学习者。', 499.00,
        699.00, 56, 4, 1, 2980, 650, 0, 1),
       (5, '网络安全实战技术', 105, 1005, 'https://example.com/images/cybersecurity.jpg',
        '网络安全实战技术课程，涵盖网络攻防、渗透测试、安全审计、加密技术等内容，通过实际案例学习网络安全防护技能。', 399.00,
        599.00, 42, 4, 1, 1860, 420, 0, 0);

-- 商业与管理类课程
insert into course (id, course_name, category_id, teacher_id, cover_img, description, price, original_price,
                    course_hours, difficulty_level, status, view_count, buy_count, is_free, is_recommend)
values (6, '市场营销策略与实践', 201, 2001, 'https://example.com/images/marketing.jpg',
        '系统学习市场营销的核心理论和实践技能，包括市场分析、消费者行为、品牌建设、营销策划等内容，提升营销管理能力。',
        199.00, 299.00, 32, 2, 1, 2450, 580, 0, 1),
       (7, '财务管理与分析', 202, 2002, 'https://example.com/images/finance.jpg',
        '全面学习财务管理知识，包括财务报表分析、成本控制、投资决策、风险管理等内容，提升财务决策和管理能力。', 259.00,
        359.00, 38, 3, 1, 1980, 420, 0, 0),
       (8, '项目管理pmp认证课程', 204, 2003, 'https://example.com/images/pmp.jpg',
        'pmp认证备考课程，系统讲解项目管理知识体系，包括项目整合、范围、时间、成本、质量、人力资源、沟通、风险、采购和干系人管理等内容。',
        399.00, 599.00, 45, 3, 1, 3280, 760, 0, 1);

-- 设计创意类课程
insert into course (id, course_name, category_id, teacher_id, cover_img, description, price, original_price,
                    course_hours, difficulty_level, status, view_count, buy_count, is_free, is_recommend)
values (9, 'ui/ux设计实战', 301, 3001, 'https://example.com/images/ui_ux.jpg',
        '学习ui/ux设计的核心理念和实战技能，包括用户研究、交互设计、视觉设计、原型制作等内容，提升产品设计能力。', 299.00,
        399.00, 40, 3, 1, 2860, 650, 0, 1),
       (10, '平面设计师进阶课程', 302, 3002, 'https://example.com/images/graphic_design.jpg',
        '平面设计进阶课程，涵盖品牌设计、排版设计、包装设计、广告设计等内容，提升平面设计专业技能。', 259.00, 359.00, 36, 3,
        1, 2150, 480, 0, 0);

-- 语言学习类课程
insert into course (id, course_name, category_id, teacher_id, cover_img, description, price, original_price,
                    course_hours, difficulty_level, status, view_count, buy_count, is_free, is_recommend)
values (11, '商务英语口语提升', 401, 4001, 'https://example.com/images/business_english.jpg',
        '专注于商务场景的英语口语训练，包括会议沟通、商务谈判、演讲技巧等内容，提升职场英语沟通能力。', 199.00, 299.00, 30,
        2, 1, 3250, 780, 0, 1),
       (12, '日语n2考试冲刺班', 402, 4002, 'https://example.com/images/japanese.jpg',
        '针对日语能力考试n2级别的系统备考课程，包括词汇、语法、听力、阅读等全方位训练，助力考试通过。', 259.00, 359.00, 48, 3,
        1, 1860, 420, 0, 0);

-- 职业技能类课程
insert into course (id, course_name, category_id, teacher_id, cover_img, description, price, original_price,
                    course_hours, difficulty_level, status, view_count, buy_count, is_free, is_recommend)
values (13, '职场沟通与演讲技巧', 5, 5001, 'https://example.com/images/communication.jpg',
        '提升职场沟通能力和演讲技巧，包括有效沟通、冲突处理、演讲构思、表达技巧等内容，增强职场竞争力。', 159.00, 259.00, 24,
        2, 1, 4280, 1050, 0, 1),
       (14, '时间管理与高效工作法', 5, 5002, 'https://example.com/images/time_management.jpg',
        '学习科学的时间管理方法和高效工作技巧，包括目标设定、任务分解、专注力训练、工作流程优化等内容，提升工作效率。', 99.00,
        159.00, 16, 1, 1, 5680, 1580, 0, 1);

-- 健康生活类课程
insert into course (id, course_name, category_id, teacher_id, cover_img, description, price, original_price,
                    course_hours, difficulty_level, status, view_count, buy_count, is_free, is_recommend)
values (15, '瑜伽入门与进阶', 6, 6001, 'https://example.com/images/yoga.jpg',
        '从零基础开始学习瑜伽，包括基础体式、呼吸法、冥想技巧等内容，循序渐进提升身体柔韧性和心灵平静。', 129.00, 199.00, 24,
        1, 1, 3860, 920, 0, 1),
       (16, '健康饮食与营养学基础', 6, 6002, 'https://example.com/images/nutrition.jpg',
        '学习健康饮食的基本原则和营养学知识，包括营养素分析、膳食平衡、食物搭配、特殊人群饮食等内容，培养健康的饮食习惯。',
        99.00, 159.00, 20, 1, 1, 2980, 680, 0, 0);

-- 免费课程示例
insert into course (id, course_name, category_id, teacher_id, cover_img, description, price, original_price,
                    course_hours, difficulty_level, status, view_count, buy_count, is_free, is_recommend)
values (17, 'html/css基础入门', 1101, 1003, 'https://example.com/images/html_css.jpg',
        'html和css基础入门课程，从零开始学习网页开发的基础知识，掌握网页结构和样式设计的核心技能。', 0.00, 0.00, 12, 1, 1,
        8960, 3250, 1, 1),
       (18, '职场新人生存指南', 5, 5001, 'https://example.com/images/workplace_guide.jpg',
        '为职场新人提供的实用指南，包括职场礼仪、人际关系处理、工作规划等内容，帮助新人快速适应职场环境。', 0.00, 0.00, 8, 1,
        1, 7580, 2860, 1, 1);

-- 课程章节示例数据
insert into course_chapter (id, course_id, chapter_name, sort)
values (1, 1, 'java编程基础', 1),
       (2, 1, 'java面向对象编程', 2),
       (3, 1, 'java集合框架', 3),
       (4, 1, 'java多线程编程', 4),
       (5, 2, 'python基础语法', 1),
       (6, 2, 'numpy数据处理', 2),
       (7, 2, 'pandas数据分析', 3),
       (8, 2, '数据可视化技术', 4),
       (9, 3, 'html5与css3基础', 1),
       (10, 3, 'javascript编程', 2),
       (11, 3, 'vue.js框架入门', 3),
       (12, 3, 'react框架入门', 4);

-- 课程小节示例数据
insert into course_section (id, course_id, chapter_id, section_name, video_url, video_duration, is_free, sort, status)
values (101, 1, 1, 'java开发环境搭建', 'https://example.com/videos/java_env_setup.mp4', 1800, 1, 1, 1),
       (102, 1, 1, 'java基本语法', 'https://example.com/videos/java_syntax.mp4', 2400, 0, 2, 1),
       (103, 1, 1, '变量与数据类型', 'https://example.com/videos/java_variables.mp4', 1800, 0, 3, 1),
       (104, 1, 2, '类与对象', 'https://example.com/videos/java_class_object.mp4', 2700, 0, 1, 1),
       (105, 1, 2, '继承与多态', 'https://example.com/videos/java_inheritance.mp4', 3000, 0, 2, 1),
       (201, 2, 5, 'python环境搭建', 'https://example.com/videos/python_setup.mp4', 1500, 1, 1, 1),
       (202, 2, 5, 'python基础语法', 'https://example.com/videos/python_syntax.mp4', 2400, 0, 2, 1),
       (203, 2, 6, 'numpy数组操作', 'https://example.com/videos/numpy_arrays.mp4', 2700, 0, 1, 1),
       (204, 2, 7, 'pandas数据结构', 'https://example.com/videos/pandas_structures.mp4', 2400, 0, 1, 1),
       (301, 3, 9, 'html5基础标签', 'https://example.com/videos/html5_basics.mp4', 1800, 1, 1, 1),
       (302, 3, 9, 'css3选择器与样式', 'https://example.com/videos/css3_selectors.mp4', 2100, 0, 2, 1),
       (303, 3, 10, 'javascript基础语法', 'https://example.com/videos/js_basics.mp4', 2400, 0, 1, 1);

-- 课程资料示例数据
insert into course_resource (id, course_id, section_id, resource_name, resource_url, resource_type, file_size,
                             download_count)
values (1, 1, 101, 'java环境搭建指南.pdf', 'https://example.com/resources/java_setup_guide.pdf', 1, 1024, 850),
       (2, 1, 102, 'java基础语法练习题.pdf', 'https://example.com/resources/java_syntax_exercises.pdf', 1, 2048, 680),
       (3, 2, 201, 'python环境配置文档.pdf', 'https://example.com/resources/python_env_doc.pdf', 1, 1536, 520),
       (4, 2, 203, 'numpy实战案例代码.zip', 'https://example.com/resources/numpy_examples.zip', 5, 4096, 420),
       (5, 3, 301, 'html5标签速查表.pdf', 'https://example.com/resources/html5_cheatsheet.pdf', 1, 1280, 920),
       (6, 3, 303, 'javascript入门示例代码.zip', 'https://example.com/resources/js_examples.zip', 5, 3072, 680);

-- 课程评价示例数据
insert into course_review (id, course_id, user_id, content, rating, is_anonymous, status)
values (1, 1, 10001, '课程内容非常详细，讲解清晰，很适合java入门学习！', 5, 0, 1),
       (2, 1, 10002, '老师讲解思路清晰，案例丰富，学到了很多实用技能。', 5, 0, 1),
       (3, 1, 10003, '内容不错，但有些知识点讲解不够深入。', 4, 0, 1),
       (4, 2, 10004, 'python数据分析讲解得很透彻，实战案例很有帮助。', 5, 0, 1),
       (5, 2, 10005, '课程质量很高，但是进度有点快，需要多花时间消化。', 4, 1, 1),
       (6, 3, 10006, '前端课程内容全面，从基础到框架都有涉及，很满意。', 5, 0, 1),
       (7, 3, 10007, '老师讲解生动有趣，代码示例丰富，学习体验很好。', 5, 0, 1),
       (8, 4, 10008, 'ai课程内容前沿，理论与实践结合得很好。', 5, 0, 1),
       (9, 5, 10009, '网络安全课程案例丰富，但有些内容过于理论化。', 4, 0, 1),
       (10, 6, 10010, '市场营销课程实用性强，学到了很多实战技巧。', 5, 1, 1);

-- 学习记录示例数据
insert into study_record (id, user_id, course_id, section_id, study_time, progress, is_complete, last_position)
values (1, 10001, 1, 101, 1800, 100, 1, 1800),
       (2, 10001, 1, 102, 1500, 62, 0, 1500),
       (3, 10001, 1, 103, 0, 0, 0, 0),
       (4, 10002, 2, 201, 1500, 100, 1, 1500),
       (5, 10002, 2, 202, 1800, 75, 0, 1800),
       (6, 10003, 3, 301, 1800, 100, 1, 1800),
       (7, 10003, 3, 302, 1200, 57, 0, 1200),
       (8, 10004, 4, 101, 900, 50, 0, 900),
       (9, 10005, 5, 101, 1800, 100, 1, 1800),
       (10, 10005, 5, 102, 600, 25, 0, 600);

-- 用户选课示例数据
insert into user_course (id, user_id, course_id, purchase_time, expire_time, progress, status)
values (1, 10001, 1, '2023-01-15 10:30:00', '2024-01-15 10:30:00', 25, 1),
       (2, 10001, 2, '2023-02-20 14:45:00', '2024-02-20 14:45:00', 0, 1),
       (3, 10002, 2, '2023-01-10 09:15:00', '2024-01-10 09:15:00', 35, 1),
       (4, 10002, 3, '2023-03-05 16:20:00', '2024-03-05 16:20:00', 0, 1),
       (5, 10003, 3, '2023-02-18 11:30:00', '2024-02-18 11:30:00', 40, 1),
       (6, 10003, 4, '2023-04-12 13:45:00', '2024-04-12 13:45:00', 0, 1),
       (7, 10004, 4, '2023-03-22 10:00:00', '2024-03-22 10:00:00', 15, 1),
       (8, 10004, 5, '2023-05-08 15:30:00', '2024-05-08 15:30:00', 0, 1),
       (9, 10005, 5, '2023-04-25 09:45:00', '2024-04-25 09:45:00', 30, 1),
       (10, 10005, 6, '2023-06-10 14:15:00', '2024-06-10 14:15:00', 0, 1);