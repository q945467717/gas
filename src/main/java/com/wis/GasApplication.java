package com.wis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@EntityScan("com.wis.pojo.po")
@ComponentScan(basePackages = {"com.wis.controller","com.wis.service","com.wis.config","com.wis.security","com.wis.webservice"})
@MapperScan("com.wis.mapper")
@SpringBootApplication
public class GasApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GasApplication.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GasApplication.class);
    }

}
