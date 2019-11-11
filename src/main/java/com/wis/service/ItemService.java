package com.wis.service;

import com.wis.pojo.vo.ItemDataInfo;
import com.wis.pojo.vo.ItemInfo;

import java.util.List;

public interface ItemService {

    List<ItemInfo> itemList();

    Integer getTotal(int type,String scene,String name,String uid);

    List<ItemInfo> getItemListPage(int type,String scene,String name,String uid,int limit,int offset);

    void addItem(ItemInfo itemInfo);

    void deleteItem(Integer id);

    void updateItem(ItemInfo itemInfo);

    List<ItemDataInfo> checkItemData(Integer itemId);

    List addDataSource(Integer itemId, Integer[] dataSourceList);
}
