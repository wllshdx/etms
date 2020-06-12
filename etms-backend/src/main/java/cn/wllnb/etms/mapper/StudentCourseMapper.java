package cn.wllnb.etms.mapper;

import cn.wllnb.etms.model.entities.StudentCourseEntity;
import cn.wllnb.etms.model.vo.response.table.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseMapper extends BaseMapper<StudentCourseEntity> {

    Integer count(String courseName, String studentName, String className);

    IPage<StudentCourseItemVO> getPage(IPage<StudentCourseItemVO> page,
                                       String courseName, String studentName, String className);

    Integer countTeacherGrade(Integer teacherNumber, String courseName, String studentName);

    IPage<TeacherGradeItemVO> getTeacherGradePage(IPage<TeacherGradeItemVO> page, Integer teacherNumber, String courseName, String studentName);

    List<StudentCourseSelectedItemVO> listStudentCourseSelected(Integer studentId);

    List<StudentExamItemVO> listStudentExam(Integer number);

    Integer countStudentCourseSelectedByTimePart(Integer studentNumber, String timePart);

    List<TimetableItemVO> listStudentTimetable(Integer number);

}
