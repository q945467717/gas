package com.wis.security;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.wis.pojo.vo.ApiResult;
import com.wis.utils.ResponseCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义返回结果：未登录或登录过期
 *
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(JSON.toJSONString(new ApiResult(ResponseCode.AUTHORITY_ERROR,authException.getMessage())));
        response.getWriter().flush();

        System.out.println(JSON.toJSONString(new ApiResult(ResponseCode.AUTHORITY_ERROR,authException.getMessage())));


    }
}
