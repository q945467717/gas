package com.wis.config;


import com.wis.webservice.StationDTO;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class MyConfiguration {

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许任何域名
        corsConfiguration.addAllowedOrigin("*");
        //允许任何头
        corsConfiguration.addAllowedHeader("*");
        //允许任何方法
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //注册
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

}
