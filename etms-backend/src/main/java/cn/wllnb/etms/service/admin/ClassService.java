package cn.wllnb.etms.service.admin;

import cn.wllnb.etms.mapper.ClassMapper;
import cn.wllnb.etms.mapper.MajorMapper;
import cn.wllnb.etms.mapper.StudentMapper;
import cn.wllnb.etms.model.entities.ClassEntity;
import cn.wllnb.etms.model.entities.StudentEntity;
import cn.wllnb.etms.model.vo.response.IdNameVO;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.model.vo.response.table.ClassItemVO;
import cn.wllnb.etms.model.vo.response.table.MajorItemVO;
import cn.wllnb.etms.service.BaseService;
import cn.wllnb.etms.utils.PageUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WLL
 */
@Service
public class ClassService extends BaseService {
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private MajorMapper majorMapper;

    public ResultVO getPageCount(String departmentName, String majorName, String name) {
        int count = classMapper.count(departmentName, majorName, name);
        return result(PageUtils.calcPageCount(count, PageUtils.PAGE_SIZE));
    }

    public ResultVO getPage(Integer index, String departmentName, String majorName, String name) {
        List<ClassItemVO> majorItemVOList = classMapper.getPage(new Page<>(index, PageUtils.PAGE_SIZE),
                departmentName, majorName, name).getRecords();
        return result(majorItemVOList);
    }

    public ResultVO get(Integer number) {
        ClassEntity entity = classMapper.selectOne(new LambdaQueryWrapper<ClassEntity>().eq(ClassEntity::getNumber, number));
        if (entity == null) {
            return failedResult("班级号" + number + "不存在！");
        }

        return result(entity);
    }

    public ResultVO update(ClassEntity entity) {
        ClassEntity entity1 = classMapper.selectOne(new LambdaQueryWrapper<ClassEntity>().eq(ClassEntity::getNumber, entity.getNumber()));
        if (entity1 != null) {
            if (!entity1.getId().equals(entity.getId())) {
                return failedResult("班级号" + entity.getNumber() + "已存在！");
            }
        }
        ClassEntity entity2 = classMapper.selectOne(new LambdaQueryWrapper<ClassEntity>().eq(ClassEntity::getName, entity.getName()));
        if (entity2 != null) {
            if (!entity2.getId().equals(entity.getId())) {
                return failedResult("班级名" + entity.getName() + "已存在！");
            }
        }
        if (majorMapper.selectById(entity.getMajorId()) == null) {
            return failedResult("所属专业不存在!");
        }

        classMapper.updateById(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer number) {
        ClassEntity entity = classMapper.selectOne(new LambdaQueryWrapper<ClassEntity>().eq(ClassEntity::getNumber, number));
        if (entity == null) {
            return failedResult("班级号" + number + "不存在!");
        }
        if (studentMapper.selectCount(new LambdaQueryWrapper<StudentEntity>().eq(StudentEntity::getClassId, entity.getId())) > 0) {
            return failedResult("此班级中还有学生未被删除");
        }

        classMapper.delete(new LambdaQueryWrapper<ClassEntity>().eq(ClassEntity::getNumber, number));
        return result("删除成功");
    }

    public ResultVO create(ClassEntity entity) {
        if (classMapper.selectOne(new LambdaQueryWrapper<ClassEntity>().eq(ClassEntity::getNumber, entity.getNumber())) != null) {
            return failedResult("班级号: " + entity.getNumber() + "已存在!");
        }
        System.out.println(entity.getMajorId());
        if (majorMapper.selectById(entity.getMajorId()) == null) {
            return failedResult("所属专业Id: " + entity.getMajorId() + "不存在!");
        }
        System.out.println("????");
        System.out.println(entity);
        classMapper.insert(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<ClassEntity> entityList = classMapper.selectList(new LambdaQueryWrapper<ClassEntity>()
                .select(ClassEntity::getId, ClassEntity::getName));
        entityList.forEach(entity -> {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        });
        return result(voList);
    }

}
