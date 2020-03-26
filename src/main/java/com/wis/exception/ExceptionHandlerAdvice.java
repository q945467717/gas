package com.wis.exception;

import com.wis.pojo.vo.ApiResult;
import com.wis.utils.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public ApiResult handleException(Exception e){
        log.error(e.getMessage(),e);
        return new ApiResult(ResponseCode.SERVICE_ERROR.getCode(),ResponseCode.SERVICE_ERROR.getMsg(),null);
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResult handleRuntimeException(RuntimeException e){
        log.error(e.getMessage(),e);
        return new ApiResult(ResponseCode.SERVICE_ERROR.getCode(),ResponseCode.SERVICE_ERROR.getMsg(),null);
    }

    @ExceptionHandler(ApiException.class)
    public ApiResult handleBaseException(ApiException e){
        log.error(e.getMessage(),e);
        ResponseCode code=e.getCode();
        return new ApiResult(code.getCode(),code.getMsg(),null);
    }
}
