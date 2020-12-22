package com.wis.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class CheckedDateDTO {

    private int id;
    @NotBlank(message = "巡检人不能为空")
    private String checkMember;

    private String checkLog;
    @NotBlank(message = "巡检时间不能为空")
    private String checkTime;
    @Min(value = 1,message = "巡检设备id应大于0")
    private Integer checkItemId;
    private Integer status;
    @Min(value = 1,message = "巡检设备sid应大于0")
    private int checkItemSid;

}
