package cn.wllnb.etms.mapper;

import cn.wllnb.etms.model.entities.TeacherEntity;
import cn.wllnb.etms.model.vo.response.table.TeacherCourseItemVO;
import cn.wllnb.etms.model.vo.response.table.TeacherItemVO;
import cn.wllnb.etms.model.vo.response.table.TimetableItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper extends BaseMapper<TeacherEntity> {
    Integer count(String departmentName, String name);

    IPage<TeacherItemVO> getPage(IPage<TeacherItemVO> page, String departmentName, String name);

    List<TimetableItemVO> listTeacherTimetable(Integer teacherNumber);

    List<TeacherCourseItemVO> listTeacherCourse(Integer teacherNumber);
}
