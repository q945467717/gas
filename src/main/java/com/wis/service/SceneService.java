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


}
