package com.wis.pojo.vo;

public class SceneInfo {

    private int id;
    private String sceneId;
    private String sceneName;
    private int scadaSid;
    private String momodaId;

    public String getMomodaId() {
        return momodaId;
    }

    public void setMomodaId(String momodaId) {
        this.momodaId = momodaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public int getScadaSid() {
        return scadaSid;
    }

    public void setScadaSid(int scadaSid) {
        this.scadaSid = scadaSid;
    }

    @Override
    public String toString() {
        return "SceneInfo{" +
                "id=" + id +
                ", sceneId='" + sceneId + '\'' +
                ", sceneName='" + sceneName + '\'' +
                ", scadaSid=" + scadaSid +
                ", momodaId='" + momodaId + '\'' +
                '}';
    }
}
