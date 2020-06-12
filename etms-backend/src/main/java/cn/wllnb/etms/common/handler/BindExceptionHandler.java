package cn.wllnb.etms.common.handler;

import cn.wllnb.etms.common.constants.HttpStatusCode;
import cn.wllnb.etms.model.vo.response.ParameterErrorVO;
import cn.wllnb.etms.model.vo.response.ResultVO;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author WLL
 */
@RestControllerAdvice
public class BindExceptionHandler {
    /**
     * 验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultVO handleBindException(MethodArgumentNotValidException exception, HttpServletResponse response) {
        List<ParameterErrorVO> errorVOList = new ArrayList<>();

        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        for (ObjectError error : errorList) {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorVOList.add(new ParameterErrorVO(fieldName, message));
        }

        errorVOList.sort(Comparator.comparing(ParameterErrorVO::getFieldName));
        String message = "请求参数无效";
        if (errorVOList.size() > 0) {
            ParameterErrorVO firstError = errorVOList.get(0);
            message += ": " + firstError.getFieldName() + "-" + firstError.getMessage();
        }

        response.setStatus(HttpStatusCode.BAD_REQUEST);
        return new ResultVO(ResultVO.INVALID_PARAMETER, message, errorVOList);
    }
}
