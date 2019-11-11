package com.wis.utils;

public class ItemTypeUtil {


    public static String type(int wtlx){

        if(wtlx==1){
            return "办公大楼";
        }
        if(wtlx==2){
            return "摄像头";
        }
        if(wtlx==3){
            return "信息牌类";
        }
        if(wtlx==4){
            return "阀门";
        }
        if(wtlx==5){
            return "地面";
        }
        return "管线";
    }

    public static int nameToId(String wtlx){
        if(wtlx.equals("办公大楼")){
            return 1;
        }
        if(wtlx.equals("摄像头")){
            return 2;
        }
        if(wtlx.equals("信息牌类")){
            return 3;
        }
        if(wtlx.equals("阀门")){
            return 4;
        }
        if(wtlx.equals("地面")){
            return 5;
        }
            return 6;
    }

}
