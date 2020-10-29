package com.wis.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AssetsDTO {

    private int id;
    @NotBlank(message = "资产名称不能为空")
    private String assetsName;
    private String assetsManufacturer;
    private String assetsTime;
    @Min(value = 1,message = "资产ID不能小于0")
    private int assetsAid;
    @Min(value = 1,message = "场景ID不能小于0")
    private int assetsSid;
    private String assetsDepartment;
    private String assetsMember;
    private String assetsMemberTel;
    private String assetsMaintenance;
    @NotBlank(message = "自编号不能为空")
    private String sinceNumber;

}
