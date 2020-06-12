package cn.wllnb.etms.service.admin;

import cn.wllnb.etms.mapper.CourseMapper;
import cn.wllnb.etms.mapper.OpenedCourseMapper;
import cn.wllnb.etms.mapper.StudentCourseMapper;
import cn.wllnb.etms.mapper.TeacherMapper;
import cn.wllnb.etms.model.bo.OpenedCourseItemBO;
import cn.wllnb.etms.model.entities.OpenedCourseEntity;
import cn.wllnb.etms.model.entities.StudentCourseEntity;
import cn.wllnb.etms.model.vo.response.OpenedCourseVO;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.model.vo.response.table.OpenedCourseItemVO;
import cn.wllnb.etms.service.BaseService;
import cn.wllnb.etms.utils.LessonTimeConverter;
import cn.wllnb.etms.utils.PageUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WLL
 */
@Service
public class OpenedCourseService extends BaseService {

    @Autowired
    private OpenedCourseMapper openedCourseMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private LessonTimeConverter lessonTimeConverter;

    public ResultVO getPageCount(String departmentName, String teacherName, String name) {
        int count = openedCourseMapper.count(departmentName, teacherName, name);
        return result(PageUtils.calcPageCount(count, PageUtils.PAGE_SIZE));
    }

    public ResultVO getPage(Integer index, String departmentName, String teacherName, String name) {
        List<OpenedCourseItemBO> boList = openedCourseMapper.getPage(
                new Page<>(index, PageUtils.PAGE_SIZE), departmentName, teacherName, name).getRecords();
        List<OpenedCourseItemVO> voList = new ArrayList<>(boList.size());

        for (OpenedCourseItemBO bo : boList) {
            OpenedCourseItemVO vo = new OpenedCourseItemVO();
            BeanUtils.copyProperties(bo, vo);
            vo.setTime(lessonTimeConverter.covertTimePart(bo.getTime()));
            voList.add(vo);
        }
        return result(voList);
    }

    public ResultVO get(Integer id) {
        OpenedCourseEntity entity = openedCourseMapper.selectById(id);
        if (entity == null) {
            return failedResult("开课Id" + id + "不存在!");
        }

        return result(entity);
    }

    public ResultVO update(OpenedCourseEntity entity) {
        OpenedCourseEntity origin = openedCourseMapper.selectById(entity.getId());
        if (origin == null) {
            return failedResult("开课Id" + entity.getId() + "不存在!");
        }
        if (courseMapper.selectById(entity.getCourseId()) == null) {
            return failedResult("课程不存在!");
        }
        if (teacherMapper.selectById(entity.getTeacherId()) == null) {
            return failedResult("授课教师不存在!");
        }

        entity.setSelectedCount(origin.getSelectedCount());

        System.out.println(entity);
        openedCourseMapper.updateById(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (openedCourseMapper.selectById(id) == null) {
            return failedResult("开课Id" + id + "不存在!");
        }
        int count = studentCourseMapper.selectCount(new LambdaQueryWrapper<StudentCourseEntity>()
                .eq(StudentCourseEntity::getOpenedCourseId, id));
        if (count > 0) {
            return failedResult("还有学生未退选此课程");
        }

        openedCourseMapper.deleteById(id);
        return result("删除成功");
    }

    public ResultVO create(OpenedCourseEntity entity) {
        if (courseMapper.selectById(entity.getCourseId()) == null) {
            return failedResult("课程不存在!");
        }
        if (teacherMapper.selectById(entity.getTeacherId()) == null) {
            return failedResult("授课教师不存在!");
        }
        System.out.println(entity);
        openedCourseMapper.insert(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        List<OpenedCourseVO> openedCourseVOList = openedCourseMapper.listName();
        openedCourseVOList.forEach(openedCourseVO -> {
            openedCourseVO.setTime(lessonTimeConverter.covertTimePart(openedCourseVO.getTime()));
        });
        return result(openedCourseVOList);
    }

}
