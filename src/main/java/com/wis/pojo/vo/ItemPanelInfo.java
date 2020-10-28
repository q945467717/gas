package com.wis.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wis.pojo.po.Assets;
import com.wis.pojo.po.ItemData;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ItemPanelInfo {

    private Assets assets;

    private List<ItemData> itemDataInfoList;

    private List<CheckInfo> checkInfoList;

    private String sceneName;

    private Integer status;

    private String itemName;

    private String uid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

}
