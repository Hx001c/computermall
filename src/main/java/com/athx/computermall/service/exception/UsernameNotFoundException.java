package com.athx.computermall.service.exception;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/12 19:30
 * @description：用户不存在异常
 * @modified By：
 * @version: $
 */
public class UsernameNotFoundException extends ServiceException {
    public UsernameNotFoundException() {
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }

    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotFoundException(Throwable cause) {
        super(cause);
    }

    public UsernameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
