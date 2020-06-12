package cn.wllnb.etms.controller.student;

import cn.wllnb.etms.authentication.annotation.Student;
import cn.wllnb.etms.controller.BaseController;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.student.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WLL
 */
@Student
@RestController
@RequestMapping("/student/exam")
public class ExamController extends BaseController {

    @Autowired
    private ExamService service;


    @RequestMapping("/list")
    public ResultVO list() {
        return service.listStudentExam();
    }

}
