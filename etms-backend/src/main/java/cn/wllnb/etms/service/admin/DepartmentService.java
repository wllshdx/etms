package cn.wllnb.etms.service.admin;

import cn.wllnb.etms.mapper.DepartmentMapper;
import cn.wllnb.etms.mapper.MajorMapper;
import cn.wllnb.etms.mapper.TeacherMapper;
import cn.wllnb.etms.model.entities.DepartmentEntity;
import cn.wllnb.etms.model.entities.MajorEntity;
import cn.wllnb.etms.model.entities.TeacherEntity;
import cn.wllnb.etms.model.vo.response.IdNameVO;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.model.vo.response.table.DepartmentItemVO;
import cn.wllnb.etms.service.BaseService;
import cn.wllnb.etms.utils.PageUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WLL
 */
@Service
public class DepartmentService extends BaseService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    public ResultVO getPageCount(String name) {
        int count = departmentMapper.selectCount(new LambdaQueryWrapper<DepartmentEntity>()
                .like(name != null, DepartmentEntity::getName, name));
        return result(PageUtils.calcPageCount(count, PageUtils.PAGE_SIZE));
    }

    @Transactional
    public ResultVO getPage(Integer index, String namePart) {
        List<DepartmentItemVO> departmentItemList = new ArrayList<>();

        List<DepartmentEntity> departmentList = departmentMapper.selectPage(
                new Page<>(index, PageUtils.PAGE_SIZE),
                new LambdaQueryWrapper<DepartmentEntity>()
                        .like(namePart != null, DepartmentEntity::getName, namePart)
        ).getRecords();

        departmentList.forEach(departmentEntity -> {
            int id = departmentEntity.getId();
            departmentItemList.add(new DepartmentItemVO(
                            id,
                            departmentEntity.getNumber(),
                            departmentEntity.getName(),
                            departmentEntity.getAddress(),
                            departmentEntity.getTelephone(),
                            majorMapper.selectCount(new LambdaQueryWrapper<MajorEntity>()
                                    .eq(MajorEntity::getDepartmentId, id)),
                            teacherMapper.selectCount(
                                    new LambdaQueryWrapper<TeacherEntity>()
                                            .eq(TeacherEntity::getDepartmentId, id))
                    )
            );
        });
        return result(departmentItemList);
    }

    public ResultVO get(Integer number) {
        DepartmentEntity entity = departmentMapper.selectOne(
                new LambdaQueryWrapper<DepartmentEntity>().eq(DepartmentEntity::getNumber, number));
        if (entity == null) {
            return failedResult("学院号[" + number + "]不存在!");
        }
        return result(entity);
    }

    public ResultVO update(DepartmentEntity entity) {
        DepartmentEntity entity1 = departmentMapper.selectOne(
                new LambdaQueryWrapper<DepartmentEntity>().eq(DepartmentEntity::getNumber, entity.getNumber()));
        if (entity1 != null) {
            if (!entity1.getId().equals(entity.getId())) {
                return failedResult("学院号[ " + entity.getNumber() + "]已存在!");
            }
        }
        DepartmentEntity entity2 = departmentMapper.selectOne(
                new LambdaQueryWrapper<DepartmentEntity>().eq(DepartmentEntity::getName, entity.getName()));
        if (entity2 != null) {
            if (!entity2.getId().equals(entity.getId())) {
                return failedResult("学院名[ " + entity.getName() + "]已存在!");
            }
        }
        departmentMapper.updateById(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer number) {
        if (departmentMapper.selectOne(
                new LambdaQueryWrapper<DepartmentEntity>().eq(DepartmentEntity::getNumber, number)) == null) {
            return failedResult("学院号[ " + number + "]不存在!");
        }
        if (majorMapper.selectCount(new LambdaQueryWrapper<MajorEntity>()
                .eq(MajorEntity::getDepartmentId, number)) > 0) {
            return failedResult("此学院中还有专业未被删除");
        }
        if (teacherMapper.selectCount(
                new LambdaQueryWrapper<TeacherEntity>()
                        .eq(TeacherEntity::getDepartmentId, number)) > 0) {
            return failedResult("此学院中还有教师未被删除");
        }
        departmentMapper.delete(new LambdaQueryWrapper<DepartmentEntity>().eq(DepartmentEntity::getNumber, number));
        return result("删除成功");
    }

    public ResultVO create(DepartmentEntity entity) {
        if (departmentMapper.selectOne(
                new LambdaQueryWrapper<DepartmentEntity>().eq(DepartmentEntity::getNumber, entity.getNumber())) != null) {
            return failedResult("学院号[ " + entity.getNumber() + "]已存在!");
        }
        if (departmentMapper.selectOne(
                new LambdaQueryWrapper<DepartmentEntity>().eq(DepartmentEntity::getName, entity.getName())) != null) {
            return failedResult("学院名[" + entity.getName() + "]已存在!");
        }

        departmentMapper.insert(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<DepartmentEntity> entityList = departmentMapper.selectList(new LambdaQueryWrapper<>());
        entityList.forEach(entity -> {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        });
        return result(voList);
    }

}
