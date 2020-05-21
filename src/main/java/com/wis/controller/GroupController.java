package com.wis.controller;

import com.wis.exception.SceneNotFindException;
import com.wis.mapper.GroupMapper;
import com.wis.mapper.ItemMapper;
import com.wis.mapper.SceneMapper;
import com.wis.pojo.po.Group;
import com.wis.pojo.po.Item;
import com.wis.pojo.po.Scene;
import com.wis.pojo.vo.ApiResult;
import com.wis.pojo.vo.GroupVo;
import com.wis.pojo.vo.SceneInfo;
import com.wis.service.GroupService;
import com.wis.service.SceneService;
import com.wis.utils.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private SceneService sceneService;
    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private ItemMapper itemMapper;

    @GetMapping("/index")
    public String index(Model model){

        List<SceneInfo> sceneInfoList = sceneService.sceneInfoList();
        model.addAttribute("sceneInfoList",sceneInfoList);

        return "admin/group_manage";
    }

    //跳转到分组详情页面
    @GetMapping("/groupInfo")
    public String groupInfo(@RequestParam("id") Integer id,Model model){

        model.addAttribute("id",id);
        return "admin/group_info";
    }

    //打开添加分组模态框
    @GetMapping("/toAddGroup")
    public String toAddGroup(Model model){

        List<SceneInfo> sceneInfoList = sceneService.sceneInfoList();
        model.addAttribute("sceneInfoList",sceneInfoList);


        return "modal/group/addGroupModal";
    }
    //打开删除分组模态框
    @GetMapping("/toDeleteGroup")
    public String toDeleteGroup(Model model,Integer id){

        model.addAttribute("id",id);


        return "modal/group/deleteGroupModal";
    }
    //打开修改分组模态框
    @GetMapping("/toUpdateGroup")
    public String toUpdateGroup(Model model,Integer id){

        Group group = groupMapper.findById(id);

        List<SceneInfo> sceneInfoList = sceneService.sceneInfoList();
        List<SceneInfo> sceneInfos = new ArrayList<>();

        for(SceneInfo sceneInfo:sceneInfoList){
            if(sceneInfo.getSceneId().equals(group.getSceneId())){
                model.addAttribute("scene",sceneInfo);
            }else {
                sceneInfos.add(sceneInfo);
            }
        }

        model.addAttribute("sceneInfoList",sceneInfos);
        model.addAttribute("id",id);
        model.addAttribute("groupName",group.getGroupName());

        return "modal/group/updateGroupModal";
    }
    //打开移除分组物体模态框
    @GetMapping("/toDeleteGroupItem")
    public String toDeleteGroupItem(Model model,String id){

        model.addAttribute("id",id);

        return "modal/group/deleteGroupItemModal";
    }

    //打开添加分组物体模态框
    @GetMapping("/toAddGroupItem")
    public String toAddGroupItem(Model model,Integer id){

        Group group = groupMapper.findById(id);
        List<Item> items = groupMapper.findItemByGroupId(id);

        Scene scene = sceneMapper.findSceneById(group.getSceneId());
        List<Item> itemList = itemMapper.findBySceneId(scene.getSceneId());

        model.addAttribute("itemList",itemList);

        model.addAttribute("id",id);

        return "modal/group/addGroupItemModal";
    }

    //数据接口

    @GetMapping("/list")
    @ResponseBody
    public ApiResult list(String sceneId,String groupName,Integer limit,Integer offset){

        return new ApiResult(ResponseCode.SUCCESS,groupService.groupList(sceneId,groupName,limit,offset));
    }

    //添加分组
    @PostMapping("/")
    @ResponseBody
    public ApiResult addGroup(String groupName,String sceneId) {

        //判断场景是否存在
        Scene scene = sceneMapper.findSceneById(sceneId);
        if(StringUtils.isEmpty(scene)){
            throw new SceneNotFindException();
        }
        //判断分组名称是否重复
        List<GroupVo> groupList = groupService.groupList();
        for(GroupVo groupVo:groupList){
            if(groupVo.getGroupName().equals(groupName)){
                return new ApiResult(ResponseCode.GROUP_REPEAT);
            }
        }
        groupService.addGroup(groupName,sceneId);

        return new ApiResult(ResponseCode.ADD_SUCCESS);
    }

    //删除分组
    @DeleteMapping("/{id}")
    @ResponseBody
    public ApiResult deleteGroup(@PathVariable("id")Integer id) {


        Group group = groupMapper.findById(id);

        if(StringUtils.isEmpty(group)){
            return new ApiResult(ResponseCode.GROUP_NOT_FIND);
        }

        groupService.deleteGroup(id);

        return new ApiResult(ResponseCode.DELETE_SUCCESS);
    }
    //修改分组
    @PutMapping("/{id}")
    @ResponseBody
    public ApiResult updateGroup(@PathVariable("id")Integer id,@RequestBody Group group) {

        List<Group> groups = groupMapper.findAllGroup();
        for(Group group1:groups){
            if(group1.getGroupName().equals(group.getGroupName()) && group1.getSceneId().equals(group.getSceneId())){
                return new ApiResult(ResponseCode.GROUP_REPEAT);
            }
        }

        group.setId(id);
        groupMapper.update(group);
        return new ApiResult(ResponseCode.UPDATE_SUCCESS);
    }

    //获取当前分组的所有设备
    @GetMapping("/{id}/items")
    @ResponseBody
    public ApiResult items(@PathVariable("id")Integer groupId,Integer limit,Integer offset,Item item) {

        Group group = groupMapper.findById(groupId);

        if(StringUtils.isEmpty(group)){
            return new ApiResult(ResponseCode.GROUP_NOT_FIND);
        }

        //groupService.findItemInGroup(groupId,limit,offset,item);
        System.out.println(item.toString());

        return new ApiResult(ResponseCode.SUCCESS,groupService.findItemInGroup(groupId,limit,offset,item));
    }

    //移除当前分组的对应物体
    @DeleteMapping("/{groupId}/item/{itemId}")
    @ResponseBody
    public ApiResult deleteGroupItem(@PathVariable("groupId")Integer groupId,@PathVariable("itemId")Integer itemId) {

        Group group = groupMapper.findById(groupId);
        Item item = itemMapper.findItemById(itemId);

        if(StringUtils.isEmpty(group)){
            return new ApiResult(ResponseCode.GROUP_NOT_FIND);
        }
        if(StringUtils.isEmpty(item)){
            return new ApiResult(ResponseCode.ITEM_NOT_FIND);
        }

        groupService.deleteGroupItem(groupId,itemId);

        return new ApiResult(ResponseCode.DELETE_SUCCESS);
    }

    //添加物体到当前分组
    @PostMapping("/{groupId}/item")
    @ResponseBody
    public ApiResult addGroupItem(@PathVariable("groupId")Integer groupId,Integer itemId) {

        Group group = groupMapper.findById(groupId);
        Item item = itemMapper.findItemById(itemId);
        List<Item> itemList = groupMapper.findItemByGroupId(groupId);
        //判断物体是否存在于该分组中
        for(Item item1:itemList){
            if(item1.getId()==itemId){
                return new ApiResult(ResponseCode.ITEM_REPEAT);
            }
        }
        //判断分组是否存在
        if(StringUtils.isEmpty(group)){
            return new ApiResult(ResponseCode.GROUP_NOT_FIND);
        }
        //判断物体是否存在
        if(StringUtils.isEmpty(item)){
            return new ApiResult(ResponseCode.ITEM_NOT_FIND);
        }

        groupService.addGroupItem(groupId,itemId);

        return new ApiResult(ResponseCode.ADD_SUCCESS);
    }


}
