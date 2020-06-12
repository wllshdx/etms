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
@TableName("major")
public class MajorEntity {
    public static final String ID = "major_id";
    public static final String NUMBER = "major_number";
    public static final String DEPARTMENT_ID = "major_department_id";
    public static final String NAME = "major_name";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @NotNull
    @Length(min = 4, max = 4, message = "专业号长度必须为4")
    @TableField(NUMBER)
    private String number;

    @NotNull(message = "必须选择所属系")
    @TableField(DEPARTMENT_ID)
    private Integer departmentId;

    @NotBlank(message = "专业名不能为空")
    @TableField(NAME)
    private String name;
}
