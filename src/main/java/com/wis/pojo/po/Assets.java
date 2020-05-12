package com.wis.pojo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Assets {

    @NotNull
    private Integer id;
    private String assetsName;
    private String assetsManufacturer;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date assetsTime;
    @NotNull
    private Integer aid;
    @NotNull
    private Integer assetsSid;
    private String assetsDepartment;
    private String assetsMember;
    private String memberTel;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date assetsMaintenance;

}
