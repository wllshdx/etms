package cn.wllnb.etms.model.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author WLL
 */
@Data
@TableName("teacher")
public class TeacherEntity {
    public static final String ID = "teacher_id";
    public static final String NUMBER = "teacher_number";
    public static final String DEPARTMENT_ID = "teacher_department_id";
    public static final String NAME = "teacher_name";
    public static final String PASSWORD = "teacher_password";
    public static final String GENDER = "teacher_gender";
    public static final String BIRTHDATE = "teacher_birthdate";
    public static final String DEGREE = "teacher_degree";
    public static final String BASE_SALARY = "teacher_base_salary";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @NotNull
    @Length(min = 4, max = 4, message = "工号长度必须为4位")
    @TableField(NUMBER)
    private String number;

    @NotNull(message = "必须选择所属学院")
    @TableField(DEPARTMENT_ID)
    private Integer departmentId;

    @NotBlank(message = "教师姓名不能为空")
    @TableField(NAME)
    private String name;

    @NotNull
    @TableField(PASSWORD)
    private String password;

    @Range(min = 0, max = 1, message= "性别不能为空")
    @TableField(GENDER)
    private Integer gender;

    @NotNull(message = "出生日期不能为空")
    @TableField(BIRTHDATE)
    private Date birthDate;

    @NotNull(message = "学历不能为空")
    @TableField(DEGREE)
    private String degree;

    @NotNull(message = "基础工资不能为空")
    @TableField(BASE_SALARY)
    private Double baseSalary;
}
