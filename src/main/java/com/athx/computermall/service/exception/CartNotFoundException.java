package com.athx.computermall.service.exception;

import javax.xml.ws.Service;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/17 8:33
 * @description：购物车数据不存在异常
 * @modified By：
 * @version: $
 */
public class CartNotFoundException extends ServiceException {
    public CartNotFoundException() {
        super();
    }

    public CartNotFoundException(String message) {
        super(message);
    }

    public CartNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CartNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
