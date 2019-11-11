package com.wis.security;

import com.wis.mapper.SysUserMapper;
import com.wis.pojo.po.Roles;
import com.wis.pojo.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = sysUserMapper.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<Roles> list = sysUserMapper.sysRoles(user.getId());

        user.setRoles(list);

        return user;
    }

}
