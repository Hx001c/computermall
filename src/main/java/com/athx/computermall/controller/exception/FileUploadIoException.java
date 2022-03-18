package com.athx.computermall.controller.exception;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/13 14:13
 * @description：
 * @modified By：
 * @version: $
 */
public class FileUploadIoException extends FileUploadException {
    public FileUploadIoException() {
        super();
    }

    public FileUploadIoException(String message) {
        super(message);
    }

    public FileUploadIoException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadIoException(Throwable cause) {
        super(cause);
    }

    protected FileUploadIoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
