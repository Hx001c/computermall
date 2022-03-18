package com.athx.computermall.controller.exception;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/13 14:15
 * @description：
 * @modiFied By：
 * @version: $
 */
public class FileTypeException extends FileUploadException {
    public FileTypeException() {
        super();
    }

    public FileTypeException(String message) {
        super(message);
    }

    public FileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeException(Throwable cause) {
        super(cause);
    }

    protected FileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
