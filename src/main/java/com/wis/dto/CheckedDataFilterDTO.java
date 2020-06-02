package com.wis.dto;

import lombok.Data;

@Data
public class CheckedDataFilterDTO {

    private String startTime;
    private String endTime;
    private String assetsName;
    private String checkMember;
    private int scadaSid;
    private Integer status;
    private Integer limit;
    private Integer offset;

}
