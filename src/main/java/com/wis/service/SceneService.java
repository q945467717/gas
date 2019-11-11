package com.wis.service;

import com.wis.pojo.vo.SceneInfo;

import java.util.List;

public interface SceneService {

    List<SceneInfo> sceneInfoList();

    void addScene(SceneInfo sceneInfo);

    void deleteScene(Integer id);

    void updateScene(SceneInfo sceneInfo);

    SceneInfo showScene(String sceneId);

}
