package cn.wllnb.etms.model.bo;

import cn.wllnb.etms.common.constants.UserType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author WLL
 */
@Data
public class LoginStatusBO implements Serializable {
    private Boolean loggedIn = false;
    private Integer userId;
    private String username;
    private Integer userType = UserType.NO;
    private Integer permission = 0;

    public static LoginStatusBO fromAuthInfo(AuthInfoBO authInfoBO) {
        LoginStatusBO loginStatus = new LoginStatusBO();
        loginStatus.loggedIn = true;
        loginStatus.userId = authInfoBO.getId();
        loginStatus.username = authInfoBO.getUsername();
        loginStatus.userType = authInfoBO.getUserType();
        loginStatus.permission = authInfoBO.getPermission();

        return loginStatus;
    }
}
