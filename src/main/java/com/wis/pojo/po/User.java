package com.wis.pojo.po;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class User implements UserDetails {


    private int id;
    private String username;
    private String password;
    private String cname;
    private String lxdh;
    private Date addtime;

    private List<Roles> roles;

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public int getId() {
        return id;
    }

    public String getCname() {
        return cname;
    }

    public String getLxdh() {
        return lxdh;
    }

    public Date getAddtime() {
        return addtime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<Roles> roles = this.getRoles();
        for (Roles role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userpass='" + password + '\'' +
                ", cname='" + cname + '\'' +
                ", lxdh='" + lxdh + '\'' +
                ", addtime=" + addtime +
                ", roles=" + roles +
                '}';
    }
}
