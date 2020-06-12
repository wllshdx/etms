package cn.wllnb.etms.model.vo.response.table;

import lombok.Data;

/**
 * @author WLL
 */
@Data
public class ClassItemVO {
    private Integer id;
    private Integer number;
    private String name;
    private Integer grade;

    private String departmentName;
    private String majorName;
}
