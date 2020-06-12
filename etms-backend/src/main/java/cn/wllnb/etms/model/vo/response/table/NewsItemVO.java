package cn.wllnb.etms.model.vo.response.table;

import cn.wllnb.etms.model.bo.NewsBO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class NewsItemVO {
    private String title;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date date;

    private String url;

    private static NewsItemVO fromNewsBO(NewsBO bo) {
        NewsItemVO vo = new NewsItemVO();
        vo.setTitle(bo.getTitle());
        vo.setUrl(bo.getUrl());

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            vo.setDate(format.parse(bo.getDate()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return vo;
    }

    public static List<NewsItemVO> fromNewsBOList(List<NewsBO> boList) {
        List<NewsItemVO> voList = new ArrayList<>();
        for (NewsBO bo : boList) {
            voList.add(NewsItemVO.fromNewsBO(bo));
        }

        return voList;
    }

}
