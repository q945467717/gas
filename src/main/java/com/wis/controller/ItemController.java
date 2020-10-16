package com.wis.controller;

import com.wis.pojo.vo.*;
import com.wis.service.ItemService;
import com.wis.service.SceneService;
import com.wis.utils.ResponseCode;
import com.wis.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/item")
@ApiIgnore
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private SceneService sceneService;

    /**
     * 物体管理和物体数据
     * @return 物体页面和数据
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/itemManage")
    public String itemManage(Model model){

        List<SceneInfo> sceneInfoList = sceneService.sceneInfoList();
        model.addAttribute("sceneInfoList",sceneInfoList);

        return "admin/item_manage";
    }
    @RequestMapping("/itemInfoList")
    @ResponseBody
    public PageHelper<ItemInfo> itemInfoList(int type,String scene,String name,String uid,int limit,int offset){

        PageHelper<ItemInfo> pageHelper = new PageHelper<>();

        Integer total = itemService.getTotal(type,scene,name,uid);
        pageHelper.setTotal(total);
        List<ItemInfo> itemInfoList = itemService.getItemListPage(type,scene,name,uid,limit,offset);
        pageHelper.setRows(itemInfoList);

        return pageHelper;
    }

    //批量导入页面
    @RequestMapping("/importItem")
    public String importItem(){
        return "admin/import_item";
    }

    //去添加物体模态框
    @RequestMapping("/toAddItem")
    public String toAddItem(Model model){

        List<SceneInfo> sceneInfoList = sceneService.sceneInfoList();

        model.addAttribute("sceneInfoList",sceneInfoList);
        return "modal/item/addItemModal";
    }
    //去修改物体模态框
    @RequestMapping("/toUpdateItem")
    public String toUpdateItem(Integer id,Model model){

        List<ItemInfo> itemInfoList = itemService.itemList();
        for(ItemInfo itemInfo:itemInfoList){
            if(itemInfo.getId()==id){
                model.addAttribute("itemInfo",itemInfo);
            }
        }

        List<SceneInfo> sceneInfoList = sceneService.sceneInfoList();
        model.addAttribute("sceneInfoList",sceneInfoList);

        return "modal/item/updateItemModal";
    }
    //去删除物体模态框
    @RequestMapping("/toDeleteItem")
    public String toDeleteItem(Integer id,Model model){

        model.addAttribute("id",id);
        return "modal/item/deleteItemModal";
    }
    //去查看数据源模态框
    @RequestMapping("/toCheckData")
    public String toCheckData(Integer id,Model model){

        List<ItemDataInfo> itemDataInfoList = itemService.checkItemData(id);

        model.addAttribute("itemDataInfoList",itemDataInfoList);

        model.addAttribute("id",id);

        return "modal/item/checkDataSourceModal";
    }

    /**
     * 添加物体
     * @param itemInfo 物体信息
     * @param response 响应状态码
     * @return 提示信息
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Result addItem(ItemInfo itemInfo, HttpServletResponse response){

        System.out.println(itemInfo);
        List<ItemInfo> itemInfoList = itemService.itemList();
        for(ItemInfo itemInfos:itemInfoList){
            if(itemInfos.getUid().equals(itemInfo.getUid())&&itemInfos.getSceneName().equals(itemInfo.getSceneName())){
                return ResultUtil.error(0,"UID已存在");
            }
        }

        try{
            itemService.addItem(itemInfo);
        }catch (Exception e){
            return ResultUtil.error(0,"服务器内部错误");
        }

        return ResultUtil.success(response.getStatus(),"添加物体成功");
    }

    /**
     * 删除物体
     * @param id 物体数据库ID
     * @param response 响应状态码
     * @return 提示信息
     */
    @RequestMapping("/deleteItem")
    @ResponseBody
    @ApiOperation(value = "删除物体")
    public Result deleteScene(Integer id, HttpServletResponse response){

        try{
            itemService.deleteItem(id);
        }catch (Exception e){
            return ResultUtil.error(0,"服务器内部错误");
        }

        return ResultUtil.success(response.getStatus(),"删除物体成功");
    }


    /**
     * 修改物体
     * @param itemInfo 物体信息实体
     * @param response 响应状态码
     * @return 提示信息
     */
    @RequestMapping("/updateItem")
    @ResponseBody
    public Result updateItem(ItemInfo itemInfo, HttpServletResponse response){

        try{
            itemService.updateItem(itemInfo);
        }catch (Exception e){
            return ResultUtil.error(0,"服务器内部错误");
        }

        return ResultUtil.success(response.getStatus(),"修改物体成功");
    }


    @RequestMapping("/addDataSource")
    @ResponseBody
    public Result addDataSource(Integer itemId, @RequestParam("dataSourceList[]") Integer[] dataSourceList, HttpServletResponse response){

        try{
            List<Integer> list = itemService.addDataSource(itemId, dataSourceList);
            if(list.size()!=0){
                return ResultUtil.success(1,"修改成功,但数据源"+list.toString()+"不存在,请重新设置");
            }

        }catch (Exception e){
            return ResultUtil.error(0,"服务器内部错误");
        }

        return ResultUtil.success(response.getStatus(),"修改成功");
    }

}
