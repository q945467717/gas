package com.wis.pojo.vo;

import com.wis.utils.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ApiResult implements Serializable {

    private Integer code;
    private String msg;
    private Object data;

    public ApiResult(ResponseCode responseCode,Object data) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
        this.data = data;
    }

}
