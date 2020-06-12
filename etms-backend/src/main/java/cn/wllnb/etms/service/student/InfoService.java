package cn.wllnb.etms.service.student;

import cn.wllnb.etms.mapper.StudentMapper;
import cn.wllnb.etms.model.entities.StudentEntity;
import cn.wllnb.etms.model.vo.request.StudentInfoFormVO;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.model.vo.response.StudentInfoVO;
import cn.wllnb.etms.service.BaseService;
import cn.wllnb.etms.utils.Md5Encrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author WLL
 */
@Service
public class InfoService extends BaseService {

    @Autowired
    private StudentMapper studentMapper;

    public ResultVO get() {
        StudentInfoVO studentInfoVO = studentMapper.getStudentInfoById(getUserId());
        return result(studentInfoVO);
    }

    public ResultVO update(@RequestBody @Validated StudentInfoFormVO studentInfoForm) {
        StudentEntity student = studentMapper.selectOne(
                new LambdaQueryWrapper<StudentEntity>().eq(StudentEntity::getNumber, getUserId()));

        String password = studentInfoForm.getPassword();
        if (password == null || "".equals(password)) {
            password = student.getPassword();
        } else {
            password = Md5Encrypt.computePasswordHash(password);
        }

        BeanUtils.copyProperties(studentInfoForm, student);
        student.setPassword(password);
        studentMapper.updateById(student);

        return result("更新成功");
    }
}
