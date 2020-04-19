package com.example.wxrobot.common.exception;

import lombok.Data;

import java.util.Map;

@Data
public class BaseRuntimeException extends RuntimeException{
    /**
     * 错误码
     */
    protected Integer errorCode;

    protected Map<String, String> requestmap;

    BaseRuntimeException(String errorMsg) {
        super(errorMsg);
    }
}
