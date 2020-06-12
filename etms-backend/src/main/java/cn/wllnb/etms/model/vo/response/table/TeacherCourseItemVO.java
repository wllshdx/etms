package cn.wllnb.etms.model.vo.response.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TeacherCourseItemVO {
    private Integer number;
    private String term;
    private String name;
    private Integer grade;
    private Integer credit;
    private String time;
    private String location;
    private Integer selectedCount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date examDate;
}
