package cn.wllnb.etms.model.vo.response.table;

import lombok.Data;

@Data
public class StudentCourseSelectedItemVO {
    private Integer studentCourseId;
    private String courseName;
    private String term;
    private String teacherName;
    private Integer credit;
    private Integer dailyScore;
    private Integer examScore;
    private Integer score;
}
