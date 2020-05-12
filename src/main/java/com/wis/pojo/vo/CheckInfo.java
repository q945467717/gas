package com.wis.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class CheckInfo {

    private Integer id;
    private String checkMember;
    private String checkLog;
    @JsonFormat(pattern="yyyy-MM-dd HH-mm-ss",timezone = "GMT+8")
    private String  checkTime;
    private String  assetsName;
    private String status;
    private String sceneName;
}
