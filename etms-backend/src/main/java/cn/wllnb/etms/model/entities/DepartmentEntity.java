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
import javax.validation.constraints.Pattern;

/**
 * @author WLL
 */
@Data
@TableName("department")
public class DepartmentEntity {

    public static final String ID = "department_id";
    public static final String NUMBER = "department_number";
    public static final String NAME = "department_name";
    public static final String ADDRESS = "department_address";
    public static final String TELEPHONE = "department_telephone";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "学院号不能为空")
    @Length(min = 2, max = 2, message = "学院号长度必须为2")
    @TableField(NUMBER)
    private String number;

    @NotBlank(message = "学院名不能为空")
    @TableField(NAME)
    private String name;

    @NotBlank(message = "地址不能为空")
    @TableField(ADDRESS)
    private String address;

    @NotBlank(message = "联系电话号不能为空")
    @Length(min = 8, max = 8, message = "联系电话号必须为8位数")
    @TableField(TELEPHONE)
    private String telephone;
}
