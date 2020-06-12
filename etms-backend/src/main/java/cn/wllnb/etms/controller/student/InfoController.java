package cn.wllnb.etms.controller.student;

import cn.wllnb.etms.authentication.annotation.Student;
import cn.wllnb.etms.controller.BaseController;
import cn.wllnb.etms.model.vo.request.StudentInfoFormVO;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.student.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author WLL
 */
@Student
@RestController
@RequestMapping("/student/info")
public class InfoController extends BaseController {

    @Autowired
    private InfoService service;

    @GetMapping
    public ResultVO get() {
        return service.get();
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated StudentInfoFormVO formVO) {
        return service.update(formVO);
    }
}
