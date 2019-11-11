package com.wis.service;

import com.wis.pojo.vo.AdminInfo;

import java.util.List;

public interface UserService {

    void addAdmin(String username,String password,String adminName,String telephone);

    void loginCheck(String username);

    List<AdminInfo> adminList();

    void deleteAdmin(Integer userId);

    void updateAdmin(Integer userId,String adminName,String telephone);

    void changePassword(String password);


}
