package com.wis.mapper;

import com.wis.pojo.po.Roles;
import com.wis.pojo.po.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper {

    int save(User user);

    @Select("select * from wis_user where username=#{username}")
    User findByUsername(String username);

    @Select("SELECT * FROM wis_user_roles LEFT JOIN wis_sys_roles ON wis_user_roles.role_id=wis_sys_roles.id where user_id=#{id}")
    List<Roles> sysRoles(int id);

    @Update("update wis_user set password=#{password} where username=#{username}")
    void changePassword(String username, String password);

    @Select("select * from wis_user su left join wis_user_roles sur on su.id = sur.user_id left join wis_sys_roles sr on sur.role_id =sr.id where sr.id=2")
    List<User> findAllUser();

    @Insert("insert into wis_user_roles(user_id,role_id) values(#{userId},#{roleId})")
    void addUserRoles(Integer userId,Integer roleId);

    @Delete("delete from wis_user where id=#{id}")
    void deleteUserById(Integer id);

    @Update("update wis_user set lxdh=#{telephone},cname=#{adminName} where id=#{id}")
    void updateAdminById(Integer id,String adminName,String telephone);

}
