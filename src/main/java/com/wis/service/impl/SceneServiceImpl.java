package com.wis.service.impl;

import com.wis.mapper.SceneMapper;
import com.wis.pojo.po.Scene;
import com.wis.pojo.vo.SceneInfo;
import com.wis.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SceneServiceImpl implements SceneService {

    @Autowired
    private SceneMapper sceneMapper;

    //展示所有场景信息
    @Override
    public List<SceneInfo> sceneInfoList() {

        List<Scene> allScene = sceneMapper.findAllScene();

        List<SceneInfo> sceneInfoList = new ArrayList<SceneInfo>();


        for(Scene scene:allScene){

            SceneInfo sceneInfo = new SceneInfo();
            sceneInfo.setId(scene.getId());
            sceneInfo.setScadaSid(scene.getScadaSid());
            sceneInfo.setSceneId(scene.getSceneId());
            sceneInfo.setSceneName(scene.getSceneName());

            sceneInfoList.add(sceneInfo);

        }

        return sceneInfoList;
    }

    //添加场景
    @Override
    public void addScene(SceneInfo sceneInfo) {

        Scene scene = new Scene();
            scene.setSceneId(sceneInfo.getSceneId());
            scene.setSceneName(sceneInfo.getSceneName());
            scene.setAddTime(new Date());
            scene.setScadaSid(sceneInfo.getScadaSid());
        sceneMapper.addScene(scene);

    }

    //删除场景
    @Override
    public void deleteScene(Integer id) {

        sceneMapper.deleteSceneById(id);
    }

    //修改场景
    public void updateScene(SceneInfo sceneInfo) {

        int id = sceneInfo.getId();
        String sceneId = sceneInfo.getSceneId();
        String sceneName = sceneInfo.getSceneName();
        int scadaSid = sceneInfo.getScadaSid();

        sceneMapper.updateSceneById(id,sceneId,sceneName,scadaSid);


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
}
