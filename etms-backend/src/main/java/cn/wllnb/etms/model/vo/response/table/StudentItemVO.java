package cn.wllnb.etms.model.vo.response.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author WLL
 */
@Data
public class StudentItemVO {
    private Integer id;
    private Integer number;
    private String name;
    private String className;
    private String majorName;
    private String telephone;
    private Date birthday;
    private String birthLocation;
    private Integer gender;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private Date lastLoginTime;
}
