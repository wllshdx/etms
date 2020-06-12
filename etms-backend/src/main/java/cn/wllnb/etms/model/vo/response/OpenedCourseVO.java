package cn.wllnb.etms.model.vo.response;

import lombok.Data;

/**
 * @author WLL
 */
@Data
public class OpenedCourseVO {
    private String id;
    private String term;
    private String courseName;
    private String teacherName;
    /**、
     * 上课时间
     */
    private String time;
}
