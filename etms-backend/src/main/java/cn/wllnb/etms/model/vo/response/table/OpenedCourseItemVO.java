package cn.wllnb.etms.model.vo.response.table;

import lombok.Data;

import java.util.Date;

/**
 * @author WLL
 */
@Data
public class OpenedCourseItemVO {
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
