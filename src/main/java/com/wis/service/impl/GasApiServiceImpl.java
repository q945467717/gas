package com.wis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wis.dto.CheckedDateDTO;
import com.wis.exception.SceneNotFindException;
import com.wis.mapper.*;
import com.wis.pojo.po.*;
import com.wis.pojo.vo.*;
import com.wis.service.GasApiService;
import com.wis.utils.CheckDataStatusUtil;
import com.wis.utils.RedisUtil;
import com.wis.webservice.PValueDTO;
import com.wis.webservice.ScadaDataService;
import com.wis.webservice.ScadaStationServiceService;
import com.wis.webservice.StationDTO;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GasApiServiceImpl implements GasApiService {

    @Autowired
    private GasApiMapper gasApiMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private AssetsMapper assetsMapper;
    @Autowired
    private CheckMapper checkMapper;
    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Map> getItemTable(String sceneId) {

        Scene scene = gasApiMapper.findSceneBySceneId(sceneId);

        List<Item> itemList = gasApiMapper.findItemBySid(sceneId);

        List<Map> mapList = new ArrayList<>();


        for (Item item : itemList) {

            Map<String, Object> map = new HashMap<>();

            map.put("id", item.getId());
            map.put("uid", item.getUid());
            map.put("wtlx", item.getWtlx());
            map.put("cname", item.getCname());
            map.put("tblx", item.getTblx());
//            StringBuffer content = new StringBuffer();
//            if (item.getWtlx() == 3) {
//                List<ItemData> itemDataList = gasApiMapper.findItemDataByItemIdAndScadaSid(item.getId(), scene.getScadaSid());
//
//                for (ItemData itemData : itemDataList) {
//
//                    String s = itemData.getPname() + itemData.getPvalue() + itemData.getUnit() + ";";
//                    content.append(s);
//                }
//
//                map.put("content", content);
//            } else {
//                map.put("content", item.getContent());
//            }

//            map.put("wtzt", item.getWtzt());

            mapList.add(map);
        }

        return mapList;

    }

//    @Override
//    public List<Map> getItemYSBH(String sceneId) {
//
////        if (redisUtil.hasKey("item:status:"+sceneId)) {
////
////            List<Object> objects = redisUtil.lGet("item:status:"+sceneId, 0, -1);
////
////            List<Map> mapList = new ArrayList<>();
////            for(Object object:objects){
////
////                String s = JSON.toJSONString(object);
////                Map map = JSONObject.parseObject(s, Map.class);
////
////                mapList.add(map);
////
////            }
////
////            return mapList;
////
////        }
//
//        List<Item> itemList = gasApiMapper.findWarningItem(sceneId);
//
//        Scene scene = gasApiMapper.findSceneBySceneId(sceneId);
//
//        List<Map> mapList = new ArrayList<>();
//
//        for (Item item : itemList) {
//
//            //Assets assets = gasApiMapper.findByAid(item.getAid(), scene.getScadaSid());
//
//            List<ItemData> warningItemData = gasApiMapper.findWarningItemData(scene.getScadaSid(), item.getId());
//
//            Map<String, Object> map = new HashMap<>();
//
//            map.put("uid", item.getUid());
//            map.put("wtlx", item.getWtlx());
//            map.put("wtzt", item.getWtzt());
////            if (assets != null) {
////                map.put("assetsName", assets.getAssetsName());
////            } else {
////                map.put("assetsName", 0);
////            }
//            map.put("cname", item.getCname());
//
//            map.put("warningData", warningItemData);
//
//
//            mapList.add(map);
//
//            //   redisUtil.lSet("item:status:"+sceneId,map,100);
//        }
//
//        //redisUtil.lSet("item:status", mapList);
//
//        return mapList;
//    }
@Override
public List<WarningInfo> getItemYSBH(String sceneId) {

    Scene scene = gasApiMapper.findSceneBySceneId(sceneId);
    List<WarningInfo> warningInfoList = gasApiMapper.findWarningDataBySid(scene.getScadaSid());

    List<WarningInfo> bigWarningInfoList = new ArrayList<>();
    List<WarningInfo> smallWarningInfoList = new ArrayList<>();


    for(WarningInfo warningInfo:warningInfoList){
        if(warningInfo.getPstatus()==1||warningInfo.getPstatus()==4){
            bigWarningInfoList.add(warningInfo);
        }
        if(warningInfo.getPstatus()==2||warningInfo.getPstatus()==3){
            smallWarningInfoList.add(warningInfo);
        }

    }

    List<WarningInfo> warningInfos = new ArrayList<>();
    warningInfos.addAll(bigWarningInfoList);
    warningInfos.addAll(smallWarningInfoList);


    return warningInfos;

}

    @Override
    public String getPinfo(String sceneId) {

        try {
            Scene scene = gasApiMapper.findSceneBySceneId(sceneId);
            String date = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss").format(scene.getUpTime());
            StringBuilder html = new StringBuilder();

            if (!"正常".equals(scene.getSceneStatus())) {
                String s = "<color=white><size=20>" + scene.getSceneName() + "</size> ；" + date + "</color><color=red>；" + scene.getSceneStatus() + "</color>；";
                html.append(s);
            } else {
                String s = "<color=white><size=20>" + scene.getSceneName() + "</size> ；" + date + "；</color><color=green><size=18>" + scene.getSceneStatus() + "</size></color>；";
                html.append(s);
            }
            if ("20180926134038328363371".equals(scene.getSceneId())) {

                String s = "<color=white><size=20>市电状态:</size></color>";
                html.append(s);
            }
            return html.toString();

        } catch (Exception e) {
            return "<color=red><size=20>未获取到最新数据</size></color>";
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

        for (Item item : itemList) {
            if (item.getWtlx() == 3) {

                List<ItemData> itemDataList = gasApiMapper.findDataByItemId(item.getId());
                for (ItemData itemData : itemDataList) {
                    if (itemData.getPstatus() != 0) {
                        gasApiMapper.updateWtzt(3, item.getId());
                        break;
                    } else {
                        gasApiMapper.updateWtzt(1, item.getId());
                    }
                }

            }
        }

    }

//    @Override
//    public StringBuffer getCameraInfo(String sceneId, String uid) {
//
//        Item item = gasApiMapper.findItemBySidAndUid(sceneId, uid);
//
//        StringBuffer html = new StringBuffer();
//
//        if ("rtsp".equals(item.getContent().substring(0, 4))) {
//            String s = "<h1><em id=\"opencamera_name\">" + item.getCname() + "</em><span onclick=\"close_camera('" + item.getUid() + "')\">+</span></h1>" +
//                    "<object type=\"application/x-vlc-plugin\" id=\"vlc\" events=\"True\" width=\"400\" height=\"400\">" +
//                    "<param name=\"mrl\" value=\"" + item.getContent() + "\" />" +
//                    "<param name=\"volume\" value=\"50\" />" +
//                    "<param name=\"autoplay\" value=\"true\" />" +
//                    "<param name=\"loop\" value=\"false\" />" +
//                    "<param name=\"fullscreen\" value=\"false\"/>" +
//                    "</object>";
//            html.append(s);
//        } else {
//            String s = "<h1><em id=\"opencamera_name\">" + item.getCname() + "</em><span onclick=\"close_camera('cam1')\">+</span></h1><p>该位置未录入可支持RTSP数据源</p>";
//            html.append(s);
//        }
//
//        return html;
//
//    }

    @Override
    public List<CameraInfo> getCameraInfo(String sceneId, String[] uids) {

        List<CameraInfo> cameraInfoList = new ArrayList<>();

        for(String uid: uids){
            Item item = gasApiMapper.findItemBySidAndUid(sceneId, uid);
            CameraInfo cameraInfo = new CameraInfo(){{
                setCname(item.getCname());
                setContent(item.getContent());
                setId(item.getId());
                setUid(item.getUid());
            }};

            cameraInfoList.add(cameraInfo);
        }



       // Item item = gasApiMapper.findItemBySidAndUid(sceneId, uid);

//        StringBuffer html = new StringBuffer();
//
//        if ("rtsp".equals(item.getContent().substring(0, 4))) {
//            String s = "<h1><em id=\"opencamera_name\">" + item.getCname() + "</em><span onclick=\"close_camera('" + item.getUid() + "')\">+</span></h1>" +
//                    "<object type=\"application/x-vlc-plugin\" id=\"vlc\" events=\"True\" width=\"400\" height=\"400\">" +
//                    "<param name=\"mrl\" value=\"" + item.getContent() + "\" />" +
//                    "<param name=\"volume\" value=\"50\" />" +
//                    "<param name=\"autoplay\" value=\"true\" />" +
//                    "<param name=\"loop\" value=\"false\" />" +
//                    "<param name=\"fullscreen\" value=\"false\"/>" +
//                    "</object>";
//            html.append(s);
//        } else {
//            String s = "<h1><em id=\"opencamera_name\">" + item.getCname() + "</em><span onclick=\"close_camera('cam1')\">+</span></h1><p>该位置未录入可支持RTSP数据源</p>";
//            html.append(s);
//        }

        return cameraInfoList;

    }

    @Override
    public StringBuffer getMsgInfo(String sceneId, String uid) {
        Item item = gasApiMapper.findItemBySidAndUid(sceneId, uid);
        Scene scene = gasApiMapper.findSceneBySceneId(sceneId);

        StringBuffer html = new StringBuffer();
        String s1 = "<h1><em id=\"opencamera_name\">" + item.getCname() + "</em><span onclick=\"closeinfomsg('" + uid + "')\">+</span></h1>";
        html.append(s1);

        List<ItemData> itemDataList = gasApiMapper.findItemDataByItemIdAndSSidAndType(item.getId(), scene.getScadaSid());

        if (itemDataList.size() == 0) {
            String s = "<p>该设备无接口返回数据</p>";
            html.append(s);
            return html;
        }

        for (ItemData itemData : itemDataList) {
            if ("A".equals(itemData.getPtype())) {
                String s = "<p>" + itemData.getPname() + " " + itemData.getPvalue() + " " + itemData.getUnit() + "</p>";
                html.append(s);
            }
        }
        return html;
    }

    @Override
    @Transactional
    public void autoTask(String sceneId) throws Exception {

        Scene scene = gasApiMapper.findSceneBySceneId(sceneId);

        ScadaStationServiceService scadaStationServiceService = new ScadaStationServiceService();
        ScadaDataService scadaStationServicePort = scadaStationServiceService.getScadaStationServicePort();
        StationDTO stationDataBySid = scadaStationServicePort.findStationDataBySid("2", scene.getScadaSid(), "");
        List<PValueDTO> pvalues = stationDataBySid.getPvalues();

//        Long sid = Long.valueOf(scene.getScadaSid());
//
//        Client client = WebServiceUtil.createWebServiceClient("http://portal.cdgas.com/QJYJWService/scadaService?wsdl");
//        QName qName = new QName("http://scada.ws.qjyj.com/", "findStationDataBySid");
//
//        Object[] objects = client.invoke(qName, "2", sid, "");
//
//        String json = JSON.toJSONString(objects[0]);
//
//        ScadaData stationDataBySid = JSON.parseObject(json, ScadaData.class);
//
//        List<Pvalues> pvalues = stationDataBySid.getPvalues();

        for (PValueDTO pValueDTO : pvalues) {
            //判断数据库是否有该条数据
            ItemData itemData = gasApiMapper.findDataByScadaSidAndPid(scene.getScadaSid(), (int) pValueDTO.getPid(), pValueDTO.getPtype());
            if (itemData != null) {         //有该条数据则更新该条数据
                gasApiMapper.updateData(scene.getScadaSid(), pValueDTO.getPvalue(), itemData.getPid(), pValueDTO.getPtype());
            } else {                        //否则插入数据
                ItemData itemData1 = new ItemData() {{
                    setScadaSid(scene.getScadaSid());
                    setPid((int) pValueDTO.getPid());
                    setPname(pValueDTO.getPname());
                    setPtype(pValueDTO.getPtype());
                    setPvalue(pValueDTO.getPvalue());
                    setPstatus(0);
                    setUnit(pValueDTO.getUnit());
                }};

                gasApiMapper.addData(itemData1);
            }

        }

        //更新整体场站信息
        gasApiMapper.updateSceneDate(stationDataBySid.getStatName(), stationDataBySid.getStatus(), stationDataBySid.getTimeCreated(), scene.getId());
    }

//    @Override
//    public List<EquipmentInfo> getEquipmentInfo(String sceneId, String text) {
//
//        System.out.println(text);
//
//        List<Item> itemList = gasApiMapper.findItemBySidAndLx(sceneId);
//
//        List<EquipmentInfo> itemInfoList = new ArrayList<>();
//
//        for (Item item : itemList) {
//
//            Scene scene = gasApiMapper.findSceneBySceneId(item.getSid());
//
//            Assets assets = gasApiMapper.findByAid(item.getAid(), scene.getScadaSid());
//
//            if (assets != null) {
//                if( assets.getAssetsName().contains(text)){
//                    EquipmentInfo equipmentInfo = new EquipmentInfo();
//                    equipmentInfo.setId(item.getId());
//                    equipmentInfo.setAssets(assets);
//                    equipmentInfo.setCheckedDataList(gasApiMapper.findCheckDataByAid(item.getAid(), scene.getScadaSid()));
//                    equipmentInfo.setUid(item.getUid());
//                    equipmentInfo.setItemName(item.getCname());
//                    equipmentInfo.setStatus(item.getWtzt());
//
//                    itemInfoList.add(equipmentInfo);
//                }
//            }else {
//                if(item.getCname().contains(text)){
//                    EquipmentInfo equipmentInfo = new EquipmentInfo();
//                    equipmentInfo.setId(item.getId());
//                    equipmentInfo.setCheckedDataList(gasApiMapper.findCheckDataByAid(item.getAid(), scene.getScadaSid()));
//                    equipmentInfo.setUid(item.getUid());
//                    equipmentInfo.setItemName(item.getCname());
//                    equipmentInfo.setStatus(item.getWtzt());
//
//
//                    itemInfoList.add(equipmentInfo);
//                }
//            }
//        }
//        return itemInfoList;
//    }

    @Override
    public List<Item> getEquipmentInfo(String sceneId, Integer[] groupId) {

        List<Item> itemList = gasApiMapper.findItemBySidAndLx(sceneId);

        if(groupId.length>0){

//            Map<Integer, Item> itemMap = itemList.stream().collect(
//                    Collectors.toMap(Item::getId, i -> i)
//            );
//
//            List<Item> groupItemList = list.stream().map(itemMap::get
//            ).collect(Collectors.toList());
            List<Item> groups = new ArrayList<>();
            for(Integer id: groupId){
                List<Item> groupList = gasApiMapper.findGroupById(id);
                groups.addAll(groupList);

            }

            return groups;

        }else {
            return itemList;
        }
    }

//@Override
//public List<Assets> getEquipmentInfo(String sceneId, String text) {
//
//    Scene scene = sceneMapper.findSceneById(sceneId);
//
//    assetsMapper.findBySid(scene.getScadaSid());
//
//    return assetsMapper.findBySid(scene.getScadaSid());
//
//}

    @Override
    public String getElectricity(String sceneId) {

        Scene scene = gasApiMapper.findSceneBySceneId(sceneId);

        ItemData itemData = gasApiMapper.findItemDataByPnameAndScadaSid("%市电状态", scene.getScadaSid());

        if (itemData != null) {
            return itemData.getPvalue();

        }

        return null;

    }

    @Override
    public List<CheckedDateDTO> getCheckedDate(Integer sceneId) {
        return gasApiMapper.findAllCheckDate(sceneId);
    }

    @Override
    public String getSceneId(String id) {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8081/init/scene.config")
                .build();

        Call call = okHttpClient.newCall(request);

        try {
            Response response = call.execute();
            if (response.body() != null) {
                Map<String, Object> map = JSONObject.parseObject(response.body().string());
                return (String) map.get(id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ItemPanelInfo getItemInfo(Integer id) throws SceneNotFindException {
        Item item = itemMapper.findItemById(id);

        if (item != null) {
            return new ItemPanelInfo() {{
                setAssets(assetsMapper.findByAid(item.getAid()));
                setItemDataInfoList(itemMapper.findDataByItemId(item.getId()));

                //查询物体所在场景
                Scene scene = sceneMapper.findSceneById(item.getSid());
                if (scene != null) {
                    LinkedList<CheckedData> checkedDataList = checkMapper.findShowCheckedData(item.getAid(), scene.getScadaSid());
                    List<CheckInfo> checkInfoList = new ArrayList<>();

                    if (checkedDataList.size() > 0) {
                        for (CheckedData checkedData : checkedDataList) {
                            CheckInfo checkInfo = new CheckInfo() {{
                                setCheckMember(checkedData.getCheckMember());
                                setStatus(CheckDataStatusUtil.statusVo(checkedData.getStatus()));
                                setCheckTime(checkedData.getCheckTime());
                            }};

                            checkInfoList.add(checkInfo);
                        }
                    }

                    //设置巡检数据list
                    setCheckInfoList(checkInfoList);
                    setSceneName(scene.getScadaName());
                    setStatus(item.getWtzt());
                    setItemName(item.getCname());
                    setUid(item.getUid());

                }else {
                    throw new SceneNotFindException("场景信息异常");
                }
            }};

        }

        return null;
    }

    @Override
    public void getWarning() {

    }

    @Override
    public List<AssetsNavInfo> showAssets(String sceneId) {

        Scene scene = sceneMapper.findSceneById(sceneId);

        List<Assets> assetsList = assetsMapper.findBySid(scene.getScadaSid());

        List<AssetsNavInfo> assetsNavInfoList = new ArrayList<>();

        for (Assets assets : assetsList) {

            Item item = itemMapper.findByAidAndSid(scene.getSceneId(), assets.getAid());

            if (item != null) {
                AssetsNavInfo assetsNavInfo = new AssetsNavInfo() {{
                    setAssetsName(item.getCname());
                    setItemId(item.getId());
                    setStatus(item.getWtzt());
                    setUid(item.getUid());
                    setType(item.getWtlx());

                }};

                assetsNavInfoList.add(assetsNavInfo);
            }

        }


        return assetsNavInfoList;
    }

    @Override
    public List<GroupInfo> showGroup(String sceneId) {

        List<Group> groups = gasApiMapper.findAllGroup(sceneId);

        List<GroupInfo> groupInfoList = new ArrayList<>();

        for(Group group:groups){

            GroupInfo groupInfo = new GroupInfo(){{
                setGroupName(group.getGroupName());
                setId(group.getId());
            }};

            groupInfoList.add(groupInfo);

        }

        return groupInfoList;

    }
}
