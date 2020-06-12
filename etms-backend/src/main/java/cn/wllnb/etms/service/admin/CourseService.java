package cn.wllnb.etms.service.admin;

import cn.wllnb.etms.mapper.CourseMapper;
import cn.wllnb.etms.mapper.OpenedCourseMapper;
import cn.wllnb.etms.model.entities.CourseEntity;
import cn.wllnb.etms.model.entities.OpenedCourseEntity;
import cn.wllnb.etms.model.vo.response.IdNameVO;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.model.vo.response.table.CourseItemVO;
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
public class CourseService extends BaseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private OpenedCourseMapper openedCourseMapper;

    public ResultVO getPageCount(String departmentName, String name) {
        int count = courseMapper.count(departmentName, name);
        return result(PageUtils.calcPageCount(count, PageUtils.PAGE_SIZE));
    }

    public ResultVO getPage(Integer index, String departmentName, String name) {
        List<CourseItemVO> courseItemVOList = courseMapper.getPage(
                new Page<>(index, PageUtils.PAGE_SIZE), departmentName, name).getRecords();
        return result(courseItemVOList);
    }

    public ResultVO get(Integer number) {
        CourseEntity entity = courseMapper.selectOne(new LambdaQueryWrapper<CourseEntity>().eq(CourseEntity::getNumber, number));
        if (entity == null) {
            return failedResult("课程号" + number + "不存在!");
        }

        return result(entity);
    }

    public ResultVO update(CourseEntity entity) {
        CourseEntity entity1 = courseMapper.selectOne(new LambdaQueryWrapper<CourseEntity>()
                .eq(CourseEntity::getNumber, entity.getNumber()));
        if (entity1 != null) {
            if (!entity1.getId().equals(entity.getId())) {
                return failedResult("课程号" + entity.getNumber() + "已存在!");
            }
        }
        CourseEntity entity2 = courseMapper.selectOne(new LambdaQueryWrapper<CourseEntity>()
                .eq(CourseEntity::getName, entity.getName()));
        if (entity2 != null) {
            if (!entity2.getId().equals(entity.getId())) {
                return failedResult("课程名" + entity.getName() + "已存在!");
            }
        }
        System.out.println(entity);
        courseMapper.updateById(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer number) {
        CourseEntity entity = courseMapper.selectOne(new LambdaQueryWrapper<CourseEntity>().eq(CourseEntity::getNumber, number));
        if (entity == null) {
            return failedResult("课程号" + number + "不存在!");
        }
        if (openedCourseMapper.selectCount(new LambdaQueryWrapper<OpenedCourseEntity>()
                .eq(OpenedCourseEntity::getCourseId, entity.getId())) > 0) {
            return failedResult("此课程尚有正在开办的课程!");
        }

        courseMapper.delete(new LambdaQueryWrapper<CourseEntity>().eq(CourseEntity::getNumber, number));
        return result("删除成功");
    }

    public ResultVO create(CourseEntity entity) {
        if (courseMapper.selectOne(new LambdaQueryWrapper<CourseEntity>()
                .eq(CourseEntity::getNumber, entity.getNumber())) != null) {
            return failedResult("课程号" + entity.getNumber() + "已存在!");
        }
        if (courseMapper.selectOne(new LambdaQueryWrapper<CourseEntity>()
                .eq(CourseEntity::getName, entity.getName())) != null) {
            return failedResult("课程名" + entity.getName() + "已存在!");
        }

        System.out.println(entity);
        courseMapper.insert(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<CourseEntity> entityList = courseMapper.selectList(new LambdaQueryWrapper<CourseEntity>()
                .select(CourseEntity::getId, CourseEntity::getName));
        for (CourseEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }
        return result(voList);
    }

}
