package com.wis.controller;

import com.wis.mapper.DataSourceMapper;
import com.wis.mapper.ItemMapper;
import com.wis.pojo.po.Item;
import com.wis.pojo.po.ItemData;
import com.wis.pojo.vo.*;
import com.wis.service.DataSourceService;
import com.wis.service.ItemService;
import com.wis.service.SceneService;
import com.wis.utils.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dataSource")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;
    @Autowired
    private SceneService sceneService;
    @Autowired
    private DataSourceMapper dataSourceMapper;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemMapper itemMapper;

    @RequestMapping("/index")
    public String index(Model model){

        List<SceneInfo> sceneInfoList = sceneService.sceneInfoList();
        model.addAttribute("sceneInfoList",sceneInfoList);
        return "admin/datesource_manage";
    }

    @GetMapping("/list")
    @ResponseBody
    public PageHelper<DataSourceVo> dataSourceList(Integer sid, Integer pid, Integer limit, Integer offset){

        return dataSourceService.dataSourceList(sid,pid,limit,offset);

    }

    @RequestMapping("/toUpdateSource")
    public String toUpdateSource(Model model,Integer id){

        ItemData itemData = dataSourceMapper.findById(id);

        SceneInfo sceneInfo = sceneService.getScene(itemData.getScadaSid());

        Item item = itemMapper.findItemById(itemData.getItemId());

        List<ItemInfo> itemInfoList = itemService.getItemListBySceneIdNotCamera(sceneInfo.getSceneId());

        if(!StringUtils.isEmpty(item)){
            for (ItemInfo itemInfo : itemInfoList) {

                if (itemInfo.getItemName().equals(item.getCname())) {
                    model.addAttribute("itemName", itemInfo.getItemName());
                    model.addAttribute("uid", itemInfo.getUid());
                }
            }
        }else {
            model.addAttribute("itemName","" );
            model.addAttribute("uid", "");
        }
        model.addAttribute("itemList", itemInfoList);
        model.addAttribute("dataName", itemData.getDataName());
        model.addAttribute("id",id);


        return "modal/source/updateSourceModal";

    }

    @PostMapping("/{id}")
    @ResponseBody
    public ApiResult updateSource(@PathVariable("id")Integer id, String dataName,String uid){

        dataSourceService.updateSource(id,dataName,uid);

        return new ApiResult(ResponseCode.UPDATE_SUCCESS,null);
    }

}
