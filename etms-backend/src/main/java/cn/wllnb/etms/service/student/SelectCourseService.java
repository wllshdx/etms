package cn.wllnb.etms.service.student;

import cn.wllnb.etms.dao.option.OptionDAO;
import cn.wllnb.etms.mapper.CourseMapper;
import cn.wllnb.etms.mapper.OpenedCourseMapper;
import cn.wllnb.etms.mapper.StudentCourseMapper;
import cn.wllnb.etms.mapper.StudentMapper;
import cn.wllnb.etms.model.bo.StudentCourseSelectItemBO;
import cn.wllnb.etms.model.entities.CourseEntity;
import cn.wllnb.etms.model.entities.OpenedCourseEntity;
import cn.wllnb.etms.model.entities.StudentCourseEntity;
import cn.wllnb.etms.model.entities.StudentEntity;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.model.vo.response.table.StudentCourseSelectItemVO;
import cn.wllnb.etms.service.BaseService;
import cn.wllnb.etms.utils.LessonTimeConverter;
import cn.wllnb.etms.utils.PageUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WLL
 */
@Service
public class SelectCourseService extends BaseService {
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private OpenedCourseMapper openedCourseMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private OptionDAO optionDAO;
    @Autowired
    private LessonTimeConverter lessonTimeConverter;

    public ResultVO getPageCount(String departmentName, String courseName, String teacherName) {
        Integer studentNumber = getUserId();
        int count = openedCourseMapper.countStudentCanSelect(studentNumber, departmentName, courseName, teacherName);
        return result(PageUtils.calcPageCount(count, PageUtils.PAGE_SIZE));
    }

    public ResultVO getPage(Integer index, String departmentName, String courseName, String teacherName) {
        Integer studentNumber = getUserId();

        List<StudentCourseSelectItemBO> boList = openedCourseMapper.getStudentCanSelectPage(
                new Page<>(index, PageUtils.PAGE_SIZE), studentNumber, departmentName, courseName, teacherName).getRecords();

        List<StudentCourseSelectItemVO> voList = new ArrayList<>(boList.size());

        for (StudentCourseSelectItemBO bo : boList) {
            StudentCourseSelectItemVO vo = new StudentCourseSelectItemVO();
            BeanUtils.copyProperties(bo, vo);
            vo.setTime(lessonTimeConverter.covertTimePart(bo.getTime()));
            voList.add(vo);
        }

        return result(voList);
    }

    @Transactional
    public ResultVO create(Integer openedCourseId) {
        Integer studentNumber = getUserId();

        if (!optionDAO.getAllowStudentSelect()) {
            return failedResult("现在不是选课时间!");
        }
        StudentEntity student = studentMapper.selectOne(new LambdaQueryWrapper<StudentEntity>().eq(StudentEntity::getNumber, studentNumber));
        System.out.println(student);
        if (student == null) {
            return failedResult("学生Id:" + studentNumber + "不存在!");
        }
        OpenedCourseEntity openedCourseEntity = openedCourseMapper.selectById(openedCourseId);
        if (openedCourseEntity == null) {
            return failedResult("开课Id:" + openedCourseId + "不存在!");
        }
        System.out.println(openedCourseEntity);
        if (openedCourseEntity.getSelectedCount() >= openedCourseEntity.getMaxSize()) {
            return failedResult("课容量已满!");
        }
        String timePart = splitTimePart(openedCourseEntity.getTime());
        if (studentCourseMapper.countStudentCourseSelectedByTimePart(studentNumber, timePart) > 0) {
            return failedResult("上课时间冲突!");
        }

        StudentCourseEntity studentCourse = new StudentCourseEntity();
        studentCourse.setStudentId(student.getId());
        studentCourse.setOpenedCourseId(openedCourseId);

        studentCourseMapper.insert(studentCourse);

        return result("选课成功");
    }

    private String splitTimePart(String time) {
        String[] spilt = time.split("-");
        return spilt[0] + "-" + spilt[1];
    }
}
