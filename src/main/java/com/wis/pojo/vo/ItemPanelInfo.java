package com.wis.pojo.vo;

import com.wis.pojo.po.Assets;
import com.wis.pojo.po.ItemData;
import lombok.Data;

import java.util.List;

@Data
public class ItemPanelInfo {

    private Assets assets;

    private List<ItemData> itemDataInfoList;

    private List<CheckInfo> checkInfoList;

    private String sceneName;

    private Integer status;

    private String itemName;

}
