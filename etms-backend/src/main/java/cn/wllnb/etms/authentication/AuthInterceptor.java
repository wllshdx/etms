package cn.wllnb.etms.authentication;

import cn.wllnb.etms.dao.manage.LoginStatusManage;
import cn.wllnb.etms.model.bo.LoginStatusBO;
import cn.wllnb.etms.common.constants.HttpStatusCode;
import cn.wllnb.etms.model.vo.response.ResultVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;

/**
 * @author WLL
 * 权限认证拦截器
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private PermissionScanner scanner;
    @Autowired
    private LoginStatusManage loginStatusManager;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        Permission permission = scanner.scan(method);
        if (!permission.getNeedLogin() || permission.getUserType().equals(0)) {
            return true;
        }

        LoginStatusBO loginStatus = loginStatusManager.getLoginStatus(request.getSession());
        if (!loginStatus.getLoggedIn()) {
            notLogin(response);
            return false;
        }
        if (!loginStatus.getUserType().equals(permission.getUserType())) {
            errorRole(response);
            return false;
        }
        if ((loginStatus.getPermission() & permission.getPermission()) != permission.getPermission()) {
            noPermission(response);
            return false;
        }
        return true;
    }

    private void notLogin(HttpServletResponse response) {
        response.setStatus(HttpStatusCode.UNAUTHORIZED);
        ResultVO resultVO = new ResultVO(ResultVO.NO_LOGIN, "您没有登录", null);
        sendResult(resultVO, response);
    }

    private void errorRole(HttpServletResponse response) {
        response.setStatus(HttpStatusCode.FORBIDDEN);
        ResultVO resultVO = new ResultVO(ResultVO.ERROR_ROLE, "您的角色错误", null);
        sendResult(resultVO, response);
    }

    private void noPermission(HttpServletResponse response) {
        response.setStatus(HttpStatusCode.FORBIDDEN);
        ResultVO resultVO = new ResultVO(ResultVO.NO_PERMISSION, "您没有此管理员权限", null);
        sendResult(resultVO, response);
    }

    private void sendResult(ResultVO result, HttpServletResponse response) {
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");

        try (Writer writer = response.getWriter()) {
            writer.write(objectMapper.writeValueAsString(result));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
