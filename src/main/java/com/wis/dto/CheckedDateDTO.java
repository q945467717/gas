package com.wis.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
