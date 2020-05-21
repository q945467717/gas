package com.wis.exception;

import com.wis.utils.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SceneNotFindException extends RuntimeException{

    private ResponseCode code;
    private String msg;

    public SceneNotFindException(ResponseCode code) {
        this.code = code;
    }

    public SceneNotFindException(Throwable cause, ResponseCode code) {
        super(cause);
        this.code = code;
    }

    public SceneNotFindException(){}

    public SceneNotFindException(String msg){

        this.msg = msg;
    }


}
