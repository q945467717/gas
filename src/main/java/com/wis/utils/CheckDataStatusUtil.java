package com.wis.utils;

public class CheckDataStatusUtil {

    public static String statusVo(Integer status){

        switch (status){
            case 1:return "正常";
            case 2:return "异常";
        }

        return "error";

    }
}
