package com.wis.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class CheckedData {


    private Integer id;
    private String checkMember;
    private String checkLog;
    private String  checkTime;
    private Integer itemAid;
    private Integer status;
    private Integer sid;
}
