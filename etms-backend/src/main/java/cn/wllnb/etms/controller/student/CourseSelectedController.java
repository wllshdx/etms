package cn.wllnb.etms.controller.student;

import cn.wllnb.etms.authentication.annotation.Student;
import cn.wllnb.etms.controller.BaseController;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.student.CourseSelectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WLL
 * 选好的课
 */
@Student
@RequestMapping("/student/course")
@RestController
public class CourseSelectedController extends BaseController {
    @Autowired
    private CourseSelectedService service;

    @RequestMapping("/list")
    public ResultVO list() {
        return service.list();
    }

    @RequestMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return service.delete(id);
    }

}
