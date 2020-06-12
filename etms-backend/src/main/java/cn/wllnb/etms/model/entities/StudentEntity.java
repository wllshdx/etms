package cn.wllnb.etms.model.entities;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @author WLL
 */
@Data
@TableName("student")
public class StudentEntity implements Serializable {
    public static final String ID = "student_id";
    public static final String NUMBER = "student_number";
    public static final String CLASS_ID = "student_class_id";
    public static final String NAME = "student_name";
    public static final String PASSWORD = "student_password";
    public static final String TELEPHONE = "student_telephone";
    public static final String BIRTHDATE = "student_birthdate";
    public static final String BIRTHLOCATION = "student_birth_location";
    public static final String GENDER = "student_gender";
    public static final String ADMISSION_YEAR = "student_admission_year";
    public static final String LAST_LOGIN_TIME = "student_last_login_time";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @NotNull
    @Length(min = 4, max = 4, message = "学号必须为4位")
    @TableField(NUMBER)
    private String number;

    @NotNull(message = "必须选择所属班级")
    @TableField(CLASS_ID)
    private Integer classId;

    @NotBlank(message = "学生姓名不能为空")
    @TableField(NAME)
    private String name;

    @NotNull
    @TableField(PASSWORD)
    private String password;

    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}", message = "手机号格式不对")
    @Length(min = 11, max = 11, message = "手机号必须为11位数")
    @TableField(value = TELEPHONE, updateStrategy = FieldStrategy.IGNORED)
    private String telephone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = BIRTHDATE, updateStrategy = FieldStrategy.IGNORED)
    private Date birthDate;

    @NotBlank(message = "籍贯不能为空")
    @TableField(value = BIRTHLOCATION)
    private String birthLocation;

    @Range(min = 0, max = 1, message = "性别不能为空")
    @TableField(GENDER)
    private Integer gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = ADMISSION_YEAR, updateStrategy = FieldStrategy.IGNORED)
    private Date admissionYear;

    @TableField(LAST_LOGIN_TIME)
    private Date lastLoginTime;
}
