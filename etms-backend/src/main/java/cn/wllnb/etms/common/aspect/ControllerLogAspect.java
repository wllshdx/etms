package cn.wllnb.etms.common.aspect;

import cn.wllnb.etms.dao.log.LogDAO;
import cn.wllnb.etms.dao.manage.LoginStatusManage;
import cn.wllnb.etms.model.bo.LoginStatusBO;
import cn.wllnb.etms.common.constants.HttpStatusCode;
import cn.wllnb.etms.model.entities.mongo.LogEntity;
import cn.wllnb.etms.model.vo.response.ResultVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author WLL
 */
@Aspect
@Component
public class ControllerLogAspect {
    private static final String PACKAGE_PREFIX = "cn.wllnb.etms.controller.";
    private static final String PACKAGE_PREFIX2 = "cn.wllnb.etms.common.handler.BindExceptionHandler.";
    private static final String CONTROLLER_POSTFIX = "Controller.";

    @Autowired
    private LoginStatusManage loginStatusManage;
    @Autowired
    private LogDAO logDAO;

    @Pointcut("execution(public * cn.wllnb.etms.controller..*.*(..)) || " +
            "execution(public * cn.wllnb.etms.common.handler.BindExceptionHandler.handleBindException(..))")
    public void controllerLog() {
    }

    @Around("controllerLog()")
    public Object around(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();

        LogEntity logEntity = new LogEntity();
        logRequest(logEntity, joinPoint);

        Object result;
        try {
            result = joinPoint.proceed();
        }catch (Throwable e){
            logEntity.setException(e.getMessage());
            setResponseCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
            result = new ResultVO(ResultVO.SERVER_ERROR, "未知错误", null);
        }

        logResult(logEntity, result, System.currentTimeMillis() - startTime);
        logDAO.insert(logEntity);

        return result;
    }

    private void logRequest(LogEntity logEntity, ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = getRequest();
        if (request != null) {
            LoginStatusBO loginStatusBO = loginStatusManage.getLoginStatus(request.getSession());
            String requestUri = request.getRequestURI();
            if (request.getQueryString() != null) {
                requestUri += "?" + request.getQueryString();
            }

            logEntity.setRequestUrl(requestUri);
            logEntity.setUserId(loginStatusBO.getUserId());
            logEntity.setUserType(loginStatusBO.getUserType());
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String businessTarget = signature.getDeclaringTypeName() + "." + signature.getMethod().getName();
        businessTarget = businessTarget
                .replace(PACKAGE_PREFIX, "")
                .replace(CONTROLLER_POSTFIX, ".")
                .replace(PACKAGE_PREFIX2, "");
        logEntity.setBusinessTarget(businessTarget);
    }


    private void logResult(LogEntity logEntity, Object result, long executeTime) {
        if (!(result instanceof ResultVO)) {
            return;
        }

        ResultVO resultVO = (ResultVO) result;
        logEntity.setResultCode(resultVO.getCode());
        logEntity.setMessage(resultVO.getMessage());
        logEntity.setExecuteTime(executeTime);
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return null;
        }
        return attributes.getRequest();
    }

    private void setResponseCode(Integer statusCode) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletResponse response = requestAttributes.getResponse();
        if (response == null) {
            return;
        }
        response.setStatus(statusCode);
    }
}
