package com.wis.controller;

import com.wis.pojo.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    @Value("${IP}")
    private String ip;

    //跳转到地图导航页
    @RequestMapping("/member/index")
    public String adminIndex(){

        return "member/index";
    }

    //跳转到站点详情页
    @RequestMapping("/member/detail")
    public String detail(Model model, @RequestParam("id") int id){

        model.addAttribute("url","http://"+ip+":8081/scene/"+id);
        return "member/detail";
    }


}
