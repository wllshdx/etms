package cn.wllnb.etms.service.admin;

import cn.wllnb.etms.mapper.CourseMapper;
import cn.wllnb.etms.mapper.OpenedCourseMapper;
import cn.wllnb.etms.mapper.StudentCourseMapper;
import cn.wllnb.etms.mapper.StudentMapper;
import cn.wllnb.etms.model.entities.CourseEntity;
import cn.wllnb.etms.model.entities.OpenedCourseEntity;
import cn.wllnb.etms.model.entities.StudentCourseEntity;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.model.vo.response.table.StudentCourseItemVO;
import cn.wllnb.etms.service.BaseService;
import cn.wllnb.etms.utils.PageUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WLL
 */
@Service
public class StudentCourseService extends BaseService {
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private OpenedCourseMapper openedCourseMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StudentMapper studentMapper;

    public ResultVO getPageCount(String courseName, String studentName, String className) {
        int count = studentCourseMapper.count(courseName, studentName, className);
        return result(PageUtils.calcPageCount(count, PageUtils.PAGE_SIZE));
    }

    public ResultVO getPage(Integer index, String courseName, String studentName, String className) {
        List<StudentCourseItemVO> studentCourseItemVOList = studentCourseMapper.getPage(
                new Page<>(index, PageUtils.PAGE_SIZE), courseName, studentName, className).getRecords();
        return result(studentCourseItemVOList);
    }

    public ResultVO get(Integer id) {
        StudentCourseEntity entity = studentCourseMapper.selectById(id);
        if (entity == null) {
            return failedResult("学生选课Id: " + id + "不存在!");
        }

        return result(entity);
    }

    public ResultVO update(StudentCourseEntity studentCourseEntity) {
        StudentCourseEntity originEntity = studentCourseMapper.selectById(studentCourseEntity.getId());
        if (originEntity == null) {
            return failedResult("学生选课Id: " + studentCourseEntity.getId() + "不存在!");
        }
        if (!originEntity.getOpenedCourseId().equals(studentCourseEntity.getOpenedCourseId())) {
            return failedResult("课程Id被篡改");
        }
        if (!originEntity.getStudentId().equals(studentCourseEntity.getStudentId())) {
            return failedResult("学生Id被篡改");
        }
        if (studentCourseEntity.getDailyScore() == null && originEntity.getDailyScore() != null) {
            studentCourseEntity.setDailyScore(originEntity.getDailyScore());
        }
        if (studentCourseEntity.getExamScore() == null && originEntity.getExamScore() != null) {
            studentCourseEntity.setExamScore(originEntity.getExamScore());
        }
        if (studentCourseEntity.getDailyScore() != null && studentCourseEntity.getExamScore() != null) {
            studentCourseEntity.setScore(studentCourseEntity.getDailyScore() + studentCourseEntity.getExamScore());
        }
        studentCourseMapper.updateById(studentCourseEntity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        StudentCourseEntity entity = studentCourseMapper.selectById(id);
        if (entity == null) {
            return failedResult("学生选课Id: " + id + "不存在!");
        }
        OpenedCourseEntity openedCourseEntity = openedCourseMapper.selectById(entity.getOpenedCourseId());
        openedCourseEntity.decreaseSelectedCount();
        openedCourseMapper.updateById(openedCourseEntity);
        studentCourseMapper.deleteById(entity.getId());
        return result("删除成功");
    }

    public ResultVO create(StudentCourseEntity studentCourseEntity) {
        if (studentCourseMapper.selectById(studentCourseEntity.getId()) != null) {
            return failedResult("学生选课Id: " + studentCourseEntity.getId() + "已存在!");
        }
        if (studentMapper.selectById(studentCourseEntity.getStudentId()) == null) {
            return failedResult("所属学生不存在!");
        }
        OpenedCourseEntity openedCourseEntity = openedCourseMapper.selectById(studentCourseEntity.getOpenedCourseId());
        if (openedCourseEntity.getSelectedCount() >= openedCourseEntity.getMaxSize()) {
            return failedResult("课容量已满");
        }
        CourseEntity courseEntity = courseMapper.selectById(openedCourseEntity.getCourseId());
        if (courseEntity == null) {
            return failedResult("所属课程Id: " + openedCourseEntity.getCourseId() + "不存在!");
        }

        studentCourseMapper.insert(studentCourseEntity);
        return result("添加成功");
    }
}
