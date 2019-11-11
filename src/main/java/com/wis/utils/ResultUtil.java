package com.wis.utils;


import com.wis.pojo.vo.Result;

public class ResultUtil {

    //当正确时返回的值
    public static Result success(int response, Object data){
        Result result = new Result();
        result.setCode(response);
        result.setMsg("OK");
        result.setData(data);
        return result;
    }

//    public static Result success(){
//        return success(null);
//    }

    //当错误时返回的值
    public static Result error(int code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
