package cn.wllnb.etms.utils;

import lombok.Data;

/**
 * @author WLL
 */
@Data
public class PageUtils {

    public static final int PAGE_SIZE = 10;

    public static int calcPageCount(int recordCount, int pageSize) {
        if (recordCount % pageSize == 0) {
            return recordCount / pageSize;
        }
        return (recordCount / pageSize) + 1;
    }
}
