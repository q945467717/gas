package com.wis.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ApiResult implements Serializable {

    private Integer code;
    private String msg;
    private Object data;
}
