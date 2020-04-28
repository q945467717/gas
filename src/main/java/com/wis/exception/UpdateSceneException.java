package com.wis.exception;

import com.wis.utils.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateSceneException extends Exception{

    private ResponseCode code;

    public UpdateSceneException(ResponseCode code) {
        this.code = code;
    }

    public UpdateSceneException(Throwable cause, ResponseCode code) {
        super(cause);
        this.code = code;
    }

    public UpdateSceneException(){}

}
