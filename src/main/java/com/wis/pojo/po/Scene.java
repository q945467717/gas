package com.wis.pojo.po;

import java.util.Date;

public class Scene {

    private int id;
    private String sceneId;
    private String sceneName;
    private Date addTime;
    private int scadaSid;
    private Date upTime;
    private String scadaName;
    private String sceneStatus;

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setScadaSid(int scadaSid) {
        this.scadaSid = scadaSid;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public void setScadaName(String scadaName) {
        this.scadaName = scadaName;
    }

    public void setSceneStatus(String sceneStatus) {
        this.sceneStatus = sceneStatus;
    }

    public int getId() {
        return id;
    }

    public String getSceneId() {
        return sceneId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public int getScadaSid() {
        return scadaSid;
    }

    public Date getUpTime() {
        return upTime;
    }

    public String getScadaName() {
        return scadaName;
    }

    public String getSceneStatus() {
        return sceneStatus;
    }
}
