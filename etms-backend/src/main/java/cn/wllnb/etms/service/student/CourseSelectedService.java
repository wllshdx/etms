package cn.wllnb.etms.service.student;

import cn.wllnb.etms.authentication.annotation.Student;
import cn.wllnb.etms.mapper.StudentCourseMapper;
import cn.wllnb.etms.dao.option.OptionDAO;
import cn.wllnb.etms.mapper.StudentMapper;
import cn.wllnb.etms.model.entities.StudentCourseEntity;
import cn.wllnb.etms.model.entities.StudentEntity;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.model.vo.response.table.StudentCourseSelectedItemVO;
import cn.wllnb.etms.service.BaseService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("student_courseService")
public class CourseSelectedService extends BaseService {

    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private OptionDAO optionDAO;

    public ResultVO list() {
        StudentEntity studentEntity = studentMapper.selectOne(new LambdaQueryWrapper<StudentEntity>()
                .eq(StudentEntity::getNumber, getUserId()));
        List<StudentCourseSelectedItemVO> studentCourseSelectedItemVOList = studentCourseMapper.listStudentCourseSelected(studentEntity.getId());
        return result(studentCourseSelectedItemVOList);
    }

    public ResultVO delete(Integer studentCourseId) {
        StudentEntity studentEntity = studentMapper.selectOne(new LambdaQueryWrapper<StudentEntity>()
                .eq(StudentEntity::getNumber, getUserId()));
        if (!optionDAO.getAllowStudentSelect()) {
            return failedResult("现在还不是选课时间");
        }
        StudentCourseEntity studentCourse = studentCourseMapper.selectById(studentCourseId);
        if (studentCourse == null) {
            return failedResult("学生选课：" + studentCourseId + "不存在");
        }
        if (!studentCourse.getStudentId().equals(studentEntity.getId())) {
            return failedResult("此课程非学生所选");
        }

        if (studentCourse.getDailyScore() != null || studentCourse.getExamScore() != null) {
            return failedResult("学生已获得成绩, 不能退选");
        }

        studentCourseMapper.deleteById(studentCourseId);
        return result("退课成功");
    }
}
