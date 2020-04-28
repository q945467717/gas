package com.wis.exception;

import com.wis.utils.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SceneNotFindException extends Exception{

    private ResponseCode code;

    public SceneNotFindException(ResponseCode code) {
        this.code = code;
    }

    public SceneNotFindException(Throwable cause, ResponseCode code) {
        super(cause);
        this.code = code;
    }

    public SceneNotFindException(){}

}
