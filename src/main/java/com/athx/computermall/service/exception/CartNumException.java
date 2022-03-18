package com.athx.computermall.service.exception;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/17 8:34
 * @description：购物车数量异常
 * @modified By：
 * @version: $
 */
public class CartNumException  extends ServiceException{
    public CartNumException() {
        super();
    }

    public CartNumException(String message) {
        super(message);
    }

    public CartNumException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNumException(Throwable cause) {
        super(cause);
    }

    protected CartNumException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
