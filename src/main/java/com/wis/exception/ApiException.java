package com.wis.exception;

import com.wis.utils.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiException extends RuntimeException{

    private ResponseCode code;

    public ApiException(ResponseCode code) {
        this.code = code;
    }

    public ApiException(Throwable cause, ResponseCode code) {
        super(cause);
        this.code = code;
    }
}
