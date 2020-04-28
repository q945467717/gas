package com.wis.pojo.vo;

import com.wis.pojo.po.Assets;
import com.wis.pojo.po.CheckedData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class EquipmentInfo extends ItemInfo{


    private Integer status;

    private Assets assets;

    private List<CheckedData> checkedDataList;


}
