package com.wis.config;


import com.wis.utils.HttpContextUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
    @Pointcut("execution(public * com.wis.mapper.GasApiMapper.*(..))")
    public void findsceneLig(){}
    @Pointcut("execution(public * com.wis.autotask.StaticScheduleTask.getScadaDate(..))")
    public void getExecuteTime(){}

//    @Before("apiLog()")
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

    //@Before("findItemLig()")
    public void itemBeforeLog(JoinPoint joinPoint) throws RuntimeException{

        System.out.println(joinPoint);
        logger.info("查询物体");

    }

    //@Before("findsceneLig()")
    public void findsceneLig(JoinPoint joinPoint){

        System.out.println(joinPoint);
        logger.info("查询物体111");

    }

    @Around("getExecuteTime()")
    public Object getExecuteTime(ProceedingJoinPoint pjp){

        long start = System.currentTimeMillis();

        Object object = null;

        try {
            object = pjp.proceed();
        }catch (Throwable e){

            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("获取数据完成，用时："+(end-start)+"毫秒");

        return object;

    }

}
