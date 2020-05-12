package com.wis.pojo.vo;

import lombok.Data;


/**
 * 左侧导航栏资产信息实体
 */
@Data
public class AssetsNavInfo {

    private String assetsName;
    private String uid;
    private Integer itemId;
    private Integer status;
    private Integer type;

}
