package cn.wllnb.etms.controller;

import cn.wllnb.etms.model.vo.request.LoginVO;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WLL
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResultVO login(@Validated @RequestBody LoginVO loginVO) {
        return service.login(loginVO.getUsername(),
                loginVO.getPassword(),
                loginVO.getUserType());
    }

    @RequestMapping("/login/status")
    public ResultVO getLoginStatus() {
        return service.getLoginStatus();
    }

    @RequestMapping("/logout")
    public ResultVO logout() {
        return service.logout();
    }

}
