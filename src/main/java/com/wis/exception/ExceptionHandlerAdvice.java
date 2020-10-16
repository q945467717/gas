package com.wis.exception;

import com.wis.pojo.vo.ApiResult;
import com.wis.utils.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        return new ApiResult(ResponseCode.SERVICE_ERROR,null);
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResult handleRuntimeException(RuntimeException e){
        log.error(e.getMessage(),e);
        return new ApiResult(ResponseCode.SERVICE_ERROR,null);
    }

    @ExceptionHandler(ApiException.class)
    public ApiResult handleBaseException(ApiException e){
        log.error(e.getMessage(),e);
        ResponseCode code=e.getCode();
        return new ApiResult(code,null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult MethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error(e.getMessage(),e);
        return new ApiResult(ResponseCode.VALIDATED_ERROR,e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(UpdateSceneException.class)
    public ApiResult UpdateSceneException(UpdateSceneException e){
        log.error(e.getMessage(),e);
        return new ApiResult(ResponseCode.VALIDATED_ERROR,null);
    }

    @ExceptionHandler(SceneNotFindException.class)
    public ApiResult SceneNotFindException(SceneNotFindException e){
        log.error(e.getMessage(),e);
        return new ApiResult(ResponseCode.SCENE_NOT_FIND,null);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ApiResult AccessDeniedException(AccessDeniedException e){
        log.error(e.getMessage(),e);
        return new ApiResult(ResponseCode.ACCESS_ERROR,null);
    }

}
