package cn.wllnb.etms.mapper;


import cn.wllnb.etms.model.bo.OpenedCourseItemBO;
import cn.wllnb.etms.model.bo.StudentCourseSelectItemBO;
import cn.wllnb.etms.model.entities.OpenedCourseEntity;
import cn.wllnb.etms.model.vo.response.OpenedCourseVO;
import cn.wllnb.etms.model.vo.response.table.OpenedCourseItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WLL
 */
@Repository
public interface OpenedCourseMapper extends BaseMapper<OpenedCourseEntity> {
    Integer count(String departmentName, String teacherName, String name);

    IPage<OpenedCourseItemBO> getPage(IPage<OpenedCourseItemBO> page, String departmentName, String teacherName, String name);

    List<OpenedCourseVO> listName();

    Integer countStudentCanSelect(Integer studentNumber, String departmentName, String courseName, String teacherName);

    IPage<StudentCourseSelectItemBO> getStudentCanSelectPage(IPage<StudentCourseSelectItemBO> page, Integer studentNumber, String departmentName, String courseName, String teacherName);


}
