package com.wis.autotask;


import com.alibaba.fastjson.JSON;
import com.wis.mapper.AssetsMapper;
import com.wis.mapper.GasApiMapper;
import com.wis.mapper.ItemMapper;
import com.wis.mapper.SceneMapper;
import com.wis.pojo.po.*;
import com.wis.utils.WebServiceUtil;
import com.wis.webservice.PValueDTO;
import com.wis.webservice.ScadaDataService;
import com.wis.webservice.ScadaStationServiceService;
import com.wis.webservice.StationDTO;
import okhttp3.*;
import org.apache.cxf.endpoint.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.xml.namespace.QName;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
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
    public void mnsj_func() {

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
    public void sendItemData(){

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

    //@Scheduled(cron = "0 0 0 * * ?")
    //@Scheduled(fixedRate = 30000)
    public void deleteExpireData() {

        gasApiMapper.deleteExpireData();

    }

    //获取SCADA系统数据
    @Scheduled(fixedRate = 45000)
    public void getScadaDate() {

        ArrayList<Scene> sceneList = sceneMapper.findAllScene();

        ScadaStationServiceService scadaStationServiceService;
        ScadaDataService scadaStationServicePort;
        StationDTO stationDataBySid;
        List<PValueDTO> pvalues;

        if(!StringUtils.isEmpty(sceneList)){
            for(Scene scene:sceneList){

                scadaStationServiceService = new ScadaStationServiceService();
                scadaStationServicePort = scadaStationServiceService.getScadaStationServicePort();
                stationDataBySid = scadaStationServicePort.findStationDataBySid("2", scene.getScadaSid(), "");
                pvalues = stationDataBySid.getPvalues();

                ItemData itemData1;
                if(!StringUtils.isEmpty(pvalues)){
                    for (PValueDTO pValueDTO : pvalues) {

                        Date date = new Date();

                        //判断数据库是否有该条数据
                        ItemData itemData = gasApiMapper.findDataByScadaSidAndPid(scene.getScadaSid(), (int) pValueDTO.getPid(), pValueDTO.getPtype());
                        if (!StringUtils.isEmpty(itemData)) {         //有该条数据则更新该条数据
                            gasApiMapper.updateData(scene.getScadaSid(), pValueDTO.getPvalue(), itemData.getPid(), pValueDTO.getPtype(),date);
                        } else {                        //否则插入数据
                            itemData1 = new ItemData() {{
                                setScadaSid(scene.getScadaSid());
                                setPid((int) pValueDTO.getPid());
                                setPname(pValueDTO.getPname());
                                setPtype(pValueDTO.getPtype());
                                setPvalue(pValueDTO.getPvalue());
                                setUpdateTime(date);
                                setUnit(pValueDTO.getUnit());
                            }};
                            gasApiMapper.addData(itemData1);
                        }
                    }
                }
                //更新整体场站信息
                gasApiMapper.updateSceneDate(stationDataBySid.getStatName(), stationDataBySid.getStatus(), stationDataBySid.getTimeCreated(), scene.getId());
            }
        }
    }

//    @Scheduled(fixedRate = 45000)
//    public void getScadaDate() {
//
//        ArrayList<Scene> sceneList = sceneMapper.findAllScene();
//
//        ScadaData scadaData;
//        String json;
//        List<Pvalues> list;
//
//        if(!StringUtils.isEmpty(sceneList)){
//            for(Scene scene:sceneList){
//                try {
//                    Client client = WebServiceUtil.createWebServiceClient("http://portal.cdgas.com/QJYJWService/scadaService?wsdl");
//                    QName qName = new QName("http://scada.ws.qjyj.com/", "findStationDataBySid");
//                    Object[] objects = client.invoke(qName, "2", (long)scene.getScadaSid(), "");
//
//                    json = JSON.toJSONString(objects[0]);
//                    scadaData = JSON.parseObject(json, ScadaData.class);
//                    list = scadaData.getPvalues();
//
//                    ItemData itemData1;
//                    ItemData itemData;
//                    if(!StringUtils.isEmpty(list)){
//                        for (Pvalues pvalue : list) {
//                            Date date = new Date();
//                            //判断数据库是否有该条数据
//                            itemData = gasApiMapper.findDataByScadaSidAndPid(scene.getScadaSid(),(int)pvalue.getPid(), pvalue.getPtype());
//                            if (!StringUtils.isEmpty(itemData)) {         //有该条数据则更新该条数据
//                                gasApiMapper.updateData(scene.getScadaSid(), pvalue.getPvalue(), itemData.getPid(), pvalue.getPtype(),date);
//                                //System.out.println(pvalue);
//                            } else {                        //否则插入数据
//                                itemData1 = new ItemData() {{
//                                    setScadaSid(scene.getScadaSid());
//                                    setPid((int)pvalue.getPid());
//                                    setPname(pvalue.getPname());
//                                    setPtype(pvalue.getPtype());
//                                    setPvalue(pvalue.getPvalue());
//                                    setUpdateTime(date);
//                                    setUnit(pvalue.getUnit());
//                                }};
//                                //System.out.println(pvalue);
//
//                                gasApiMapper.addData(itemData1);
//                            }
//                        }
//                    }
//                    //更新整体场站信息
//                    gasApiMapper.updateSceneDate(scadaData.getStatName(), scadaData.getStatus(), scadaData.getTimeCreated(), scene.getId());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
