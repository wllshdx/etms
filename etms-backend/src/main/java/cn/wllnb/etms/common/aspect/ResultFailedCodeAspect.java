package cn.wllnb.etms.common.aspect;

import cn.wllnb.etms.common.constants.HttpStatusCode;
import cn.wllnb.etms.model.vo.response.ResultVO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * @author WLL
 */
@Aspect
@Component
public class ResultFailedCodeAspect {

    /**
     * 为什么这里可以不用写 cn.wllnb.etms.model.vo.response.ResultVO的返回值？
     */
    @Pointcut("execution(public cn.wllnb.etms.model.vo.response.ResultVO " +
            "cn.wllnb.etms.controller..*.*(..))")
    public void controllerResult() {
    }

    @AfterReturning(value = "controllerResult()", returning = "resultVO")
    public Object afterReturning(ResultVO resultVO) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return resultVO;
        }
        HttpServletResponse response = requestAttributes.getResponse();
        if (response == null) {
            return resultVO;
        }
        if (resultVO.getCode() == ResultVO.FAIL) {
            response.setStatus(HttpStatusCode.NOT_ACCEPTABLE);
        }
        return resultVO;
    }

}
