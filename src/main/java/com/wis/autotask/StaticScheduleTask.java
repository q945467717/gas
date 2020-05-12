package com.wis.autotask;


import com.alibaba.fastjson.JSON;
import com.wis.annotation.ApiResponse;
import com.wis.mapper.AssetsMapper;
import com.wis.mapper.GasApiMapper;
import com.wis.mapper.ItemMapper;
import com.wis.mapper.SceneMapper;
import com.wis.pojo.po.Assets;
import com.wis.pojo.po.Item;
import com.wis.pojo.po.ItemData;
import com.wis.pojo.po.Scene;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class StaticScheduleTask {

    @Autowired
    private GasApiMapper gasApiMapper;
    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private AssetsMapper assetsMapper;

    //@Scheduled(cron = "*/30 * * * * ?")
    @Scheduled(fixedRate = 30000)
    private void mnsj_func() {

        List<Item> itemList = gasApiMapper.findAllItem();

        for (Item item : itemList) {
            if (item.getWtlx() == 3) {

                List<ItemData> itemDataList = gasApiMapper.findDataByItemId(item.getId());
                for (ItemData itemData : itemDataList) {
                    if (itemData.getPstatus() == 1||itemData.getPstatus()==4) {
                        gasApiMapper.updateWtzt(3, item.getId());
                        break;
                    } else if(itemData.getPstatus() == 2||itemData.getPstatus()==3){
                        if(item.getWtzt()!=2){
                            gasApiMapper.updateWtzt(2, item.getId());
                        }
                    }else {
                        if(item.getWtzt()!=1){
                            gasApiMapper.updateWtzt(1, item.getId());
                        }
                    }
                }
            }
        }

    }

    /**
     * 定时调用模模搭实时数据接口，向场景推送数据
     */
    //@Scheduled(fixedRate = 45000)
    private void sendItemData(){

        OkHttpClient okHttpClient = new OkHttpClient();

        HashMap<String, Object> sceneMap = new HashMap<>();
        List<Scene> scenes = sceneMapper.findAllScene();

        for(Scene scene:scenes){

            List<Item> items = itemMapper.findUidByLxAndSceneId(scene.getSceneId());
            HashMap<String, Object> itemMap = new HashMap<>();


            for(Item item:items){

                List<ItemData> itemDataList = itemMapper.findDataByItemId(item.getId());
                HashMap<String, String> itemDataMap = new HashMap<>();

                Assets assets = assetsMapper.findByAid(item.getAid());

                if(assets!=null){
                    itemDataMap.put("assetsName",assets.getAssetsName());
                }else {
                    itemDataMap.put("assetsName",item.getCname());
                }


                if(itemDataList!=null&&itemDataList.size()>0){
                    for(int i=0;i<itemDataList.size();i++){

                        itemDataMap.put("value"+i,itemDataList.get(i).getPname()+"："+itemDataList.get(i).getPvalue()+itemDataList.get(i).getUnit());

                        if(itemDataList.get(i).getPstatus()!=0){
                            itemDataMap.put("status"+i,String.valueOf(itemDataList.get(i).getPstatus()));
                        }
                    }
                }
                itemMap.put(item.getUid(),itemDataMap);


            }

            sceneMap.put(scene.getSceneId(),itemMap);
        }

        RequestBody body = new FormBody.Builder()
                .add("param", JSON.toJSONString(sceneMap))
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8081/data/putdata")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);

        try{
            Response response = call.execute();
            System.out.println(response.body().string());
        }catch (Exception e){

            e.printStackTrace();
        }

    }

}
