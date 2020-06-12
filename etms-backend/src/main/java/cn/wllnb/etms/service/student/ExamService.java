package cn.wllnb.etms.service.student;

import cn.wllnb.etms.mapper.StudentCourseMapper;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.model.vo.response.table.StudentExamItemVO;
import cn.wllnb.etms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author WLL
 */
@Service
public class ExamService extends BaseService {

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    public ResultVO listStudentExam() {
        List<StudentExamItemVO>  studentExamItemVOList = studentCourseMapper.listStudentExam(getUserId());
        return result(studentExamItemVOList);
    }

}
