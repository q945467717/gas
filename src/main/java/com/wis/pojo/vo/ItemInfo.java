package com.wis.pojo.vo;

import java.io.Serializable;

public class ItemInfo implements Serializable {

    private int id;
    private String itemName;
    private String sceneName;
    private String uid;
    private String text;
    private String itemType;
    private Integer aid;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getSceneName() {
        return sceneName;
    }

    public String getUid() {
        return uid;
    }

    public String getText() {
        return text;
    }

    public String getItemType() {
        return itemType;
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", sceneName='" + sceneName + '\'' +
                ", uid='" + uid + '\'' +
                ", text='" + text + '\'' +
                ", itemType='" + itemType + '\'' +
                '}';
    }
}
