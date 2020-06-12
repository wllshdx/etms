package cn.wllnb.etms.mapper;

import cn.wllnb.etms.model.entities.CourseEntity;
import cn.wllnb.etms.model.vo.response.table.CourseItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

/**
 * @author WLL
 */
@Repository
public interface CourseMapper extends BaseMapper<CourseEntity> {

    Integer count(String departmentName, String name);

    IPage<CourseItemVO> getPage(IPage<CourseItemVO> page, String departmentName, String name);

}
