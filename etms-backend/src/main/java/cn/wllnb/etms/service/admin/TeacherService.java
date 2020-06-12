package cn.wllnb.etms.service.admin;

import cn.wllnb.etms.mapper.DepartmentMapper;
import cn.wllnb.etms.mapper.OpenedCourseMapper;
import cn.wllnb.etms.mapper.TeacherMapper;
import cn.wllnb.etms.model.entities.OpenedCourseEntity;
import cn.wllnb.etms.model.entities.TeacherEntity;
import cn.wllnb.etms.model.vo.response.IdNameVO;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.model.vo.response.table.TeacherItemVO;
import cn.wllnb.etms.service.BaseService;
import cn.wllnb.etms.utils.Md5Encrypt;
import cn.wllnb.etms.utils.PageUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TeacherService extends BaseService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private OpenedCourseMapper openedCourseMapper;

    public ResultVO getPageCount(String departmentName, String name) {
        int count = teacherMapper.count(departmentName, name);
        return result(PageUtils.calcPageCount(count, PageUtils.PAGE_SIZE));
    }

    public ResultVO getPage(Integer index, String departmentName, String name) {
        List<TeacherItemVO> teacherItemVOList = teacherMapper.getPage(new Page<>(index, PageUtils.PAGE_SIZE), departmentName, name).getRecords();
        return result(teacherItemVOList);
    }

    public ResultVO get(Integer number) {
        TeacherEntity entity = teacherMapper.selectOne(new LambdaQueryWrapper<TeacherEntity>().eq(TeacherEntity::getNumber, number));
        if (entity == null) {
            return failedResult("教师号" + number + "不存在!");
        }

        entity.setPassword("");
        return result(entity);
    }

    public ResultVO update(TeacherEntity entity) {
        TeacherEntity originEntity = teacherMapper.selectOne(new LambdaQueryWrapper<TeacherEntity>().eq(TeacherEntity::getNumber, entity.getNumber()));
        if (originEntity == null) {
            return failedResult("教师号" + entity.getNumber() + "不存在!");
        }
        if (departmentMapper.selectById(entity.getDepartmentId()) == null) {
            return failedResult("所属学院不存在!");
        }

        if ("".equals(entity.getPassword())) {
            entity.setPassword(originEntity.getPassword());
        } else {
            entity.setPassword(Md5Encrypt.computePasswordHash(entity.getPassword()));
        }
        System.out.println(entity);
        teacherMapper.updateById(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer number) {
        TeacherEntity entity = teacherMapper.selectOne(new LambdaQueryWrapper<TeacherEntity>().eq(TeacherEntity::getNumber, number));
        if (entity == null) {
            return failedResult("教师号" + number + "不存在!");
        }
        int count = openedCourseMapper.selectCount(new LambdaQueryWrapper<OpenedCourseEntity>()
                .eq(OpenedCourseEntity::getTeacherId, entity.getId()));
        if (count > 0) {
            return failedResult("此教师还有教授的课程未被删除");
        }

        teacherMapper.delete(new LambdaQueryWrapper<TeacherEntity>().eq(TeacherEntity::getNumber, number));
        return result("删除成功");
    }

    public ResultVO create(TeacherEntity entity) {
        if (teacherMapper.selectOne(new LambdaQueryWrapper<TeacherEntity>().eq(TeacherEntity::getNumber, entity.getNumber())) != null) {
            return failedResult("教师号" + entity.getNumber() + "已存在!");
        }
        if (departmentMapper.selectById(entity.getDepartmentId()) == null) {
            return failedResult("所属学院" + entity.getDepartmentId() + "不存在!");
        }

        entity.setPassword(Md5Encrypt.computePasswordHash(entity.getPassword()));
        System.out.println(entity);
        teacherMapper.insert(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<TeacherEntity> entityList = teacherMapper.selectList(new LambdaQueryWrapper<>());
        entityList.forEach(teacherEntity -> {
            voList.add(new IdNameVO(teacherEntity.getId(), teacherEntity.getName()));
        });
        return result(voList);
    }
}
