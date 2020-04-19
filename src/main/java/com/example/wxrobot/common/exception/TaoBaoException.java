package com.example.wxrobot.common.exception;

import com.example.wxrobot.common.ErrorEnums;
import lombok.Data;

import java.util.Map;

@Data
public class TaoBaoException extends BaseRuntimeException{
    public TaoBaoException(ErrorEnums errorEnums,Map<String, String> requestmap) {
        super(errorEnums.getDesc());
        this.errorCode = errorEnums.getCode();
        this.requestmap=requestmap;
    }
}
