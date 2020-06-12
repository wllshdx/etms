package cn.wllnb.etms.service.admin;

import cn.wllnb.etms.mapper.ClassMapper;
import cn.wllnb.etms.mapper.DepartmentMapper;
import cn.wllnb.etms.mapper.MajorMapper;
import cn.wllnb.etms.model.entities.ClassEntity;
import cn.wllnb.etms.model.entities.DepartmentEntity;
import cn.wllnb.etms.model.entities.MajorEntity;
import cn.wllnb.etms.model.vo.response.IdNameVO;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.model.vo.response.table.MajorItemVO;
import cn.wllnb.etms.service.BaseService;
import cn.wllnb.etms.utils.PageUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class MajorService extends BaseService {
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    public ResultVO getPageCount(String departmentName, String name) {
        int count = majorMapper.count(departmentName, name);
        return result(PageUtils.calcPageCount(count, PageUtils.PAGE_SIZE));
    }

    @Transactional
    public ResultVO getPage(Integer index, String departmentName, String name) {
        List<MajorItemVO> majorItemVOList = majorMapper.getPage(new Page<>(index, PageUtils.PAGE_SIZE),
                departmentName, name).getRecords();
        return result(majorItemVOList);
    }

    public ResultVO get(Integer number) {
        MajorEntity entity = majorMapper.selectOne(
                new LambdaQueryWrapper<MajorEntity>().eq(MajorEntity::getNumber, number));
        if (entity == null) {
            return failedResult("专业号[ " + number + "]不存在!");
        }
        return result(entity);
    }

    public ResultVO update(MajorEntity entity) {
        MajorEntity entity1 = majorMapper.selectOne(new LambdaQueryWrapper<MajorEntity>().eq(MajorEntity::getNumber, entity.getNumber()));
        if (entity1!=null){
            if (!entity1.getId().equals(entity.getId())) {
                return failedResult("专业号" + entity.getNumber() + "已存在!");
            }
        }
        MajorEntity entity2 = majorMapper.selectOne(new LambdaQueryWrapper<MajorEntity>().eq(MajorEntity::getName, entity.getName()));
        if(entity2 != null){
            if (!entity2.getId().equals(entity.getId())) {
                return failedResult("专业名[" + entity.getName() + "]已存在!");
            }
        }
        if (departmentMapper.selectById(entity.getDepartmentId()) == null) {
            return failedResult("所属学院号不存在!");
        }
        majorMapper.updateById(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer number) {
        MajorEntity majorEntity = majorMapper.selectOne(
                new LambdaQueryWrapper<MajorEntity>().eq(MajorEntity::getNumber, number));
        if (majorEntity == null) {
            return failedResult("专业号" + number + "不存在!");
        }
        if (classMapper.selectCount(new LambdaQueryWrapper<ClassEntity>()
                .eq(ClassEntity::getMajorId, majorEntity.getId())) > 0) {
            return failedResult("此专业中还有班级未被删除");
        }

        majorMapper.delete(new LambdaQueryWrapper<MajorEntity>().eq(MajorEntity::getNumber, number));
        return result("删除成功");
    }

    public ResultVO create(MajorEntity entity) {
        if (majorMapper.selectOne(
                new LambdaQueryWrapper<MajorEntity>().eq(MajorEntity::getNumber, entity.getNumber())) != null) {
            return failedResult("专业号" + entity.getNumber() + "已存在!");
        }
        if (majorMapper.selectOne(
                new LambdaQueryWrapper<MajorEntity>().eq(MajorEntity::getName, entity.getName())) != null) {
            return failedResult("专业名[" + entity.getName() + "]已存在!");
        }
        if (departmentMapper.selectById(entity.getDepartmentId()) == null) {
            return failedResult("所属学院号不存在!");
        }
        majorMapper.insert(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<MajorEntity> entityList = majorMapper.selectList(new LambdaQueryWrapper<>());
        for (MajorEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }
        return result(voList);
    }
}
