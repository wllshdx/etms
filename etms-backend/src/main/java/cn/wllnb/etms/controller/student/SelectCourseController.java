package cn.wllnb.etms.controller.student;

import cn.wllnb.etms.authentication.annotation.Student;
import cn.wllnb.etms.controller.BaseController;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.student.SelectCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WLL
 * 选课
 */
@Student
@RequestMapping("/student/course/select")
@RestController
public class SelectCourseController extends BaseController {

    @Autowired
    private SelectCourseService service;

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String departmentName, String courseName, String teacherName) {
        return service.getPageCount(departmentName, courseName, teacherName);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String departmentName, String courseName, String teacherName) {
        return service.getPage(index, departmentName, courseName, teacherName);
    }

    @PostMapping("/{id}")
    public ResultVO create(@PathVariable Integer id) {
        return service.create(id);
    }
}
