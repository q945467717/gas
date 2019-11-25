package com.wis.config;


import com.wis.utils.HttpContextUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(5)
public class AopConfiguration {

    private final Logger logger = LoggerFactory.getLogger(AopConfiguration.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<>();


    @Pointcut("execution(public * com.wis.controller.GasController.*(..))")
    public void apiLog(){}
    @Pointcut("execution(public * com.wis.mapper.ItemMapper.getItemListPage(..))")
    public void findItemLig(){}

    //@Before("apiLog()")
    public void beforeLog(JoinPoint joinPoint) throws RuntimeException{
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String ipAddress = HttpContextUtils.getIpAddress();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ip:"+ipAddress);
    }

    @Before("findItemLig()")
    public void itemBeforeLog(JoinPoint joinPoint) throws RuntimeException{

        System.out.println(joinPoint);
        logger.info("查询物体");

    }

}
