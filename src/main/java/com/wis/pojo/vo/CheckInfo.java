package com.wis.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CheckInfo {

    private Integer id;
    private String checkMember;
    private String checkLog;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime checkTime;
    private String assetsName;
    private String status;
    private String sceneName;
}
