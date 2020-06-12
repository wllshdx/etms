package cn.wllnb.etms.service;

import cn.wllnb.etms.dao.manage.LoginStatusManage;
import cn.wllnb.etms.model.bo.LoginStatusBO;
import cn.wllnb.etms.model.vo.response.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * @author WLL
 */
public class BaseService {
    @Autowired
    private HttpSession session;
    @Autowired
    private LoginStatusManage loginStatusManage;

    private LoginStatusBO getLoginStatus() {
        return loginStatusManage.getLoginStatus(session);
    }

    protected Integer getUserId() {
        return getLoginStatus().getUserId();
    }

    protected ResultVO result(Object data) {
        return new ResultVO(ResultVO.SUCCESS, "success", data);
    }

    protected ResultVO result(Object data, String message) {
        return new ResultVO(ResultVO.SUCCESS, message, data);
    }

    protected ResultVO failedResult(String message) {
        return new ResultVO(ResultVO.FAIL, message, null);
    }

    protected ResultVO failedResult(String message, Object data) {
        return new ResultVO(ResultVO.FAIL, message, data);
    }
}
