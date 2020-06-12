package cn.wllnb.etms.model.vo.response.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author WLL
 */
@Data
public class TeacherItemVO {
    private Integer id;
    private Integer number;
    private String departmentName;
    private String name;
    private Integer gender;
    @JsonFormat(pattern = "yyyy-MM-DD")
    private Date birthDate;
    private String degree;
    private Double baseSalary;
}
