package com.wis.config;

import com.wis.security.CustomUserService;
import com.wis.security.FormLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring Security 权限安全配置
 */
@Configuration
@EnableWebSecurity
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
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**","/","/toRegister");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/item/**","/sys/**","/admin/**","/scene/**").authenticated()
                .and()
                .formLogin().loginPage("/toLogin").loginProcessingUrl("/login_check").permitAll()
                .successHandler(formLoginSuccessHandler())
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/toLogin")
                .and().csrf().disable().headers().cacheControl();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
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