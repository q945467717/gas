package com.wis.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("wis_item_assets")
public class Assets {

    private Integer id;
    private String assetsName;
    private String assetsManufacturer;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private LocalDateTime assetsTime;
    @TableField("assets_aid")
    private Integer aid;
    private Integer assetsSid;
    private String assetsDepartment;
    private String assetsMember;
    @TableField("assets_member_tel")
    private String memberTel;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private LocalDateTime assetsMaintenance;//维保时间

}
