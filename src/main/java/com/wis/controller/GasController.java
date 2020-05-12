package com.wis.controller;

import com.wis.mapper.SceneMapper;
import com.wis.pojo.po.Scene;
import com.wis.pojo.vo.ApiResult;
import com.wis.pojo.vo.Result;
import com.wis.service.GasApiService;
import com.wis.utils.ResponseCode;
import com.wis.utils.ResultUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/gas")
public class GasController {

    @Autowired
    private GasApiService gasApiService;
    @Autowired
    private SceneMapper sceneMapper;

    /**
     * 查询所有物体
     * @param response 相应头
     * @param sceneId 场景ID
     * @return 场景所有物体
     */
    @ApiOperation(value = "查询所有物体",notes = "")
    @PostMapping("/getItemTable")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "sceneId",value = "场景ID",required=true)
    )
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
    @ApiOperation("会产生告警的设备信息")
    @PostMapping("/getItemYSBH")
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
    @ApiOperation("场站整体情况")
    @PostMapping("/getPinfo")
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
    @ApiOperation("更新告警信息")
    @PostMapping("/mnsj_func")
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
    @ApiOperation("相机实时画面页面")
    @PostMapping("/getCameraInfo")
    public Result getCameraInfo(HttpServletResponse response, String sceneId, @RequestParam("uids[]") String[] uid){

        try {
            gasApiService.getCameraInfo(sceneId,uid);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }

        return ResultUtil.success(response.getStatus(),gasApiService.getCameraInfo(sceneId,uid));

    }

    @ApiOperation("获取资产信息")
    @GetMapping("/getEquipmentInfo")
    public Result getEquipmentInfo(HttpServletResponse response, String sceneId,@RequestParam("groupId[]") Integer[] groupId){

        try {
            gasApiService.getEquipmentInfo(sceneId,groupId);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }

        return ResultUtil.success(response.getStatus(),gasApiService.getEquipmentInfo(sceneId,groupId));

    }

    /**
     * 设备数据信息
     * @param response 响应状态码
     * @param sceneId 场景SID
     * @param uid 物体UID
     * @return 提示信息
     */

    @ApiOperation("设备数据信息")
    @PostMapping("/getMsgInfo")
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
     * 获取市电信息
     * @param response 响应状态码
     * @param sceneId 场景ID
     * @return 市电状态
     */

    @ApiOperation("获取市电信息")
    @PostMapping("/getElectricity")
    public Result getElectricity(HttpServletResponse response,String sceneId){

        try {
            gasApiService.getElectricity(sceneId);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }

        return ResultUtil.success(response.getStatus(),gasApiService.getElectricity(sceneId));
    }


    /**
     * webservice接口请求数据
     * @param response 响应状态码
     * @param sceneId 场景SID
     * @return 提示信息
     */

    @ApiOperation("webservice接口请求数据")
    @GetMapping("/autoTask")
    public Result autoTask(HttpServletResponse response,String sceneId){

        try {
            gasApiService.autoTask(sceneId);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }

        return ResultUtil.success(response.getStatus(),"成功");
    }

    @ApiOperation("获取巡检数据")
    @GetMapping("/getCheckDate")
    public ApiResult checkedDate(String sceneId){

        Scene scene = sceneMapper.findSceneById(sceneId);

        if(scene==null){
            return new ApiResult(ResponseCode.VALIDATED_ERROR,null);

        }
        return new ApiResult(ResponseCode.SUCCESS,gasApiService.getCheckedDate(scene.getScadaSid()));
    }

    @ApiOperation("获取场景ID")
    @GetMapping("/sceneId/{id}")
    public ApiResult getSceneId(@PathVariable("id")String id){
        return new ApiResult(ResponseCode.SUCCESS,gasApiService.getSceneId(id));

    }

    @ApiOperation("获取物体列表")
    @GetMapping("/item/{id}")
    public ApiResult getItemInfo(@PathVariable("id")Integer id){

        return new ApiResult(ResponseCode.SUCCESS,gasApiService.getItemInfo(id));

    }

    @ApiOperation("获取告警列表")
    @GetMapping("/warnings")
    public ApiResult getWarning(){

        return new ApiResult(ResponseCode.SUCCESS,null);

    }

    @ApiOperation("获取资产列表")
    @GetMapping("{sceneId}/assets")
    public ApiResult getAssets(@PathVariable("sceneId")String sceneId){

        return new ApiResult(ResponseCode.SUCCESS,gasApiService.showAssets(sceneId));

    }

    @ApiOperation("获取分组信息")
    @GetMapping("{sceneId}/groups")
    public ApiResult getGroup(@PathVariable("sceneId")String sceneId){

        return new ApiResult(ResponseCode.SUCCESS,gasApiService.showGroup(sceneId));

    }


}
