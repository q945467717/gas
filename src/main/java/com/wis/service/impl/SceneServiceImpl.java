package com.wis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wis.exception.UpdateSceneException;
import com.wis.mapper.SceneMapper;
import com.wis.pojo.po.Scene;
import com.wis.pojo.vo.SceneInfo;
import com.wis.service.SceneService;
import com.wis.utils.ResponseCode;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SceneServiceImpl implements SceneService {

    @Autowired
    private SceneMapper sceneMapper;

    //展示所有场景信息
    @Override
    public List<SceneInfo> sceneInfoList() {

        List<Scene> allScene = sceneMapper.findAllScene();

        List<SceneInfo> sceneInfoList = new ArrayList<SceneInfo>();

        SceneInfo sceneInfo;
        for (Scene scene : allScene) {

            sceneInfo = new SceneInfo();
            sceneInfo.setId(scene.getId());
            sceneInfo.setScadaSid(scene.getScadaSid());
            sceneInfo.setSceneId(scene.getSceneId());
            sceneInfo.setSceneName(scene.getSceneName());
            sceneInfo.setMomodaId(scene.getMomodaId());

            sceneInfoList.add(sceneInfo);

        }

        return sceneInfoList;
    }

    //添加场景
    @Override
    public void addScene(SceneInfo sceneInfo) {

        Scene scene = new Scene();
        scene.setMomodaId(sceneInfo.getMomodaId());
        scene.setSceneName(sceneInfo.getSceneName());
        scene.setAddTime(new Date());
        scene.setScadaSid(sceneInfo.getScadaSid());
        sceneMapper.addScene(scene);
        matchingSceneId();

    }

    //删除场景
    @Override
    public void deleteScene(Integer id) {

        sceneMapper.deleteSceneById(id);
    }

    //修改场景
    @Override
    public void updateScene(SceneInfo sceneInfo){

        int id = sceneInfo.getId();
        String momodaId = sceneInfo.getMomodaId();
        String sceneName = sceneInfo.getSceneName();
        int scadaSid = sceneInfo.getScadaSid();

        sceneMapper.updateSceneById(id, momodaId, sceneName, scadaSid);
        matchingSceneId();


    }

    @Override
    public SceneInfo showScene(String sceneId) {

        Scene scene = sceneMapper.findSceneById(sceneId);

        SceneInfo sceneInfo = new SceneInfo();
        sceneInfo.setId(scene.getId());
        sceneInfo.setSceneName(scene.getSceneName());
        sceneInfo.setSceneId(scene.getSceneId());
        sceneInfo.setScadaSid(scene.getScadaSid());

        return sceneInfo;
    }

    @Override
    public void matchingSceneId() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8081/init/scene.config")
                .build();

        Call call = okHttpClient.newCall(request);

        try {
            Response response = call.execute();
            if (response.body() != null) {
                Map<String, Object> map = JSONObject.parseObject(response.body().string());
                List<Scene> scenes = sceneMapper.findAllScene();

                for (Scene scene : scenes) {
                    for (Map.Entry<String, Object> entry : map.entrySet()) {

                        if (scene.getMomodaId().equals(entry.getKey())) {

                            sceneMapper.matchingId((String) entry.getValue(), scene.getMomodaId());

                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SceneInfo getScene(Integer sid) {

        Scene scene = sceneMapper.findBySid(sid);

        SceneInfo sceneInfo = new SceneInfo();
        sceneInfo.setSceneId(scene.getSceneId());

        return sceneInfo;
    }
}
