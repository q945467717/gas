package com.wis.config;

import com.wis.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 权限安全配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }


    /**
     * 配置静态资源部被Security拦截
     *
     * @param web
     */
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//                .antMatchers("/static/**","/","/toRegister");
//                .antMatchers("/swagger-ui.html")
//                .antMatchers("/v2/**")
//                .antMatchers("/swagger-resources/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                //关闭禁止iframe引用
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/item/**","/sys/**","/admin/**","/scene/**","/assets/**","/checked/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/toLogin").loginProcessingUrl("/login_check").permitAll()
//                .loginProcessingUrl("/login_check").permitAll()
                .successHandler(formLoginSuccessHandler())
                .and()
                .logout()
                .logoutUrl("/logout").logoutSuccessUrl("/toLogin")
                .and()
                //提供API时需要禁用csrf
                .csrf().disable()
                //禁用缓存
                .headers().cacheControl().disable()
                .and()
                .exceptionHandling()
//                .authenticationEntryPoint(restAuthenticationEntryPoint())
                .accessDeniedHandler(restfulAccessDeniedHandler())
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                //实现UserDetailsService接口重新loadUserByUsername方法，返回用户信息
                .userDetailsService(customUserService())
                //密码校验规则，可以自己写规则(通过new PasswordEncoder()，重写matches方法)
                .passwordEncoder(passwordEncoder());
        //.inMemoryAuthentication()
                //.passwordEncoder(new BCryptPasswordEncoder()).withUser("user1").password(new BCryptPasswordEncoder().encode("123456")).roles("1");

    }

    /**
     * 密码加密工具
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    /**
     * 登陆成功之后的后置跳转
     */
    @Bean
    public FormLoginSuccessHandler formLoginSuccessHandler() {
//        FormLoginSuccessHandler handler = new FormLoginSuccessHandler();
//        handler.setDefaultTargetUrl("/admin/index");
//        handler.setAlwaysUseDefaultTargetUrl(true);
//        return handler;
        return new FormLoginSuccessHandler();
    }

}