package cn.wllnb.etms.model.entities;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author WLL
 * 联合主键：学期、课程号 上课时间（星期几-第几节-几节课）
 */
@Data
@TableName("opened_course")
public class OpenedCourseEntity {
    public static final String ID = "oc_id";
    public static final String TERM = "oc_term";
    public static final String COURSE_ID = "oc_course_id";
    public static final String TEACHER_ID = "oc_teacher_id";
    public static final String TIME = "oc_time";
    public static final String LOCATION = "oc_location";
    public static final String SELECTED_COUNT = "oc_selected_count";
    public static final String MAX_SIZE = "oc_max_size";
    public static final String EXAM_DATE = "course_exam_date";
    public static final String EXAM_LOCATION = "course_exam_location";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "学期不能为空")
    @TableField(TERM)
    private String term;

    @NotNull(message = "课程号不能为空")
    @TableField(COURSE_ID)
    private Integer courseId;

    @NotNull(message = "教师号不能为空")
    @TableField(TEACHER_ID)
    private Integer teacherId;

    @NotBlank(message = "上课时间不能为空")
    @TableField(TIME)
    private String time;

    @NotBlank(message = "上课地址不能为空")
    @TableField(LOCATION)
    private String location;

    @TableField(SELECTED_COUNT)
    private Integer selectedCount;

    @NotNull
    @Range(min = 0, message = "容量不能为负数")
    @TableField(MAX_SIZE)
    private Integer maxSize;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @TableField(value = EXAM_DATE, updateStrategy = FieldStrategy.IGNORED)
    private Date examTime;

    @TableField(value = EXAM_LOCATION, updateStrategy = FieldStrategy.IGNORED)
    private String examLocation;

    public void decreaseSelectedCount() {
        this.selectedCount -= 1;
    }
}
