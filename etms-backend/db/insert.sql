use etms;

set foreign_key_checks = 0;

insert into `department`
values (1, '01', '计算机学院', '上大东校区三号楼', '65347567');
insert into `department`
values (2, '02', '通信学院', '上大东校区二号楼', '65341234');
insert into `department`
values (3, '03', '材料学院', '上大东校区四号楼', '65347890');
insert into `department`
values (4, '04', '数学学院', '上大东校区一号楼', '65347567');
insert into `department`
values (5, '05', '机自学院', '上大东校区五号楼', '65341334');
insert into `department`
values (6, '06', '环化学院', '上大东校区六号楼', '65345490');
insert into `department`
values (7, '07', '测试学院1', '上大东校区9号楼', '65345490');
insert into `department`
values (8, '08', '测试学院2', '上大东校区10号楼', '65125490');
insert into `department`
values (9, '09', '测试学院3', '上大东校区1号楼', '65345490');
insert into `department`
values (10, '10', '测试学院4', '上大东校区2号楼', '65345490');
insert into `department`
values (11, '11', '测试学院5', '上大东校区3号楼', '65345490');
insert into `department`
values (12, '12', '测试学院6', '上大东校区4号楼', '65345490');
insert into `department`
values (13, '13', '测试学院7', '上大东校区5号楼', '65345490');
insert into `department`
values (14, '14', '测试学院8', '上大东校区6号楼', '65345490');
insert into `department`
values (15, '15', '测试学院9', '上大东校区7号楼', '65345490');
insert into `department`
values (16, '16', '测试学院10', '上大东校区8号楼', '65345490');
insert into `department`
values (17, '17', '测试学院11', '上大东校区11号楼', '65345490');
insert into `department`
values (18, '18', '测试学院12', '上大东校区12号楼', '65345490');
insert into `department`
values (19, '19', '测试学院13', '上大东校区8号楼', '65345490');
insert into `department`
values (20, '20', '测试学院14', '上大东校区15号楼', '65345490');
insert into `department`
values (21, '21', '测试学院15', '上大东校区16号楼', '65345490');

insert into `major`
values (1, '0101', '01', '计算机科学与工程');
insert into `major`
values (2, '0102', '01', '智能科学与工程');
insert into `major`
values (3, '0201', '02', '通信工程');
insert into `major`
values (4, '0202', '02', '通信生医工程');
insert into `major`
values (5, '0301', '03', '材料科学工程');
insert into `major`
values (6, '0302', '03', '材料2工程');
INSERT INTO `major`
VALUES (7, '0103', 1, '计算机科学与技术');
INSERT INTO `major`
VALUES (8, '0104', 1, '测试系1');
INSERT INTO `major`
VALUES (9, '0105', 1, '测试系2');
INSERT INTO `major`
VALUES (10, '0203', 2, '测试系3');
INSERT INTO `major`
VALUES (11, '0304', 3, '测试系4');
INSERT INTO `major`
VALUES (12, '0501', 5, '测试系5');
INSERT INTO `major`
VALUES (13, '0502', 5, '测试系6');
INSERT INTO `major`
VALUES (14, '0601', 6, '测试系7');
INSERT INTO `major`
VALUES (15, '0701', 7, '测试系8');
INSERT INTO `major`
VALUES (16, '0602', 6, '测试系9');
INSERT INTO `major`
VALUES (17, '0702', 7, '测试系10');
INSERT INTO `major`
VALUES (18, '0801', 8, '测试系11');
INSERT INTO `major`
VALUES (19, '0401', 4, '测试系12');
INSERT INTO `major`
VALUES (20, '0305', 3, '测试系13');
INSERT INTO `major`
VALUES (21, '0205', 2, '测试系14');
INSERT INTO `major`
VALUES (22, '0206', 2, '测试系15');
INSERT INTO `major`
VALUES (23, '0207', 2, '测试系16');
INSERT INTO `major`
VALUES (24, '0208', 2, '测试系17');


insert into `teacher`
values (1, '0101', '01', '陈迪茂', '12a5ee88c5a3a7784ef0cdafa142aec7', '0', '1973-03-06', '副教授', '3567.00');
insert into `teacher`
values (2, '0102', '01', '马小红', '12a5ee88c5a3a7784ef0cdafa142aec7', '1', '1972-12-08', '讲师', '2845.00');
insert into `teacher`
values (3, '0201', '02', '张心颖', '12a5ee88c5a3a7784ef0cdafa142aec7', '0', '1960-01-05', '教授', '4200.00');
insert into `teacher`
values (4, '0103', '01', '吴宝钢', '12a5ee88c5a3a7784ef0cdafa142aec7', '1', '1980-11-06', '讲师', '2554.00');
insert into `teacher`
values (5, '0301', '03', '陈茂', '12a5ee88c5a3a7784ef0cdafa142aec7', '0', '1973-03-06', '副教授', '3567.00');
insert into `teacher`
values (6, '0302', '03', '马红', '12a5ee88c5a3a7784ef0cdafa142aec7', '1', '1972-12-08', '讲师', '2845.00');
insert into `teacher`
values (7, '0304', '02', '张颖', '12a5ee88c5a3a7784ef0cdafa142aec7', '0', '1960-01-05', '教授', '4200.00');
insert into `teacher`
values (8, '0303', '01', '吴钢', '12a5ee88c5a3a7784ef0cdafa142aec7', '0', '1980-11-06', '讲师', '2554.00');

insert into `class`
values (1, 1, 1, 2019, '1班');
insert into `class`
values (2, 2, 2, 2019, '2班');
insert into `class`
values (3, 3, 3, 2017, '3班');
insert into `class`
values (4, 4, 2, 2017, '4班');
insert into `class`
values (5, 5, 1, 2016, '5班');
insert into `class`
values (6, 6, 2, 2016, '6班');
insert into `class`
values (7, 7, 3, 2015, '7班');
insert into `class`
values (8, 8, 4, 2014, '8班');
insert into `class`
values (9, 9, 5, 2014, '9班');
insert into `class`
values (10, 10, 6, 2013, '10班');
insert into `class`
values (11, 11, 5, 2015, '11班');
insert into `class`
values (12, 12,3, 2017, '12班');
insert into `class`
values (13, 13, 4, 2018, '13班');
insert into `class`
values (14, 14, 2, 2019, '14班');
insert into `class`
values (15, 15, 5, 2016, '15班');
insert into `class`
values (16, 16, 3, 2015, '16班');
insert into `class`
values (17, 17, 6, 2018, '17班');
insert into `class`
values (18, 18, 1, 2018, '18班');
insert into `class`
values (19, 19, 5, 2016, '19班');
insert into `class`
values (20, 20, 3, 2016, '20班');
insert into `class`
values (21, 21, 1, 2016, '21班');
insert into `class`
values (22, 22, 1, 2016, '22班');


insert into `student`
values (1, '1101', 1, '李明', '12a5ee88c5a3a7784ef0cdafa142aec7', '13613005486', '1993-03-06', '上海', '1', '2001-09-01',
        now());
insert into `student`
values (2, '1102', 2, '刘晓明', '12a5ee88c5a3a7784ef0cdafa142aec7', '18913457890', '1992-12-08', '安徽', '1', '2001-09-01',
        now());
insert into `student`
values (3, '1103', 3, '张颖', '12a5ee88c5a3a7784ef0cdafa142aec7', '18826490423', '1993-01-05', '江苏', '0', '2001-09-01',
        now());
insert into `student`
values (4, '1104', 4, '刘晶晶', '12a5ee88c5a3a7784ef0cdafa142aec7', '13331934111', '1994-11-06', '上海', '1', '2001-09-01',
        now());
insert into `student`
values (5, '1105', 5, '刘成刚', '12a5ee88c5a3a7784ef0cdafa142aec7', '18015872567', '1991-06-07', '上海', '1', '2001-09-01',
        now());
insert into `student`
values (6, '1106', 6, '李二丽', '12a5ee88c5a3a7784ef0cdafa142aec7', '18107620945', '1993-05-04', '江苏', '0', '2001-09-01',
        now());
insert into `student`
values (7, '1107', 7, '张晓峰', '12a5ee88c5a3a7784ef0cdafa142aec7', '13912341078', '1992-08-16', '浙江', '1', '2001-09-01',
        now());
insert into `student`
values (8, '1108', 8, '张小峰', '12a5ee88c5a3a7784ef0cdafa142aec7', '13913241078', '1993-05-20', '江西', '1', '2001-09-01',
        now());
insert into `student`
values (9, '1109', 9, '张三峰', '12a5ee88c5a3a7784ef0cdafa142aec7', '13911241078', '1994-02-08', '江西', '0', '2001-09-01',
        now());
insert into `student`
values (10, '1110', 10, '张四峰', '12a5ee88c5a3a7784ef0cdafa142aec7', '13913124078', '1992-07-31', '江西', '0', '2001-09-01',
        now());
insert into `student`
values (11, '1111', 11, '张五峰', '12a5ee88c5a3a7784ef0cdafa142aec7', '13923424078', '1996-08-20', '江西', '0', '2001-09-01',
        now());
insert into `student`
values (12, '1112', 11, '张六峰', '12a5ee88c5a3a7784ef0cdafa142aec7', '13913213078', '1994-03-20', '江西', '0', '2001-09-01',
        now());


insert into `course`
values (1, '08305001', '离散数学', 1, '4', '40', '01');
insert into `course`
values (2, '08305002', '数据库原理', 2, '4', '50', '01');
insert into `course`
values (3, '08305003', '数据结构', 3, '4', '50', '01');
insert into `course`
values (4, '08305004', '系统结构', 3, '6', '60', '01');
insert into `course`
values (5, '08301001', '分子物理学', 2, '4', '40', '03');
insert into `course`
values (6, '08302001', '通信学', 1, '3', '30', '02');

insert into `opened_course`
values (1, '2012-2013秋季', 1,4, '3-5-3', '地点1', 3, 3, null, null);
insert into `opened_course`
values (2, '2012-2013冬季', 2,1, '1-1-4', '地点2', 2, 5, null, null);
insert into `opened_course`
values (3, '2012-2013冬季', 2,2, '3-1-4', '地点2', 2, 5, null, null);
insert into `opened_course`
values (4, '2012-2013冬季', 2, 4, '4-1-4', '地点3', 3, 5, null, null);
insert into `opened_course`
values (5, '2012-2013冬季', 3,2, '4-5-3', '地点3', 1, 5, null, null);
insert into `opened_course`
values (6, '2013-2014秋季', 4,1, '2-1-4', '地点3', 2, 5, null, null);
insert into `opened_course`
values (7, '2013-2014秋季', 1, 2, '2-5-3', '地点1', 1, 5, null, null);
insert into `opened_course`
values (8, '2013-2014冬季', 1, 1, '1-5-3', '地点1', 5, 5, null, null);

insert into `student_course`
values (1, 1, 1, '60', '60', '60');
insert into `student_course`
values (2, 2, 3, '87', '87', '87');
insert into `student_course`
values (3, 2, 4, '84', '84', '84');
insert into `student_course`
values (4, 3, 1, '60', '60', '60');
insert into `student_course`
values (5, 4, 2, '84', '84', '84');
insert into `student_course`
values (6, 5, 7, null, null, null);
insert into `student_course`
values (7, 6, 6, '60', '60', '60');
insert into `student_course`
values (8, 5, 4, '75', '75', '75');
insert into `student_course`
values (9, 3, 5, '60', '60', '60');
insert into `student_course`
values (10, 7, 4, '75', '75', '75');
insert into `student_course`
values (11, 7, 3, null, null, null);
insert into `student_course`
values (12, 5, 2, null, null, null);
insert into `student_course`
values (13, 2, 1, null, null, null);
insert into `student_course`
values (14, 7, 6, null, null, null);
insert into `student_course`
values (15, 5,8, '75', '75', '75');
insert into `student_course`
values (16, 4, 8, '75', '75', '75');
insert into `student_course`
values (17, 3, 8, '75', '75', '75');
insert into `student_course`
values (18, 2, 8, '75', '75', '75');
insert into `student_course`
values (19, 1, 8, '75', '75', '75');



