package cn.wllnb.etms.model.vo.response.table;

import lombok.Data;

@Data
public class StudentCourseItemVO {
    private Integer id;
    private String courseName;
    private String studentName;
    /**
     * 学期
     */
    private String term;
    private String className;
    private Integer dailyScore;
    private Integer examScore;
    private Integer score;
}
