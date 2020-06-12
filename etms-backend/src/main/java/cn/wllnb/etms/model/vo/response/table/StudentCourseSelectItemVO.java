package cn.wllnb.etms.model.vo.response.table;

import lombok.Data;

@Data
public class StudentCourseSelectItemVO {
    private Integer openedCourseId;
    private String term;
    private String courseName;
    private String teacherName;
    /**
     * 上课时间
     */
    private String time;
    private Integer credit;
    private Integer selectedCount;
    private Integer maxSize;
}
