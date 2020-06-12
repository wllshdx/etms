package cn.wllnb.etms.dao.manage;

import cn.wllnb.etms.model.bo.LoginStatusBO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @author WLL
 */
@Component
public class LoginStatusManage {

    private static final String SESSION_USER_STATUS = "user_status";

    public void setLoginStatus(HttpSession session, LoginStatusBO loginStatus) {
        session.setAttribute(SESSION_USER_STATUS, loginStatus);
    }

    public LoginStatusBO getLoginStatus(HttpSession session) {
        LoginStatusBO loginStatus = (LoginStatusBO) session.getAttribute(SESSION_USER_STATUS);
        if (loginStatus == null) {
            loginStatus = new LoginStatusBO();
            setLoginStatus(session, loginStatus);
        }

        return loginStatus;
    }
}
