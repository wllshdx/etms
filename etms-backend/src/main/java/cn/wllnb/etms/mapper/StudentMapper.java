package cn.wllnb.etms.mapper;

import cn.wllnb.etms.model.entities.StudentEntity;
import cn.wllnb.etms.model.vo.response.StudentInfoVO;
import cn.wllnb.etms.model.vo.response.table.StudentItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

/**
 * @author WLL
 */
@Repository
public interface StudentMapper extends BaseMapper<StudentEntity> {

    Integer getDepartmentIdById(Integer studentId);

    Integer getGradeById(Integer studentId);

    Integer count(String majorName, String className, String name);

    IPage<StudentItemVO> getPage(IPage<StudentItemVO> page, String majorName, String className, String name);

    StudentInfoVO getStudentInfoById(Integer number);

}
