package com.wis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wis.mapper.SceneMapper;
import com.wis.pojo.po.Scene;
import com.wis.pojo.vo.SceneInfo;
import com.wis.service.SceneService;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SceneServiceImpl implements SceneService {

    @Autowired
    private SceneMapper sceneMapper;
    @Value("${IP}")
    private String ip;

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
    public void updateScene(SceneInfo sceneInfo) {

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
                .url("http://" + ip + ":8081/init/scene.config")
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
        if(!StringUtils.isEmpty(scene)){
            sceneInfo.setSceneId(scene.getSceneId());
        }

        return sceneInfo;
    }

    @Override
    public void syncScene() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://" + ip + ":8081/init/scene.config")
                .build();

        Call call = okHttpClient.newCall(request);

        try {
            Response response = call.execute();
            if (response.body() != null) {
                //sceneId和访问idJSON字符串转为map
                Map<String, Object> map = JSONObject.parseObject(response.body().string());

                //遍历map
                for (Map.Entry<String, Object> entry : map.entrySet()) {

                    //将map的value（值为sceneId）转为string
                    String sceneId = String.valueOf(entry.getValue());

                    //判断该sceneId是否存在，如果不存在添加记录
                    if (StringUtils.isEmpty(sceneMapper.findSceneById(sceneId))) {

                        Scene scene = new Scene(){{
                            setSceneId(sceneId);
                            setMomodaId(entry.getKey());
                            setAddTime(new Date());
                        }};

                        sceneMapper.addScene(scene);

                    }else {      //否则修改

                        sceneMapper.updateMomodaId(sceneId,entry.getKey());
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void uploadScene(String mid) {

        sceneMapper.uploadScene(mid,new Date());

    }
}
