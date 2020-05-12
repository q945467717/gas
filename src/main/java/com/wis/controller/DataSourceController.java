package com.wis.controller;

import com.wis.pojo.vo.ApiResult;
import com.wis.pojo.vo.DataSourceVo;
import com.wis.pojo.vo.PageHelper;
import com.wis.pojo.vo.SceneInfo;
import com.wis.service.DataSourceService;
import com.wis.service.SceneService;
import com.wis.utils.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dataSource")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;
    @Autowired
    private SceneService sceneService;


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

        model.addAttribute("id",id);


        return "modal/source/updateSourceModal";

    }

    @PostMapping("/{id}")
    @ResponseBody
    public ApiResult updateSource(@PathVariable("id")Integer id, String dataName){

        dataSourceService.updateSource(id,dataName);

        return new ApiResult(ResponseCode.UPDATE_SUCCESS,null);
    }

}
