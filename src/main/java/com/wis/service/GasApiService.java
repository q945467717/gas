package com.wis.service;

import com.wis.dto.CheckedDateDTO;
import com.wis.pojo.vo.EquipmentInfo;
import com.wis.pojo.vo.ItemInfo;

import java.util.List;
import java.util.Map;

public interface GasApiService {

    //获取所有物体信息
    List<Map> getItemTable(String sceneId);

    //获取会产生告警的设备记录
    List<Map> getItemYSBH(String sceneId);

    //获取场站整体情况
    String getPinfo(String sceneId);

    //更新设备告警信息
    void mnsj_func(String sceneId);

    //获取摄像机实时画面接口
    StringBuffer getCameraInfo(String sceneId, String uid);

    //获取设备数据接口
    StringBuffer getMsgInfo(String sceneId, String uid);

    //向webservice接口请求数据
    void autoTask(String sceneId) throws Exception;

    List<EquipmentInfo> getEquipmentInfo(String sceneId,String text);

    String getElectricity(String sceneId);

    //获取巡检巡查数据接口
    List<CheckedDateDTO> getCheckedDate(Integer sceneId);

    //根据id获取场景id
    String getSceneId(String id);

}
