package cn.wllnb.etms.service.teacher;

import cn.wllnb.etms.mapper.TeacherMapper;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.model.vo.response.table.TeacherCourseItemVO;
import cn.wllnb.etms.service.BaseService;
import cn.wllnb.etms.utils.LessonTimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WLL
 */
@Service
public class TeacherCourseService extends BaseService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private LessonTimeConverter lessonTimeConverter;

    public ResultVO list() {
        Integer teacherNumber = getUserId();

        List<TeacherCourseItemVO> teacherCourseItemVOList = teacherMapper.listTeacherCourse(teacherNumber);
        for (TeacherCourseItemVO vo : teacherCourseItemVOList) {
            vo.setTime(lessonTimeConverter.covertTimePart(vo.getTime()));
        }
        System.out.println(teacherCourseItemVOList);

        return result(teacherCourseItemVOList);
    }

}
