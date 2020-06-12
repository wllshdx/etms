package cn.wllnb.etms.service.teacher;

import cn.wllnb.etms.mapper.TeacherMapper;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WLL
 */
@Service("teacher_TimeTableService")
public class TimeTableService extends BaseService {

    @Autowired
    private TeacherMapper teacherMapper;

    public ResultVO get() {
        Integer teacherNumber = getUserId();
        return result(teacherMapper.listTeacherTimetable(teacherNumber));
    }
}
