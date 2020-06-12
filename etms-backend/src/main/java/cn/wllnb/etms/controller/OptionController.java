package cn.wllnb.etms.controller;

import cn.wllnb.etms.authentication.annotation.Admin;
import cn.wllnb.etms.model.vo.request.BoolOptionVO;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author WLL
 */
@RestController
@RequestMapping("/option")
public class OptionController extends BaseController {
    @Autowired
    private OptionService service;

    @GetMapping("/allowStudentSelect")
    public ResultVO getAllowStudentSelect() {
        return service.getAllowStudentSelect();
    }

    @Admin(Admin.STUDENT_COURSE_MANAGE)
    @PutMapping("/allowStudentSelect")
    public ResultVO setAllowStudentSelect(@RequestBody @Validated BoolOptionVO option) {
        return service.setAllowStudentSelect(option.getOption());
    }

    @GetMapping("/allowTeacherGrade")
    public ResultVO getAllowTeacherGrade() {
        return service.getAllowTeacherGrade();
    }

    @Admin(Admin.STUDENT_COURSE_MANAGE)
    @PutMapping("/allowTeacherGrade")
    public ResultVO setAllowTeacherGrade(@RequestBody @Validated BoolOptionVO option) {
        return service.setAllowTeacherGrade(option.getOption());
    }
}
