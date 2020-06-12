package cn.wllnb.etms.model.entities;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author WLL
 */
@Data
@TableName("student_course")
public class StudentCourseEntity {
    public static final String ID = "sc_id";
    public static final String STUDENT_ID = "sc_student_id";
    public static final String OPENED_COURSE_ID = "oc_id";
    public static final String DAILY_SCORE = "sc_daily_score";
    public static final String EXAM_SCORE = "sc_exam_score";
    public static final String SCORE = "sc_score";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "必须选择学生")
    @TableField(STUDENT_ID)
    private Integer studentId;

    @NotNull(message = "必须选择课程")
    @TableField(OPENED_COURSE_ID)
    private Integer openedCourseId;

    @Range(min = 0, max = 100, message = "分数必须在0-100之间")
    @TableField(value = DAILY_SCORE, updateStrategy = FieldStrategy.IGNORED)
    private Integer dailyScore;

    @Range(min = 0, max = 100, message = "分数必须在0-100之间")
    @TableField(value = EXAM_SCORE, updateStrategy = FieldStrategy.IGNORED)
    private Integer examScore;

    @Range(min = 0, max = 100, message = "分数必须在0-100之间")
    @TableField(value = SCORE, updateStrategy = FieldStrategy.IGNORED)
    private Integer score;
}
