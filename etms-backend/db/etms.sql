drop database if exists etms;
create database etms;
use etms;

set foreign_key_checks = 0;

-- -----------------------
-- 管理员表`admin`
-- -----------------------
drop table if exists `admin`;
create table `admin`
(
    `admin_id`        int(4) zerofill not null auto_increment,
    `admin_username`  varchar(255)    not null,
    `admin_password`  char(32)        not null,
    `admin_privilege` int(11)         not null comment '角色\r\n二进制表示权限\r\n1-系管理\r\n2-专业管理\r\n4-班级管理\r\n8-学生管理\r\n16-教师管理\r\n32-课程管理\r\n64-选课管理\r\n128-管理员管理',
    primary key (`admin_id`),
    unique index `idx_admin_username` (`admin_username`)
) engine = InnoDB
  auto_increment = 3
  default charset = utf8mb4
  collate = utf8mb4_general_ci
  ROW_FORMAT = Dynamic
    comment '管理员表';

insert into `admin`
values (1, 'admin', '12a5ee88c5a3a7784ef0cdafa142aec7', 255);
insert into `admin`
values (2, 'azure99', '12a5ee88c5a3a7784ef0cdafa142aec7', 96);

-- -----------------------
-- 学院表department
-- -----------------------
drop table if exists `department`;
create table `department`
(
    `department_id`        int auto_increment not null comment '学院id',
    `department_number`    int(2) zerofill    not null comment '学院号',
    `department_name`      varchar(32)        not null comment '学院名称',
    `department_address`   varchar(32)        not null comment '地址',
    `department_telephone` char(8)            not null comment '联系电话',
    primary key (`department_id`),
    unique index `idx_department_number` (`department_number`),
    unique index `idx_department_name` (`department_name`)
) ENGINE = InnoDB
  auto_increment = 10
  default charset = utf8mb4
  collate = utf8mb4_general_ci
  ROW_FORMAT = Dynamic
    comment '学院表';

-- ----------------------------
-- 专业表 major
-- ----------------------------
drop table if exists `major`;
create table `major`
(
    `major_id`            int auto_increment not null comment '专业id',
    `major_number`        int(4) zerofill    not null comment '专业号',
    `major_department_id` int                not null comment '学院Id',
    `major_name`          varchar(32)        not null comment '专业名称',
    primary key (`major_id`),
    index `fk_major_department_id` (`major_department_id`),
    unique index `idx_major_number` (`major_number`),
    unique index `idx_major_name` (`major_name`),
    FOREIGN KEY (`major_department_id`) REFERENCES `department` (`department_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) engine = InnoDB
  auto_increment = 15
  character set utf8mb4
  collate utf8mb4_general_ci
  ROW_FORMAT = Dynamic
    comment '专业表';

-- -----------------------
-- 教师表teacher
-- -----------------------
drop table if exists `teacher`;
create table `teacher`
(
    `teacher_id`            int auto_increment not null comment '老师id',
    `teacher_number`        int(4) zerofill    not null comment '老师工号',
    `teacher_department_id` int                not null comment '院系Id',
    `teacher_name`          varchar(20)        not null comment '姓名',
    `teacher_password`      char(32)           not null comment '密码',
    `teacher_gender`        int(2)             NOT NULL COMMENT '性别,1男，0女',
    `teacher_birthdate`     date               not null comment '出生日期',
    `teacher_degree`        varchar(10)        not null comment '学历',
    `teacher_base_salary`   double(10, 2)      not null comment '基础工资',
    primary key (`teacher_id`),
    unique index `idx_teacher_number` (`teacher_number`),
    foreign key (`teacher_department_id`) references `department` (`department_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  auto_increment = 10
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic
    comment '教师表';

-- -----------------------
-- 班级表class
-- -----------------------
drop table if exists `class`;
create table `class`
(
    `class_id`       int auto_increment not null comment '班级·Id',
    `class_number`   int(4) zerofill    not null comment '班级号',
    `class_major_id` int                not null comment '专业Id',
    `class_grade`    int(4) zerofill    not null comment '年级',
    `class_name`     varchar(32)        NOT NULL COMMENT '班级名称',
    primary key (`class_id`),
    index `fk_major_id` (`class_major_id`),
    unique index `idx_class_number` (`class_number`),
    unique index `idx_class_name` (`class_name`),
    foreign key (`class_major_id`) references `major` (`major_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) engine = InnoDB
  auto_increment = 24
  character set = utf8mb4
  collate = utf8mb4_general_ci
  ROW_FORMAT = Dynamic
    comment '班级表';

-- -----------------------
-- 学生表student
-- -----------------------
drop table if exists `student`;
create table `student`
(
    `student_id`              int auto_increment not null comment '学生Id',
    `student_number`          int(4) zerofill    not null comment '学号',
    `student_class_id`        int                not null comment '班级Id',
    `student_name`            varchar(20)        not null comment '姓名',
    `student_password`        char(32)           not null comment '密码',
    `student_telephone`       char(11)           not null comment '手机号',
    `student_birthdate`       date               not null COMMENT '生日',
    `student_birth_location`  varchar(10)        not null comment '籍贯',
    `student_gender`          int(2)             NOT NULL COMMENT '性别,1男，0女',
    `student_admission_year`  date               not null comment '入学日期',
    `student_last_login_time` datetime(0)        NULL DEFAULT NULL COMMENT '最近登录时间',
    primary key (`student_id`),
    INDEX `idx_student_name` (`student_name`),
    unique index `idx_student_number` (`student_number`),
    FOREIGN KEY (`student_class_id`) REFERENCES `class` (`class_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 29
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic
    comment '学生表';

-- ----------------------------
-- 课程表:course
-- ----------------------------
drop table if exists `course`;
create table `course`
(
    `course_id`            int auto_increment not null COMMENT '课程Id',
    `course_number`        int(8) zerofill    not null COMMENT '课程号',
    `course_name`          varchar(32)        NOT NULL COMMENT '课程名称',
    `course_grade`         int(2)             NOT NULL COMMENT '授课年级',
    `course_credit`        int(2)             NOT NULL default 4 COMMENT '学分',
    `course_time`          int(2) zerofill    not null default 40 comment '学时',
    `course_department_id` int                not null comment '院系号',
    PRIMARY KEY (`course_id`),
    unique INDEX `idx_course_number` (`course_number`),
    unique INDEX `idx_course_name` (`course_name`),
    constraint `fk_department_id` foreign key (`course_department_id`) references `department` (`department_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic
    comment '课程表';

-- ----------------------------
-- 开课表O：学期，课号，工号
-- ----------------------------
drop table if exists `opened_course`;
create table `opened_course`
(
    `oc_id`                int auto_increment not null comment '开课表主键',
    `oc_term`              char(15)           not null comment '学期',
    `oc_course_id`         int                not null COMMENT '课程Id',
    `oc_teacher_id`        int                NOT NULL COMMENT '授课教师Id',
    `oc_time`              varchar(16)        NOT NULL COMMENT '上课时间 星期几-第几节-几节课',
    `oc_location`          varchar(32)        NOT NULL COMMENT '上课地址',
    `oc_selected_count`    int(10) UNSIGNED   NOT NULL DEFAULT 0 COMMENT '已选人数',
    `oc_max_size`          int(10) UNSIGNED   NOT NULL COMMENT '最大容量',
    `course_exam_date`     datetime(0)        NULL     DEFAULT NULL COMMENT '考试时间',
    `course_exam_location` varchar(32)        NULL     DEFAULT NULL COMMENT '考试地点',
    primary key (`oc_id`),
    FOREIGN KEY (`oc_teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    foreign key (`oc_course_id`) references `course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  AUTO_INCREMENT = 1
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic
    comment '开课表';

-- ----------------------------
-- Table: student-course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course`
(
    `sc_id`          int auto_increment not null comment '学生-课程表',
    `sc_student_id`  int                NOT NULL COMMENT '学生Id',
    `oc_id`          int                not null comment '开课表主键',
    `sc_daily_score` int(10) UNSIGNED   NULL DEFAULT NULL COMMENT '平时表现分',
    `sc_exam_score`  int(10) UNSIGNED   NULL DEFAULT NULL COMMENT '期末测试分',
    `sc_score`       int(10) UNSIGNED   NULL DEFAULT NULL COMMENT '总成绩',
    primary key (`sc_id`),
    FOREIGN KEY (`sc_student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (`oc_id`) REFERENCES `opened_course` (`oc_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  auto_increment = 10
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic
    comment '学生-课程表';

set foreign_key_checks = 1;



