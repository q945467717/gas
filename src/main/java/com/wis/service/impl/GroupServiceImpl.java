package com.wis.service.impl;

import com.wis.mapper.GroupMapper;
import com.wis.mapper.SceneMapper;
import com.wis.pojo.po.Group;
import com.wis.pojo.po.Item;
import com.wis.pojo.po.Scene;
import com.wis.pojo.vo.GroupVo;
import com.wis.pojo.vo.ItemInfo;
import com.wis.pojo.vo.PageHelper;
import com.wis.service.GroupService;
import com.wis.utils.ItemTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private SceneMapper sceneMapper;

    @Override
    public List<GroupVo> groupList() {


        List<Group> groupList = groupMapper.findAllGroup();
        List<GroupVo> groupVoList = new ArrayList<>();
        GroupVo groupVo;
        for(Group group:groupList){

            Scene scene = sceneMapper.findSceneById(group.getSceneId());

            groupVo = new GroupVo(){{
                setId(group.getId());
                setGroupName(group.getGroupName());
                setSceneName(scene.getSceneName());

            }};

            groupVoList.add(groupVo);
        }

        return groupVoList;
    }

    @Override
    public PageHelper<GroupVo> groupList(String sceneId,String groupName,Integer limit,Integer offset) {

        List<Group> groupList = groupMapper.findPageGroup(sceneId,groupName,limit,offset);
        List<GroupVo> groupVoList = new ArrayList<>();
        GroupVo groupVo;
        for(Group group:groupList){

            Scene scene = sceneMapper.findSceneById(group.getSceneId());

            groupVo = new GroupVo(){{
                setId(group.getId());
                setGroupName(group.getGroupName());
                setSceneName(scene.getSceneName());

            }};

            groupVoList.add(groupVo);
        }

        Integer total = groupMapper.getGroupTotal(sceneId, groupName);

        PageHelper<GroupVo> pageHelper = new PageHelper<>();
        pageHelper.setTotal(total);
        pageHelper.setRows(groupVoList);

        return pageHelper;
    }

    @Override
    public void addGroup(String groupName, String sceneId) {

        groupMapper.insert(groupName,sceneId);

    }

    @Override
    public void updateGroup(Group group) {

        groupMapper.update(group);
    }

    @Override
    public void deleteGroup(Integer id) {

        //删除分组
        groupMapper.delete(id);
        //删除分组中的物体
        groupMapper.deleteItemRelation(id);

    }

    @Override
    public List<ItemInfo> findItemInGroup(Integer groupId) {

        List<Item> itemList = groupMapper.findItemByGroupId(groupId);
        List<ItemInfo> itemInfoList = new ArrayList<>();

        ItemInfo itemInfo;
        for(Item item:itemList){
            itemInfo = new ItemInfo(){{
               setId(item.getId());
               setAid(item.getAid());
               setUid(item.getUid());
               setItemType(ItemTypeUtil.type(item.getWtlx()));
               setItemName(item.getCname());
               setText(item.getContent());

            }};

            itemInfoList.add(itemInfo);
        }

        return itemInfoList;
    }

    @Override
    public PageHelper<ItemInfo> findItemInGroup(Integer groupId, Integer limit, Integer offset, Item item) {

        List<Item> itemList = groupMapper.findPageGroupItem(groupId,limit,offset,item);
        List<ItemInfo> itemInfoList = new ArrayList<>();

        ItemInfo itemInfo;
        for(Item item1:itemList){
            itemInfo = new ItemInfo(){{
                setId(item1.getId());
                setAid(item1.getAid());
                setUid(item1.getUid());
                setItemType(ItemTypeUtil.type(item1.getWtlx()));
                setItemName(item1.getCname());
                setText(item1.getContent());

            }};

            itemInfoList.add(itemInfo);
        }


        PageHelper<ItemInfo> pageHelper = new PageHelper<>();

        pageHelper.setRows(itemInfoList);
        pageHelper.setTotal(groupMapper.getGroupItemTotal(groupId,item));

        return pageHelper;

    }

    @Override
    public void deleteGroupItem(Integer groupId, Integer itemId) {

        groupMapper.deleteGroupItem(groupId,itemId);

    }

    @Override
    public void addGroupItem(Integer groupId, Integer itemId) {

        groupMapper.insertGroupItem(groupId,itemId);
    }
}
