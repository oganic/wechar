package com.example.wxrobot.common.exception;

import com.example.wxrobot.common.ErrorEnums;
import lombok.Data;

import java.util.Map;

@Data
public class TuLingException extends BaseRuntimeException {

    public TuLingException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public TuLingException(ErrorEnums errorEnums, Map<String, String> requestmap) {
        super(errorEnums.getDesc());
        this.errorCode = errorEnums.getCode();
        this.requestmap=requestmap;
    }

}
