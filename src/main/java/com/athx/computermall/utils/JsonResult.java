package com.athx.computermall.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/12 16:27
 * @description：效应结果类
 * @modified By：
 * @version: $
 */
@Data
public class JsonResult<E> implements Serializable {
    //状态码
    private Integer state;
    //状态描述信息
    private String message;
    //数据
    private E data;
    public JsonResult() {
    }

    public JsonResult(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public JsonResult(Integer state) {
        this.state = state;
    }
    /** 出现异常时调用 */
    public JsonResult(Throwable e) {
        super();
        // 获取异常对象中的异常信息
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        super();
        this.state = state;
        this.data = data;
    }


}
