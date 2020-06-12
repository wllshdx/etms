package cn.wllnb.etms.model.bo;

import lombok.Data;

/**
 * @author WLL
 */
@Data
public class StudentCourseSelectItemBO {
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
