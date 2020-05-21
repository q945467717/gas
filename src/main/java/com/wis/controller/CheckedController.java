package com.wis.controller;

import com.wis.pojo.vo.*;
import com.wis.service.*;
import com.wis.utils.ResponseCode;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/checked")
@ApiIgnore
public class CheckedController {

    @Autowired
    private AssetsService assetsService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private SceneService sceneService;
    @Autowired
    private CheckDataService checkDataService;

    //页面跳转
    @RequestMapping("/checkedManage")
    public String assetsManage(Model model) {

        List<SceneInfo> sceneInfoList = sceneService.sceneInfoList();
        model.addAttribute("sceneInfoList",sceneInfoList);
        return "admin/checkdata_manage";
    }

    @ResponseBody
    @GetMapping("/assetsInfoList")
    public PageHelper<AssetsInfo> assetsInfoList(String sceneId, Integer aid, String assetsName, Integer limit, Integer offset) {

        return assetsService.assetsList(sceneId,aid,assetsName,limit,offset);
    }

    @RequestMapping("/toUpdateAssets")
    public String toUpdateAssets(Integer id, Model model) {

        //根据资产ID查询资产信息
        AssetsInfo assetsInfo = assetsService.assets(id);
        //根据资产sid查询所属场景
        SceneInfo sceneInfo = sceneService.getScene(assetsInfo.getSid());
        //根据场景sid查询该场景所有设备
        List<ItemInfo> itemInfoList = itemService.getItemListBySceneId(sceneInfo.getSceneId());

        for (ItemInfo itemInfo : itemInfoList) {

            if (itemInfo.getAid().equals(assetsInfo.getAid())) {
                model.addAttribute("uid", itemInfo.getUid());
            }
        }

        model.addAttribute("itemList", itemInfoList);

        model.addAttribute("id", id);

        return "modal/assets/updateAssetsModal";
    }

    //更新资产信息
    @PostMapping("/updateAssets")
    @ResponseBody
    public ApiResult updateAssets(Integer id, String uid) throws Exception {

        assetsService.updateAssets(id, uid);

        return new ApiResult(ResponseCode.SUCCESS, "修改成功");

    }

    @RequestMapping("/getCheckDateList")
    @ResponseBody
    public List<CheckInfo> getCheckDateList(String startTime){

        return checkDataService.getCheckedDateList();

    }

}
