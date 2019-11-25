package com.wis.service.impl;

import com.wis.mapper.GasApiMapper;
import com.wis.pojo.po.Item;
import com.wis.pojo.po.ItemData;
import com.wis.pojo.po.Scene;
import com.wis.pojo.vo.ItemInfo;
import com.wis.service.GasApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GasApiServiceImpl implements GasApiService {

    @Autowired
    private GasApiMapper gasApiMapper;

    @Override
    public List<Map> getItemTable(String sceneId) {

        Scene scene = gasApiMapper.findSceneBySceneId(sceneId);
        List<Item> itemList = gasApiMapper.findItemBySid(sceneId);

        List<Map> mapList = new ArrayList<>();


        for(Item item:itemList){

            Map<String,Object> map = new HashMap<>();

            map.put("uid",item.getUid());
            map.put("wtlx",item.getWtlx());
            map.put("cname",item.getCname());
            map.put("tblx",item.getTblx());
            StringBuffer content = new StringBuffer();
            if(item.getWtlx()==3){
                List<ItemData> itemDataList = gasApiMapper.findItemDataByItemIdAndScadaSid(item.getId(), scene.getScadaSid());

                for(ItemData itemData:itemDataList){

                    String s = itemData.getPname()+itemData.getPvalue()+itemData.getUnit()+";";
                    content.append(s);
                }

                map.put("content",content);
            }else {
                map.put("content",item.getContent());
            }

            map.put("wtzt",item.getWtzt());

            mapList.add(map);
        }

        return mapList;

    }

    @Override
    public List<Map> getItemYSBH(String sceneId) {

        List<Item> itemList = gasApiMapper.findItemBySidAndWarning(sceneId);

        List<Map> mapList = new ArrayList<>();

        for(Item item:itemList){
            Map<String,Object> map = new HashMap<>();

            map.put("uid",item.getUid());
            map.put("wtlx",item.getWtlx());
            map.put("wtzt",item.getWtzt());
            map.put("qpzt",item.getQpzt());

            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public StringBuffer getPinfo(String sceneId){

        try {
            Scene scene = gasApiMapper.findSceneBySceneId(sceneId);
            String date = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss").format(scene.getUpTime());
            StringBuffer html=new StringBuffer();

            if(!scene.getSceneStatus().equals("正常")){
                String s = "<color=white><size=20>"+scene.getScadaName()+"</size> ；"+date+"</color><color=red>"+scene.getSceneStatus()+"</color>；";
                html.append(s);
            }else {
                String s = "<color=white><size=20>"+scene.getScadaName()+"</size> ；"+date+"</color><color=green><size=18>；"+scene.getSceneStatus()+"</size></color>；";
                html.append(s);
            }
            if(scene.getSceneId().equals("20180926134038328363371")){

                String s ="<color=white><size=20>市电状态:</size></color>";
                html.append(s);
            }
            return html;

        }catch (Exception e){
            String s = "<color=red><size=20>未获取到最新数据</size></color>";
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(s);
            return stringBuffer;
        }


//        List<Map> list = new ArrayList<>();
//
//        Map<String,String> map = new HashMap<>();
//
//        map.put("scadaName",scene.getSceneName());
//        map.put("uptime",date);
//        map.put("sceneStatus",scene.getSceneStatus());
//
//        list.add(map);
//        return list;
    }

    @Override
    public void mnsj_func(String sceneId) {

        List<Item> itemList = gasApiMapper.findItemBySidAndWarning(sceneId);

        for(Item item:itemList){
            if(item.getWtlx()==3){

                List<ItemData> itemDataList = gasApiMapper.findDataByItemId(item.getId());
                for(ItemData itemData:itemDataList){
                    if(itemData.getPstatus()!=0){
                        gasApiMapper.updateWtzt(3,item.getId());
                        break;
                    }else {
                        gasApiMapper.updateWtzt(1,item.getId());
                    }
                }

            }
        }

    }

    @Override
    public StringBuffer getCameraInfo(String sceneId, String uid) {

        Item item = gasApiMapper.findItemBySidAndUid(sceneId, uid);

        StringBuffer html = new StringBuffer();

            if(item.getContent().substring(0, 4).equals("rtsp")){
                String s ="<h1><em id=\"opencamera_name\">"+item.getCname()+"</em><span onclick=\"close_camera('"+item.getUid()+"')\">+</span></h1>"+
                          "<object type=\"application/x-vlc-plugin\" id=\"vlc\" events=\"True\" width=\"400\" height=\"400\">"+
                          "<param name=\"mrl\" value=\""+item.getContent()+"\" />"+
                          "<param name=\"volume\" value=\"50\" />"+
                          "<param name=\"autoplay\" value=\"true\" />"+
                          "<param name=\"loop\" value=\"false\" />"+
                          "<param name=\"fullscreen\" value=\"false\"/>"+
                          "</object>";
                html.append(s);
            }else {
                String s ="<h1><em id=\"opencamera_name\">"+item.getCname()+"</em><span onclick=\"close_camera('cam1')\">+</span></h1><p>该位置未录入可支持RTSP数据源</p>";
                html.append(s);
            }

        return html;

    }

    @Override
    public StringBuffer getMsgInfo(String sceneId, String uid) {
        Item item = gasApiMapper.findItemBySidAndUid(sceneId, uid);
        Scene scene = gasApiMapper.findSceneBySceneId(sceneId);

        StringBuffer html = new StringBuffer();
        String s1 = "<h1><em id=\"opencamera_name\">"+item.getCname()+"</em><span onclick=\"closeinfomsg('"+uid+"')\">+</span></h1>";
        html.append(s1);

        List<ItemData> itemDataList = gasApiMapper.findItemDataByItemIdAndSSidAndType(item.getId(), scene.getScadaSid());

        if(itemDataList.size()==0){
            String s="<p>该设备无接口返回数据</p>";
            html.append(s);
            return html;
        }

        for(ItemData itemData:itemDataList){
            String s="<p>"+itemData.getPname()+" "+itemData.getPvalue()+" "+itemData.getUnit()+"</p>";
            html.append(s);
        }
        return html;
    }

    @Override
    public void autoTask(String sceneId) {

//        try {
//            Client client = WebServiceUtil.createWebServiceClient();
//            Object[] objects = client.invoke("");
//
//            System.out.println(objects[0].toString());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    @Override
    public List<ItemInfo> getEquipmentInfo(String sceneId) {

        List<Item> itemList = gasApiMapper.findItemBySidAndLx(sceneId);

        List<ItemInfo> itemInfoList = new ArrayList<>();

        for(Item item:itemList){
            ItemInfo itemInfo = new ItemInfo();
            itemInfo.setUid(item.getUid());
            itemInfo.setItemName(item.getCname());

            itemInfoList.add(itemInfo);
        }
        return itemInfoList;
    }

    @Override
    public String getElectricity(String sceneId) {

        Scene scene = gasApiMapper.findSceneBySceneId(sceneId);

        if(scene.getSceneId().equals("20180926134038328363371")){

            ItemData itemData = gasApiMapper.findItemDataByPnameAndScadaSid("九眼桥CNG市电状态","60");
            if(itemData!=null){
                return itemData.getPvalue();
            }
        }

        return null;

    }
}
