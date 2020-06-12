package cn.wllnb.etms.mapper;

import cn.wllnb.etms.model.entities.ClassEntity;
import cn.wllnb.etms.model.vo.response.table.ClassItemVO;
import cn.wllnb.etms.model.vo.response.table.MajorItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

/**
 * @author WLL
 */
@Repository
public interface ClassMapper extends BaseMapper<ClassEntity> {
    Integer count(String departmentName, String majorName, String name);

    IPage<ClassItemVO> getPage(IPage<ClassItemVO> page, String departmentName, String majorName, String name);
}