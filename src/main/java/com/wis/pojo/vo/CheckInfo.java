package com.wis.pojo.vo;

import lombok.Data;

@Data
public class CheckInfo {

    private Integer id;
    private String checkMember;
    private String checkLog;
    private String  checkTime;
    private String  assetsName;
    private String status;
    private String sceneName;
}
