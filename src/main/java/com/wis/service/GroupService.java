package com.wis.service;

import com.wis.pojo.po.Group;
import com.wis.pojo.po.Item;
import com.wis.pojo.vo.GroupVo;
import com.wis.pojo.vo.ItemInfo;
import com.wis.pojo.vo.PageHelper;

import java.util.List;

public interface GroupService {


    List<GroupVo> groupList();

    PageHelper<GroupVo> groupList(String sceneId, String groupName, Integer limit, Integer offset);
    //添加设备分组
    void addGroup(String groupName,String sceneId) ;
    //修改设备分组
    void updateGroup(Group group);
    //删除设备分组
    void deleteGroup(Integer id);
    //分组设备列表
    List<ItemInfo> findItemInGroup(Integer groupId);

    PageHelper<ItemInfo> findItemInGroup(Integer groupId, Integer limit, Integer offset, Item item);
    //删除分组设备
    void deleteGroupItem(Integer groupId,Integer itemId);
    //添加分组设备
    void addGroupItem(Integer groupId,Integer itemId);
}
