package cn.wllnb.etms.service.admin;

import cn.wllnb.etms.mapper.ClassMapper;
import cn.wllnb.etms.mapper.StudentCourseMapper;
import cn.wllnb.etms.mapper.StudentMapper;
import cn.wllnb.etms.model.entities.StudentCourseEntity;
import cn.wllnb.etms.model.entities.StudentEntity;
import cn.wllnb.etms.model.vo.response.IdNameVO;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.model.vo.response.table.StudentItemVO;
import cn.wllnb.etms.service.BaseService;
import cn.wllnb.etms.utils.Md5Encrypt;
import cn.wllnb.etms.utils.PageUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WLL
 */
@Service
public class StudentService extends BaseService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private StudentCourseMapper studentCourseMapper;

    public ResultVO getPageCount(String majorName, String className, String name) {
        int count = studentMapper.count(majorName, className, name);
        return result(PageUtils.calcPageCount(count, PageUtils.PAGE_SIZE));
    }

    public ResultVO getPage(Integer index, String majorName, String className, String name) {
        List<StudentItemVO> studentItemVOList = studentMapper.getPage(
                new Page<>(index, PageUtils.PAGE_SIZE), majorName, className, name).getRecords();
        return result(studentItemVOList);
    }

    public ResultVO get(Integer number) {
        StudentEntity entity = studentMapper.selectOne(new LambdaQueryWrapper<StudentEntity>().eq(StudentEntity::getNumber, number));
        if (entity == null) {
            return failedResult("学生号" + number + "不存在!");
        }
        entity.setPassword("");
        return result(entity);
    }

    public ResultVO update(StudentEntity entity) {
        StudentEntity origin = studentMapper.selectById(entity.getId());
        if (origin == null) {
            return failedResult("学生号" + entity.getNumber() + "不存在!");
        }
        if (classMapper.selectById(entity.getClassId()) == null) {
            return failedResult("所属班级号" + entity.getClassId() + "不存在!");
        }

        if ("".equals(entity.getPassword())) {
            entity.setPassword(origin.getPassword());
        } else {
            entity.setPassword(Md5Encrypt.computePasswordHash(entity.getPassword()));
        }
        studentMapper.updateById(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer number) {
        StudentEntity studentEntity = studentMapper.selectOne(new LambdaQueryWrapper<StudentEntity>().eq(StudentEntity::getNumber, number));
        if (studentEntity == null) {
            return failedResult("学生号" + number + "不存在!");
        }
        System.out.println(studentEntity);
        int count = studentCourseMapper.selectCount(
                new LambdaQueryWrapper<StudentCourseEntity>()
                        .eq(StudentCourseEntity::getStudentId, studentEntity.getId()));
        List<StudentCourseEntity> studentCourseEntityList = studentCourseMapper.selectList(new LambdaQueryWrapper<>());
        System.out.println(studentCourseEntityList);
        System.out.println(count);
        if ( count > 0) {
            return failedResult("此学生还有未退选课程");
        }

        studentMapper.delete(new LambdaQueryWrapper<StudentEntity>().eq(StudentEntity::getNumber, number));
        return result("删除成功");
    }

    public ResultVO create(StudentEntity entity) {
        if (studentMapper.selectOne(new LambdaQueryWrapper<StudentEntity>().eq(StudentEntity::getNumber, entity.getNumber())) != null) {
            return failedResult("学生号" + entity.getNumber() + "不存在!");
        }
        if (classMapper.selectById(entity.getClassId()) == null) {
            return failedResult("所属班级号" + entity.getClassId() + "不存在!");
        }

        entity.setLastLoginTime(new Date());
        System.out.println(entity);
        studentMapper.insert(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<StudentEntity> entityList = studentMapper.selectList(new LambdaQueryWrapper<>());
        entityList.forEach(studentEntity -> {
            voList.add(new IdNameVO(studentEntity.getId(), studentEntity.getName()));
        });
        return result(voList);
    }
}
