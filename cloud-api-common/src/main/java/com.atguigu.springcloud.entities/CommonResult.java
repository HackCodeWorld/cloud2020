package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String msg;
    private T data;

    /**
     * 空参数 非200
     * @param code
     * @param message
     */
    public CommonResult(Integer code, String message){
        this(code,message,null);
    }
}
