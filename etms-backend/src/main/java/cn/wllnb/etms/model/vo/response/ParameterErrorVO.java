package cn.wllnb.etms.model.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WLL
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParameterErrorVO {
    private String fieldName;
    private String message;

}
