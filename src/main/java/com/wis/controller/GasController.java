package com.wis.controller;

import com.wis.pojo.vo.Result;
import com.wis.service.GasApiService;
import com.wis.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("/gas")
public class GasController {

    @Autowired
    private GasApiService gasApiService;

    /**
     * 查询所有物体
     * @param response 相应头
     * @param sceneId 场景ID
     * @return 场景所有物体
     */
    @RequestMapping("/getItemTable")
    public Result getItemTable(HttpServletResponse response,String sceneId){

        try {
            gasApiService.getItemTable(sceneId);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }

        return ResultUtil.success(response.getStatus(),gasApiService.getItemTable(sceneId));
    }

    /**
     * 会产生告警的设备信息
     * @param response 响应头
     * @param sceneId 场景ID
     * @return 会产生告警的设备信息
     */
    @RequestMapping("/getItemYSBH")
    public Result getItemYSBH(HttpServletResponse response,String sceneId){

        try {
            gasApiService.getItemYSBH(sceneId);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }

        return ResultUtil.success(response.getStatus(),gasApiService.getItemYSBH(sceneId));
    }

    /**
     * 场站整体情况
     * @param response 响应状态码
     * @param sceneId 场景SID
     * @return 提示信息
     */
    @RequestMapping("/getPinfo")
    public Result getPinfo(HttpServletResponse response,String sceneId){

        try {
            gasApiService.getPinfo(sceneId);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }

        return ResultUtil.success(response.getStatus(),gasApiService.getPinfo(sceneId));
    }

    /**
     * 更新告警信息
     * @param response 响应状态码
     * @param sceneId 场景SID
     * @return 提示信息
     */
    @RequestMapping("/mnsj_func")
    public Result mnsj_func(HttpServletResponse response,String sceneId){

        try {
            gasApiService.mnsj_func(sceneId);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }

        return ResultUtil.success(response.getStatus(),"成功");
    }

    /**
     * 相机实时画面页面
     * @param response 响应状态码
     * @param sceneId 场景SID
     * @param uid 物体UID
     * @return 提示信息
     */
    @RequestMapping("/getCameraInfo")
    public Result getCameraInfo(HttpServletResponse response, String sceneId, String uid){

        try {
            gasApiService.getCameraInfo(sceneId,uid);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }

        return ResultUtil.success(response.getStatus(),gasApiService.getCameraInfo(sceneId,uid));

    }

    @RequestMapping("/getEquipmentInfo")
    public Result getEquipmentInfo(HttpServletResponse response, String sceneId){

        try {
            gasApiService.getEquipmentInfo(sceneId);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }

        return ResultUtil.success(response.getStatus(),gasApiService.getEquipmentInfo(sceneId));

    }

    /**
     * 设备数据信息
     * @param response 响应状态码
     * @param sceneId 场景SID
     * @param uid 物体UID
     * @return 提示信息
     */
    @RequestMapping("/getMsgInfo")
    public Result getMsgInfo(HttpServletResponse response,String sceneId,String uid){

        try {
            gasApiService.getMsgInfo(sceneId,uid);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }

        return ResultUtil.success(response.getStatus(),gasApiService.getMsgInfo(sceneId,uid));
    }

    /**
     * webservice接口请求数据
     * @param response 响应状态码
     * @param sceneId 场景SID
     * @return 提示信息
     */
    @RequestMapping("/autoTask")
    public Result autoTask(HttpServletResponse response,String sceneId){

        try {
            gasApiService.autoTask(sceneId);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }

        return ResultUtil.success(response.getStatus(),"成功");
    }

    @RequestMapping("/lookAtItem")
    public Result lookAtItem(String uid,HttpServletResponse response){

        return ResultUtil.success(response.getStatus(),uid);
    }

}
