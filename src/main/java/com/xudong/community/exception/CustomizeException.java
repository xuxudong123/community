package com.xudong.community.exception;

/**
 * Created by xuxudong on 2020-03-11 14:23
 */
public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMassage();
        this.code = errorCode.getCode();
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
