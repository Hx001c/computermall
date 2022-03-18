package com.athx.computermall.controller.exception;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/13 14:14
 * @description：
 * @modified By：
 * @version: $
 */
public class FileSIzeException extends FileUploadException {
    public FileSIzeException() {
        super();
    }

    public FileSIzeException(String message) {
        super(message);
    }

    public FileSIzeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSIzeException(Throwable cause) {
        super(cause);
    }

    protected FileSIzeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
