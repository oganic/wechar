package com.example.wxrobot.common.exception;

import com.example.wxrobot.common.ErrorEnums;
import lombok.Data;

@Data
public class PublicException extends BaseRuntimeException{
    public PublicException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public PublicException(ErrorEnums errorEnums) {
        super(errorEnums.getDesc());
        this.errorCode = errorEnums.getCode();
    }
}
