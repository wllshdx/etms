package cn.wllnb.etms.dao.manage;

import cn.wllnb.etms.common.constants.UserType;
import cn.wllnb.etms.mapper.AdminMapper;
import cn.wllnb.etms.mapper.StudentMapper;
import cn.wllnb.etms.mapper.TeacherMapper;
import cn.wllnb.etms.model.bo.AuthInfoBO;
import cn.wllnb.etms.model.entities.AdminEntity;
import cn.wllnb.etms.model.entities.StudentEntity;
import cn.wllnb.etms.model.entities.TeacherEntity;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author WLL
 */
@Component
public class UserManage {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentMapper studentMapper;

    public AuthInfoBO getAuthInfoByUsername(String userInfo, Integer userType) {
        if (userType == UserType.STUDENT) {
            StudentEntity entity = studentMapper.selectOne(
                    new LambdaQueryWrapper<StudentEntity>()
                            .eq(StudentEntity::getNumber, userInfo));
            return AuthInfoBO.fromStudent(entity);
        } else if (userType == UserType.TEACHER) {
            return AuthInfoBO.fromTeacher(teacherMapper.selectOne(
                    new LambdaQueryWrapper<TeacherEntity>()
                            .eq(TeacherEntity::getNumber, userInfo)));
        } else if (userType == UserType.ADMIN) {
            return AuthInfoBO.fromAdmin(adminMapper.selectOne(
                    new LambdaQueryWrapper<AdminEntity>()
                            .eq(AdminEntity::getUsername, userInfo)));
        }
        return null;
    }

    public void updateStudentLastLoginTime(String number) {
        StudentEntity entity = studentMapper.selectOne(
                new LambdaQueryWrapper<StudentEntity>()
                        .eq(StudentEntity::getNumber, number));
        if (entity == null) {
            return;
        }
        entity.setLastLoginTime(new Date());
        studentMapper.updateById(entity);
    }
}
