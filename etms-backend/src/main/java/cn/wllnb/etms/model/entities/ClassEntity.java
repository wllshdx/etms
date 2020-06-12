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

/**
 * @author WLL
 */
@Data
@TableName("class")
public class ClassEntity {
    public static final String ID = "class_id";
    public static final String NUMBER = "class_number";
    public static final String MAJOR_ID = "class_major_id";
    public static final String GRADE = "class_grade";
    public static final String NAME = "class_name";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "班级号不能为空")
    @Range(min = 1000, max = 9999, message = "班级号长度必须为4")
    @TableField(NUMBER)
    private Integer number;

    @NotNull(message = "必须选择所属专业")
    @TableField(MAJOR_ID)
    private Integer majorId;

    @NotNull
    @Range(min = 1999, max = 2099, message = "年级范围必须在1999~2099之间")
    @TableField(GRADE)
    private Integer grade;

    @NotBlank(message = "班级名不能为空")
    @TableField(NAME)
    private String name;
}
