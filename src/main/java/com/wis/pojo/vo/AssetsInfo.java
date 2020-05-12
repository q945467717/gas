package com.wis.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AssetsInfo {

    private Integer id;
    private String assetsName;
    private String assetsManufacturer;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date assetsTime;
    private Integer aid;
    private String  sceneName;
    private String uid;
    private Integer sid;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date assetsMaintenance;
}
