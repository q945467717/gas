package com.wis.exception;

import com.wis.annotation.ApiResponse;
import com.wis.pojo.vo.ApiResult;
import com.wis.utils.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@ControllerAdvice(annotations = ApiResponse.class)
public class ResponseResultHandlerAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {

        log.info("methodParameter:"+methodParameter);
        log.info("aClass:"+aClass);
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(MediaType.APPLICATION_JSON.equals(mediaType) || MediaType.APPLICATION_JSON_UTF8.equals(mediaType)){ // 判断响应的Content-Type为JSON格式的body
            if(o instanceof ApiResult){ // 如果响应返回的对象为统一响应体，则直接返回body
                return o;
            }else{
                // 只有正常返回的结果才会进入这个判断流程，所以返回正常成功的状态码
                return new ApiResult(ResponseCode.SUCCESS,o);

            }
        }
        // 非JSON格式body直接返回即可
        return o;
    }
}
