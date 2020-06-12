package cn.wllnb.etms.model.vo.response.table;

import lombok.Data;

/**
 * @author WLL
 */
@Data
public class CourseItemVO {
    private Integer id;
    private Integer number;
    private String name;
    private Integer grade;
    private Integer credit;
    private String time;

    private String departmentName;
}
