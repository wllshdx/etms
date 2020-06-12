package cn.wllnb.etms.model.bo;

import lombok.Data;

@Data
public class OpenedCourseItemBO {
    private Integer id;
    private String term;
    private String courseName;
    private String teacherName;
    private String departmentName;
    private Integer grade;
    /**
     * 上课时间
     */
    private String time;
    private String location;
    private Integer selectedCount;
    private Integer maxSize;
}
