package com.wis.service;

import com.wis.exception.SceneNotFindException;
import com.wis.pojo.po.Group;
import com.wis.pojo.po.Item;
import com.wis.pojo.vo.GroupVo;
import com.wis.pojo.vo.ItemInfo;
import com.wis.pojo.vo.PageHelper;
import io.swagger.models.auth.In;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface GroupService {


    List<GroupVo> groupList();

    PageHelper<GroupVo> groupList(String sceneId, String groupName, Integer limit, Integer offset);

    void addGroup(String groupName,String sceneId) ;

    void updateGroup(Group group);

    void deleteGroup(Integer id);

    List<ItemInfo> findItemInGroup(Integer groupId);

    PageHelper<ItemInfo> findItemInGroup(Integer groupId, Integer limit, Integer offset, Item item);


    void deleteGroupItem(Integer groupId,Integer itemId);

    void addGroupItem(Integer groupId,Integer itemId);
}
