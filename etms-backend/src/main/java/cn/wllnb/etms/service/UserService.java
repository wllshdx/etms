package cn.wllnb.etms.service;

import cn.wllnb.etms.common.constants.UserType;
import cn.wllnb.etms.dao.manage.LoginStatusManage;
import cn.wllnb.etms.dao.manage.UserManage;
import cn.wllnb.etms.model.bo.AuthInfoBO;
import cn.wllnb.etms.model.bo.LoginStatusBO;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.utils.Md5Encrypt;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author WLL
 */
@AllArgsConstructor
@Service
public class UserService extends BaseService {
    @Autowired
    private final HttpSession session;
    @Autowired
    private final UserManage userManage;
    @Autowired
    private final LoginStatusManage loginStatusManage;

    public ResultVO login(String userInfo, String password, Integer userType) {
        AuthInfoBO authInfo = userManage.getAuthInfoByUsername(userInfo, userType);
        if (authInfo == null) {
            return failedResult("用户不存在");
        }
        String passwordHash = Md5Encrypt.computePasswordHash(password);
        if (!passwordHash.equals(authInfo.getPassword())) {
            return failedResult("密码错误");
        }
        if (authInfo.getUserType().equals(UserType.STUDENT)) {
            userManage.updateStudentLastLoginTime(userInfo);
        }
        LoginStatusBO statusBO = LoginStatusBO.fromAuthInfo(authInfo);
        loginStatusManage.setLoginStatus(session, statusBO);

        return result(statusBO);
    }

    public ResultVO getLoginStatus() {
        LoginStatusBO statusBO = loginStatusManage.getLoginStatus(session);
        return result(statusBO);
    }

    public ResultVO logout() {
        loginStatusManage.setLoginStatus(session, null);
        return result("注销成功");
    }

}
