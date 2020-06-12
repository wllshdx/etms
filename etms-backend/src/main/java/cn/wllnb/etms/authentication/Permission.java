package cn.wllnb.etms.authentication;

import cn.wllnb.etms.common.constants.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WLL
 * 权限类
 */
@Data
@NoArgsConstructor
public class Permission {
    private Integer userType = UserType.NO;
    private Integer permission = 0;
    private Boolean needLogin = Boolean.TRUE;

    public Permission(Integer userType) {
        this.userType = userType;
    }

    public Permission(Integer userType, Integer permission) {
        this.userType = userType;
        this.permission = permission;
    }

    public Permission(Boolean needLogin) {
        this.needLogin = needLogin;
    }
}
