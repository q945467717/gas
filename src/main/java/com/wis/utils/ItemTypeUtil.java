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
            return "流量计";
        }
        if(wtlx==4){
            return "压力变送器";
        }
        if(wtlx==5){
            return "其他";
        }
        return null;
    }

    public static int nameToId(String wtlx){
        if("办公大楼".equals(wtlx)){
            return 1;
        }
        if("摄像头".equals(wtlx)){
            return 2;
        }
        if("流量计".equals(wtlx)){
            return 3;
        }
        if("压力变送器".equals(wtlx)){
            return 4;
        }
        if("其他".equals(wtlx)){
            return 5;
        }
            return 6;
    }

}
