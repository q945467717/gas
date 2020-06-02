package com.wis.service;

import com.wis.pojo.vo.SceneInfo;

import java.util.List;

public interface SceneService {

    List<SceneInfo> sceneInfoList();

    void addScene(SceneInfo sceneInfo);

    void deleteScene(Integer id);

    void updateScene(SceneInfo sceneInfo);

    SceneInfo showScene(String sceneId);

    //匹配场景id和模模搭id
    void matchingSceneId();

    //根据sid获取场景信息
    SceneInfo  getScene(Integer sid);

    //同步场景
    void syncScene();

    //上传成功后添加场景
    void uploadScene(String mid);


}
