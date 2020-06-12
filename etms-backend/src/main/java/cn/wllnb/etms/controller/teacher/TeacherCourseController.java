package cn.wllnb.etms.controller.teacher;

import cn.wllnb.etms.authentication.annotation.Teacher;
import cn.wllnb.etms.controller.BaseController;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.teacher.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WLL
 */
@Teacher
@RequestMapping("/teacher/course")
@RestController
public class TeacherCourseController extends BaseController {
    @Autowired
    private TeacherCourseService service;

    @RequestMapping("/list")
    public ResultVO list() {
        return service.list();
    }

}
