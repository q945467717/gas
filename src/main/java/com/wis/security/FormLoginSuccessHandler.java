package com.wis.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * 登陆成功后的处理
 */
public class FormLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if(roles.contains("ROLE_ADMIN")){
            response.sendRedirect("/admin/index");
        }else {
            response.sendRedirect("/member/index");
        }
        //if(roles.contains("ROLE_USER")){
//        response.sendRedirect("admin/index");
        // }else {
        //response.sendRedirect("/admin/index");
        //}

        // super.onAuthenticationSuccess(request, response, authentication);

    }

}
