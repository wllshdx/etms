package cn.wllnb.etms.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewsBO {
    private String title;
    private String url;
    private String date;

}
