package com.wis.pojo.po;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Assets {

    @NotNull
    private Integer id;
    private String assetsName;
    private String assetsManufacturer;
    private String assetsTime;
    @NotNull
    private Integer aid;
    @NotNull
    private Integer assetsSid;

}
