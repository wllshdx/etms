package cn.wllnb.etms.model.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author WLL
 */
@Data
@TableName("course")
public class CourseEntity {
    public static final String ID = "course_id";
    public static final String NUMBER = "course_number";
    public static final String NAME = "course_name";
    public static final String GRADE = "course_grade";
    public static final String CREDIT = "course_credit";
    public static final String TIME = "course_time";
    public static final String DEPARTMENT_ID = "course_department_id";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "课程号不能为空")
    @Length(min = 8, max = 8, message = "课程号必须为8位")
    @TableField(NUMBER)
    private String number;

    @NotBlank(message = "课程名不能为空")
    @TableField(NAME)
    private String name;

    @NotNull
    @Range(min = 1, max = 10, message = "授课年级范围必须在1-10之间")
    @TableField(GRADE)
    private Integer grade;

    @NotNull
    @Range(min = 1, max = 30, message = "学分必须在1-30之间")
    @TableField(CREDIT)
    private Integer credit;

    @NotNull
    @Range(min = 1, max = 60, message = "学时必须在1-60之间")
    @TableField(TIME)
    private Integer time;

    @NotNull
    @TableField(DEPARTMENT_ID)
    private Integer departmentId;
}
