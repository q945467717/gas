package com.wis.service.impl;

import com.wis.mapper.SysUserMapper;
import com.wis.pojo.po.User;
import com.wis.pojo.vo.AdminInfo;
import com.wis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    //检查用户名是否重复
    @Override
    public void loginCheck(String username){
        User user = sysUserMapper.findByUsername(username);
        try{
            if(user.getUsername().equals(username)){
                return;
            };
        }catch (Exception e){
           throw e;
        }
    }

    //修改密码
    @Override
    public void changePassword(String password) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User principal =(User) authentication.getPrincipal();

        String newPassword = bCryptPasswordEncoder.encode(password);

        sysUserMapper.changePassword(principal.getUsername(),newPassword);

    }

    //展示管理员
    @Override
    public List<AdminInfo> adminList() {

        List<User> userList = sysUserMapper.findAllUser();

        List<AdminInfo> adminInfoList = new ArrayList<>();

        for(User user :userList){
            AdminInfo adminInfo = new AdminInfo();
            adminInfo.setAdminName(user.getCname());
            adminInfo.setTelephone(user.getLxdh());
            adminInfo.setUsername(user.getUsername());
            adminInfo.setId(user.getId());

            adminInfoList.add(adminInfo);

        }

        return adminInfoList;
    }

    //删除管理员
    @Override
    public void deleteAdmin(Integer userId) {
        sysUserMapper.deleteUserById(userId);
    }
    //修改管理员
    @Override
    public void updateAdmin(Integer userId,String adminName,String telephone){

        sysUserMapper.updateAdminById(userId,adminName,telephone);
    }

    //添加管理员
    @Override
    @Transactional
    public void addAdmin(String username, String password, String adminName, String telephone) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setCname(adminName);
        user.setLxdh(telephone);
        user.setAddtime(new Date());

        int userId = sysUserMapper.save(user);

        sysUserMapper.addUserRoles(user.getId(),2);
    }
}
