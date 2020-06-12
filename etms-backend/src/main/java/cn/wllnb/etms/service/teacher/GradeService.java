package cn.wllnb.etms.service.teacher;

import cn.wllnb.etms.dao.option.OptionDAO;
import cn.wllnb.etms.mapper.OpenedCourseMapper;
import cn.wllnb.etms.mapper.StudentCourseMapper;
import cn.wllnb.etms.mapper.TeacherMapper;
import cn.wllnb.etms.model.entities.StudentCourseEntity;
import cn.wllnb.etms.model.entities.TeacherEntity;
import cn.wllnb.etms.model.vo.TeacherGradeVO;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.BaseService;
import cn.wllnb.etms.utils.PageUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WLL
 */
@Service
public class GradeService extends BaseService {
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private OptionDAO optionDAO;
    @Autowired
    private OpenedCourseMapper openedCourseMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    public ResultVO getPageCount(String courseName, String studentName) {
        Integer teacherName = getUserId();
        int count = studentCourseMapper.countTeacherGrade(teacherName, courseName, studentName);
        return result(PageUtils.calcPageCount(count, PageUtils.PAGE_SIZE));
    }

    public ResultVO getPage(Integer index, String courseName, String studentName) {
        Integer teacherName = getUserId();
        return result(studentCourseMapper.getTeacherGradePage(
                new Page<>(index, PageUtils.PAGE_SIZE), teacherName, courseName, studentName).getRecords());
    }

    public ResultVO update(TeacherGradeVO vo) {
        if (!optionDAO.getAllowTeacherGrade()) {
            return failedResult("现在不是打分时间!");
        }

        Integer teacherNumber = getUserId();
        TeacherEntity teacherEntity = teacherMapper.selectOne(new LambdaQueryWrapper<TeacherEntity>().eq(TeacherEntity::getNumber, teacherNumber));
        StudentCourseEntity studentCourse = studentCourseMapper.selectById(vo.getStudentCourseId());
        if (studentCourse == null) {
            return failedResult("学生选课Id:" + vo.getStudentCourseId() + "不存在");
        }
        if (!openedCourseMapper.selectById(studentCourse.getOpenedCourseId()).getTeacherId().equals(teacherEntity.getId())) {
            return failedResult("此课程非您教授");
        }

        BeanUtils.copyProperties(vo, studentCourse);

        studentCourseMapper.updateById(studentCourse);
        return result("打分成功");
    }

    public ResultVO get(Integer studentCourseId) {
        if (!optionDAO.getAllowTeacherGrade()) {
            return failedResult("现在不是打分时间!");
        }

        Integer teacherNumber = getUserId();
        TeacherEntity teacherEntity = teacherMapper.selectOne(new LambdaQueryWrapper<TeacherEntity>().eq(TeacherEntity::getNumber, teacherNumber));
        StudentCourseEntity studentCourse = studentCourseMapper.selectById(studentCourseId);
        if (studentCourse == null) {
            return failedResult("学生选课Id:" + studentCourseId + "不存在");
        }
        if (!openedCourseMapper.selectById(studentCourse.getOpenedCourseId()).getTeacherId().equals(teacherEntity.getId())) {
            return failedResult("此课程非您教授");
        }

        TeacherGradeVO vo = new TeacherGradeVO();
        BeanUtils.copyProperties(studentCourse, vo);
        vo.setStudentCourseId(studentCourseId);

        return result(vo);
    }

}
