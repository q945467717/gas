package com.wis.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AssetsDTO {

    private int id;
    @NotBlank(message = "资产名称不能为空")
    private String assetsName;
    @NotBlank(message = "资产设备制造商不能为空")
    private String assetsManufacturer;
    private String assetsTime;
    @Min(value = 1,message = "资产ID不能小于0")
    private int assetsAid;
    @Min(value = 1,message = "场景ID不能小于0")
    private int assetsSid;
    @NotBlank(message = "资产部门不能为空")
    private String assetsDepartment;
    @NotBlank(message = "联系人不能为空")
    private String assetsMember;
    @NotBlank(message = "联系人电话不能为空")
    private String assetsMemberTel;
    @NotBlank(message = "维保时间不能为空")
    private String assetsMaintenance;

}
