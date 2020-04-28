package com.wis.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CheckedDateDTO {

    @NotNull
    private String checkMember;

    private String checkLog;
    @NotNull
    private String checkTime;
    @NotNull
    private Integer checkItemId;
    @NotNull
    private Integer status;
    @NotNull
    private String checkItemSid;

}
