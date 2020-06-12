package cn.wllnb.etms.mapper;

import cn.wllnb.etms.model.entities.MajorEntity;
import cn.wllnb.etms.model.vo.response.table.MajorItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

/**
 * @author WLL
 */
@Repository
public interface MajorMapper extends BaseMapper<MajorEntity> {
    Integer count(String departmentName, String name);

    IPage<MajorItemVO> getPage(IPage<MajorItemVO> page, String departmentName, String name);
}
