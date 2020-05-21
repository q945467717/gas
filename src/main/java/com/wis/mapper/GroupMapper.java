package com.wis.mapper;

import com.wis.pojo.po.Group;
import com.wis.pojo.po.Item;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMapper {

    //查询所有分组
    @ResultMap("GroupResultMap")
    @Select("select * from wis_item_group ")
    List<Group> findAllGroup();

    //查询分页分组数据和分页数据总数
    List<Group> findPageGroup(String sceneId,String groupName,Integer limit,Integer offset);
    Integer getGroupTotal(String sceneId,String groupName);

    //查询分页分组物体数据和总数
    List<Item> findPageGroupItem(Integer groupId,Integer limit,Integer offset,@Param("item")Item item);
    Integer getGroupItemTotal(Integer groupId,@Param("item") Item item);

    //添加一个分组
    @Insert("insert into wis_item_group(group_name,scene_id) values(#{groupName},#{sceneId})")
    void insert(String groupName,String sceneId);

    //修改分组
    @Update("update wis_item_group set group_name=#{groupName},scene_id=#{sceneId} where id=#{id}")
    void update(Group group);

    //删除分组
    @Delete("delete from wis_item_group where id=#{id}")
    void delete(Integer id);

    //根据ID查询分组
    @ResultMap("GroupResultMap")
    @Select("select * from wis_item_group where id=#{id}")
    Group findById(Integer id);

    //查询当前分组的所有数据
    @Select("select wi.* from wis_item wi left join wis_item_group_relation wigr on wi.id = wigr.item_id where wigr.group_id=#{groupId}")
    List<Item> findItemByGroupId(Integer id);

    //移除分组中的所有物体
    @Delete("delete from wis_item_group_relation where group_id=#{groupId}")
    void deleteItemRelation(Integer groupId);

    //移除分组中的一个物体
    @Delete("delete from wis_item_group_relation where item_id=#{itemId} and group_id=#{groupId}")
    void deleteGroupItem(Integer groupId,Integer itemId);

    //添加分组物体
    @Insert("insert into wis_item_group_relation(group_id,item_id) values (#{groupId},#{itemId})")
    void insertGroupItem(Integer groupId,Integer itemId);
}
