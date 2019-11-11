package com.wis.controller;


import com.wis.pojo.vo.AdminInfo;
import com.wis.pojo.vo.Result;
import com.wis.service.UserService;
import com.wis.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class SysUserController {

    @Autowired
    private UserService userService;

    //跳转到管理员管理页面
    @RequestMapping("/peopleManage")
    public String peopleManage(){
        return "admin/people_manage";
    }

    /**
     * 添加管理员
     * @param response 响应状态码
     * @param username 用户名
     * @param password 密码
     * @param adminName 管理员姓名
     * @param telephone 电话
     * @return 提示信息
     */
    @RequestMapping("/addAdmin")
    @ResponseBody
    public Result addAdmin(HttpServletResponse response,String username, String password, String adminName, String telephone){

        try {
            userService.loginCheck(username);
        }catch (Exception e){
            try{
                userService.addAdmin(username, password, adminName, telephone);
            }catch (Exception e1){
                return ResultUtil.error(0,"服务器内部错误");
            }
            return ResultUtil.success(response.getStatus(),"添加管理员成功");
        }
        return ResultUtil.error(0,"用户名已存在");
    }
    //删除管理员
    @RequestMapping("/deleteAdmin")
    @ResponseBody
    public Result deleteAdmin(Integer userId, HttpServletResponse response){
        try {
            userService.deleteAdmin(userId);
        }catch (Exception e){
            return ResultUtil.error(0,"服务器内部错误");
        }
        return ResultUtil.success(response.getStatus(),"删除管理员成功");
    }
    //修改管理员
    @RequestMapping("/updateAdmin")
    @ResponseBody
    public Result updateAdmin(Integer userId, HttpServletResponse response,String adminName,String telephone){
        try {
            userService.updateAdmin(userId,adminName,telephone);
        }catch (Exception e){
            return ResultUtil.error(0,"服务器内部错误");
        }
        return ResultUtil.success(response.getStatus(),"修改管理员成功");
    }

    //修改密码
    @ResponseBody
    @RequestMapping("/changePassword")
    public String changePassword(String password){
        userService.changePassword(password);
        return "ok";
    }

    //管理员数据接口
    @RequestMapping("peopleInfoList")
    @ResponseBody
    public List<AdminInfo> peopleInfoList(){

        return userService.adminList();

    }

    //跳转到添加管理员模态框
    @RequestMapping("/toAddAdmin")
    public String toAddAdmin(){
        return "modal/sys/addAdminModal";
    }
    //跳转到修改管理员信息模态框
    @RequestMapping("/toUpdateAdmin")
    public String toUpdateAdmin(Integer id,Model model){

        List<AdminInfo> adminInfoList = userService.adminList();

        for(AdminInfo adminInfo:adminInfoList){
            if(adminInfo.getId()==id){
                model.addAttribute("adminInfo",adminInfo);
            }
        }


        return "modal/sys/updateAdminModal";
    }
    //跳转到删除管理员模态框
    @RequestMapping("/toDeleteAdmin")
    public String toDeleteAdmin(Integer id,Model model){

        model.addAttribute("id",id);

        return "modal/sys/deleteAdminModal";
    }



}
