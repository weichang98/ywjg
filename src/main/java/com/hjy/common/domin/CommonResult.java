package com.hjy.common.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * json封装体
 * @param <T>
 */
@Data
@AllArgsConstructor//全参
@NoArgsConstructor//空参
public class CommonResult<T>{
    private int code;
    private String status;
//    private String timestamp = DateUtil.formatFullTime(LocalDateTime.now(),"yyyy-MM-dd HH:mm:ss");
    private String msg;
    private T data;

    //,String timestamp
    public CommonResult(int code, String status,String msg) {
        this(code,status,msg,null);
    }
}
