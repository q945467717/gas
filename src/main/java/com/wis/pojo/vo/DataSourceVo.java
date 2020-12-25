package com.wis.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DataSourceVo {

    private Integer id;
    private String pname;
    private Integer pid;
    private String dataName;
    private String stationName;
    private String uid;
    private String  itemName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    private int sid;

}
