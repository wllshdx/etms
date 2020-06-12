package cn.wllnb.etms.model.entities;

import cn.wllnb.etms.authentication.annotation.Admin;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author WLL
 */
@Data
@TableName("admin")
public class AdminEntity {
    private static final String ID = "admin_id";
    public static final String USERNAME = "admin_username";
    public static final String PASSWORD = "admin_password";
    public static final String PRIVILEGE = "admin_privilege";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @TableField(USERNAME)
    private String username;

    @NotEmpty
    @TableField(PASSWORD)
    private String password;

    @NotNull(message = "权限不能为空")
    @Range(min = Admin.NO, max = Admin.ALL)
    @TableField(PRIVILEGE)
    private Integer privilege;

}
