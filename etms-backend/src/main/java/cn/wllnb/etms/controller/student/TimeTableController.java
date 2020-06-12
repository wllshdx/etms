package cn.wllnb.etms.controller.student;

import cn.wllnb.etms.authentication.annotation.Student;
import cn.wllnb.etms.controller.BaseController;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.student.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WLL
 */
@Student
@RestController
@RequestMapping("/student/timetable")
public class TimeTableController extends BaseController {

    @Autowired
    private TimeTableService service;


    @RequestMapping
    public ResultVO get() {
        return service.get();
    }
}
