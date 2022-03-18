package com.athx.computermall.controller.exception;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/13 14:13
 * @description：
 * @modified By：
 * @version: $
 */
public class FileStateException extends FileUploadException {
    public FileStateException() {

    }

    public FileStateException(String message) {
        super(message);
    }

    public FileStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStateException(Throwable cause) {
        super(cause);
    }

    protected FileStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
