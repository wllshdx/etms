package cn.wllnb.etms.model.entities.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author WLL
 */
@Document("etms_log")
@Data
public class LogEntity {
    private Integer userId;
    private Integer userType;
    private String requestUrl;
    private String businessTarget;
    private String message;
    private Object exception;
    private Integer resultCode;
    private Long executeTime;
    private Date datetime = new Date();
}
