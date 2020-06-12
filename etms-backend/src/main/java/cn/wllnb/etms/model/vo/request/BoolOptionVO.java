package cn.wllnb.etms.model.vo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author WLL
 */
@Data
public class BoolOptionVO {
    @NotNull
    public Boolean option;
}
