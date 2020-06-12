package cn.wllnb.etms.service.student;

import cn.wllnb.etms.mapper.StudentCourseMapper;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.model.vo.response.table.TimetableItemVO;
import cn.wllnb.etms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WLL
 */
@Service("student_TimeTableService")
public class TimeTableService extends BaseService {
    @Autowired
    private StudentCourseMapper studentCourseMapper;

    public ResultVO get() {
        Integer number = getUserId();
        List<TimetableItemVO> timetableItemVOList = studentCourseMapper.listStudentTimetable(number);
        return result(timetableItemVOList);
    }

}
