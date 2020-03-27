package com.wis.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CheckedDateDTO {

    private Integer id;

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
