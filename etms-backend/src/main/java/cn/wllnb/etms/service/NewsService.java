package cn.wllnb.etms.service;

import cn.wllnb.etms.dao.manage.NewsManage;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.model.vo.response.table.NewsItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WLL
 */
@Service
public class NewsService extends BaseService {

    @Autowired
    private NewsManage manage;

    public ResultVO getAllNews() {
        List<NewsItemVO> voList = NewsItemVO.fromNewsBOList(manage.getAllNews());
        voList.sort((a, b) -> b.getDate().compareTo(a.getDate()));

        return result(voList);
    }

}
