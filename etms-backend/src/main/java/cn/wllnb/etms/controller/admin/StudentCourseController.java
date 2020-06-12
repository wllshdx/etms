package cn.wllnb.etms.controller.admin;

import cn.wllnb.etms.authentication.annotation.Admin;
import cn.wllnb.etms.controller.BaseController;
import cn.wllnb.etms.model.entities.StudentCourseEntity;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.admin.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author WLL
 */
@Admin(Admin.STUDENT_COURSE_MANAGE)
@RequestMapping("/admin/student/course")
@RestController
public class StudentCourseController extends BaseController {
    @Autowired
    private StudentCourseService service;

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated StudentCourseEntity entity) {
        return service.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated StudentCourseEntity entity) {
        return service.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String courseName, String studentName, String className) {
        return service.getPageCount(courseName, studentName, className);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String courseName, String studentName, String className) {
        return service.getPage(index, courseName, studentName, className);
    }
}
