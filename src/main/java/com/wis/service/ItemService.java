package com.wis.service;

import com.wis.pojo.vo.ItemDataInfo;
import com.wis.pojo.vo.ItemInfo;

import java.util.List;

public interface ItemService {

    List<ItemInfo> itemList();

    Integer getTotal(int type,String scene,String name,String uid);

    List<ItemInfo> getItemListPage(int type,String scene,String name,String uid,int limit,int offset);

    //添加设备
    void addItem(ItemInfo itemInfo);
    //删除设备
    void deleteItem(Integer id);
    //修改设备
    void updateItem(ItemInfo itemInfo);

    List<ItemDataInfo> checkItemData(Integer itemId);

    List<Integer> addDataSource(Integer itemId, Integer[] dataSourceList);

    //获取单个场景中设备类型为2，3，4,5的所有设备
    List<ItemInfo> getItemListBySceneId(String sceneId);

    //获取单个场景中设备类型为3，4,5的所有设备
    List<ItemInfo> getItemListBySceneIdNotCamera(String sceneId);
}
