package com.wis.controller;

import com.wis.annotation.ApiResponse;
import com.wis.exception.UpdateSceneException;
import com.wis.mapper.SceneMapper;
import com.wis.pojo.po.Scene;
import com.wis.pojo.vo.ApiResult;
import com.wis.pojo.vo.Result;
import com.wis.pojo.vo.SceneInfo;
import com.wis.service.SceneService;
import com.wis.utils.ResponseCode;
import com.wis.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/scene")
@ApiIgnore
public class SceneController {

    @Autowired
    private SceneService sceneService;
    @Autowired
    private SceneMapper sceneMapper;

    /**
     * 场景管理及场景数据
     *
     * @return 场景页面和数据
     */
    @RequestMapping("/sceneManage")
    public String sceneManage() {
        return "admin/scene_manage";
    }

    @RequestMapping("/sceneInfoList")
    @ResponseBody
    public List<SceneInfo> sceneInfoList() {
        return sceneService.sceneInfoList();
    }

    //去添加场景模态框
    @RequestMapping("/toAddScene")
    public String toAddScene() {
        return "modal/scene/addSceneModal";
    }

    //去修改场景模态框
    @RequestMapping("/toUpdateScene")
    public String toUpdateScene(Integer id, Model model) {

        List<SceneInfo> sceneInfoList = sceneService.sceneInfoList();
        for (SceneInfo sceneInfo : sceneInfoList) {
            if (sceneInfo.getId() == id) {
                model.addAttribute("sceneInfo", sceneInfo);
            }
        }
        return "modal/scene/updateSceneModal";
    }

    //去删除场景模态框
    @RequestMapping("/toDeleteScene")
    public String toDeleteScene(Integer id, Model model) {
        model.addAttribute("id", id);
        return "modal/scene/deleteSceneModal";
    }


    /**
     * 添加场景
     *
     * @param sceneInfo 场景信息
     * @param response  响应状态码
     * @return 提示信息
     */
    @RequestMapping("/addScene")
    @ResponseBody
    public Result addScene(SceneInfo sceneInfo, HttpServletResponse response) {

        List<SceneInfo> sceneInfoList = sceneService.sceneInfoList();
        for (SceneInfo sceneInfo1 : sceneInfoList) {
            if (sceneInfo1.getMomodaId().equals(sceneInfo.getMomodaId())) {
                return ResultUtil.error(0, "模模搭ID已存在");
            }
            if (sceneInfo1.getScadaSid() == sceneInfo.getScadaSid()) {
                return ResultUtil.error(0, "对接数据平台重复");
            }
            if (sceneInfo1.getSceneName().equals(sceneInfo.getSceneName())) {
                return ResultUtil.error(0, "场景名称重复");
            }
        }
        try {
            sceneService.addScene(sceneInfo);
        } catch (Exception e) {
            return ResultUtil.error(0, "服务器内部错误");
        }

        return ResultUtil.success(response.getStatus(), "添加场景成功");
    }

    /**
     * 删除场景
     *
     * @param id       场景数据库ID
     * @param response 响应状态码
     * @return 提示信息
     */
    @RequestMapping("/deleteScene")
    @ResponseBody
    public Result deleteScene(Integer id, HttpServletResponse response) {

        try {
            sceneService.deleteScene(id);
        } catch (Exception e) {
            return ResultUtil.error(0, "服务器内部错误");
        }

        return ResultUtil.success(response.getStatus(), "删除场景成功");
    }

    /**
     * 修改场景信息
     *
     * @param sceneInfo 修改后的场景信息
     * @param response  响应状态码
     * @return 提示信息
     */
    @RequestMapping("/updateScene")
    @ResponseBody
    @ApiResponse
    public ApiResult updateScene(SceneInfo sceneInfo, HttpServletResponse response) throws Exception{

//        try{
//            sceneService.updateScene(sceneInfo);
//        }catch (Exception e){
//            return ResultUtil.error(0,"服务器内部错误");
//        }
//
//        return ResultUtil.success(response.getStatus(),"修改场景成功");

        Scene scene = sceneMapper.findByMomodaId(sceneInfo.getMomodaId());

        if(scene==null){
            throw new UpdateSceneException();
        }

        sceneService.updateScene(sceneInfo);
        //return ResultUtil.success(response.getStatus(),"修改场景成功");
        return new ApiResult(ResponseCode.UPDATE_SUCCESS,null);

    }

    @RequestMapping("/matchingSceneId")
    @ResponseBody
    public ApiResult matchingSceneId() {

        sceneService.matchingSceneId();
        return new ApiResult(ResponseCode.MATCHING_SUCCESS, null);

    }

}
