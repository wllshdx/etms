package cn.wllnb.etms.controller.teacher;

import cn.wllnb.etms.authentication.annotation.Teacher;
import cn.wllnb.etms.controller.BaseController;
import cn.wllnb.etms.model.vo.TeacherGradeVO;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.teacher.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author WLL
 */
@Teacher
@RequestMapping("/teacher/grade")
@RestController
public class GradeController extends BaseController {

    @Autowired
    private GradeService service;

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String courseName, String studentName) {
        return service.getPageCount(courseName, studentName);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String courseName, String studentName) {
        return service.getPage(index, courseName, studentName);
    }

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated TeacherGradeVO vo) {
        return service.update(vo);
    }

}
