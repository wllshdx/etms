package cn.wllnb.etms.model.vo.response.table;

import lombok.Data;

/**
 * 添加term
 */
@Data
public class TeacherGradeItemVO {
    private Integer studentCourseId;
    private String courseName;
    private String studentName;
    private String term;
    private Integer dailyScore;
    private Integer examScore;
    private Integer score;
}
