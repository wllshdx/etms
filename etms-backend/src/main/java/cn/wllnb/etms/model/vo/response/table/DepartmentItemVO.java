package cn.wllnb.etms.model.vo.response.table;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author WLL
 */
@Data
@AllArgsConstructor
public class DepartmentItemVO {
    private Integer id;
    private String number;
    private String name;
    private String address;
    private String telephone;

    private Integer majorCount;
    private Integer teacherCount;
}
