package cn.wllnb.etms.model.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author WLL
 */
@Data
public class StudentInfoVO {
    private String number;
    private String name;
    private String departmentName;
    private String majorName;
    private String className;
    private String telephone;

    private String birthLocation;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private Integer sex;
}
