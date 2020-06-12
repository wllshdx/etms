package cn.wllnb.etms.controller;

import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WLL
 */
@RestController
@RequestMapping("/etms/news")
public class  EtmsNewsController extends BaseController{

    @Autowired
    private NewsService service;

    @GetMapping
    public ResultVO get() {
        return service.getAllNews();
    }


}
