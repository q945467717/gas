package com.wis.utils;

import com.wis.exception.BaseErrorInfoInterface;

public enum  ResponseCode implements BaseErrorInfoInterface {

    SUCCESS(10000, "success"),
    MATCHING_SUCCESS(10003,"场景ID匹配成功"),
    UPDATE_SUCCESS(10004,"修改成功"),
    ADD_SUCCESS(10005,"添加成功"),
    DELETE_SUCCESS(10006,"删除成功"),
    UPLOAD_SUCCESS(10007,"上传场景成功"),

    AUTHORITY_ERROR(40001,"未登录或token过期"),
    ACCESS_ERROR(40002,"登录用户无访问权限"),

    SERVICE_ERROR(50000, "服务器异常"),
    VALIDATED_ERROR(50002,"请求参数错误"),
    SCENE_NOT_FIND(50011,"场景信息异常"),
    GROUP_REPEAT(50021,"分组已存在"),
    GROUP_NOT_FIND(50022,"分组不存在"),
    ITEM_NOT_FIND(50031,"物体不存在"),
    ITEM_REPEAT(50032,"物体已存在"),
    ASSETS_REPEAT(50032,"物体已存在"),


    ;

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
