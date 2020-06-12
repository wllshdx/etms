package cn.wllnb.etms.model.vo.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author WLL
 */
@Data
public class StudentInfoFormVO {

    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}", message = "手机号格式不对")
    @Length(min = 11, max = 11, message = "手机号必须为11位数")
    private String telephone;

    @NotBlank
    private String birthLocation;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Range(min = 0, max = 1)
    private Integer sex;

    private String password;

}
