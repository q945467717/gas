package com.wis.utils;

import com.wis.exception.BaseErrorInfoInterface;

public enum  ResponseCode implements BaseErrorInfoInterface {

    SUCCESS(10000, "success"),
    SERVICE_ERROR(50000, "服务器异常"),
    VALIDATED_ERROR(10002,"请求参数错误"),
    MATCHING_SUCCESS(10003,"场景ID匹配成功"),
    UPDATE_SUCCESS(10004,"修改成功"),
    AUTHORITY_ERROR(40001,"未登录或token过期"),
    SCENE_NOT_FIND(41000,"场景信息异常"),
    ACCESS_ERROR(40002,"登录用户无访问权限");

    /**
     * 状态码
     */
    private int code;
    /**
     * 返回信息
     */
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
