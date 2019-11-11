package com.wis.config;


import com.wis.webservice.WebServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfiguration {


    @Bean
    public ServletRegistrationBean dispatcherServlets(){
        return new ServletRegistrationBean(new CXFServlet(),"/cxf/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    //hello
    @Bean
    public Endpoint hello() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), new WebServiceImpl());// 绑定要发布的服务实现类
        endpoint.publish("/hello"); // 接口访问地址
        return endpoint;
    }


}
