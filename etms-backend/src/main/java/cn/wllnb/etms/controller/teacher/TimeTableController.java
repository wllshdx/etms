package cn.wllnb.etms.controller.teacher;

import cn.wllnb.etms.authentication.annotation.Teacher;
import cn.wllnb.etms.controller.BaseController;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.teacher.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WLL
 */
@Teacher
@RestController("teacher_TimeTableController")
@RequestMapping("/teacher/timetable")
public class TimeTableController extends BaseController {

    @Autowired
    private TimeTableService timeTableService;

    @RequestMapping
    public ResultVO get() {
        return timeTableService.get();
    }
}
