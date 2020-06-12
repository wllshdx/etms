package cn.wllnb.etms.controller.admin;


import cn.wllnb.etms.authentication.annotation.Admin;
import cn.wllnb.etms.controller.BaseController;
import cn.wllnb.etms.model.entities.CourseEntity;
import cn.wllnb.etms.model.entities.OpenedCourseEntity;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.admin.OpenedCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author WLL
 */
@Admin(Admin.COURSE_MANAGE)
@RequestMapping("/admin/openedCourse")
@RestController
public class OpenedCourseController extends BaseController {

    @Autowired
    private OpenedCourseService service;

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated OpenedCourseEntity entity) {
        return service.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated OpenedCourseEntity entity) {
        return service.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String departmentName, String teacherName, String name) {
        return service.getPageCount(departmentName, teacherName, name);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String departmentName, String teacherName, String name) {
        return service.getPage(index, departmentName, teacherName, name);
    }

    @Admin
    @RequestMapping("/names")
    public ResultVO listName() {
        return service.listName();
    }

}
