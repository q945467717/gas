package com.wis.pojo.vo;

import lombok.Data;

/**
 * 左侧告警信息面板实体
 */
@Data
public class WarningInfo {

    private Integer id;
    private String title;
    private String pname;
    private String name;
    private String uid;
    private String pvalue;
    private Integer pstatus;
    private String unit;
    private Integer wtzt;
    private Integer wtlx;
    private Integer qpzt;

}
