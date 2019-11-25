package com.wis.service.impl;

import com.wis.mapper.ItemMapper;
import com.wis.mapper.SceneMapper;
import com.wis.pojo.po.Item;
import com.wis.pojo.po.ItemData;
import com.wis.pojo.po.Scene;
import com.wis.pojo.vo.ItemDataInfo;
import com.wis.pojo.vo.ItemInfo;
import com.wis.service.ItemService;
import com.wis.utils.ItemTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private SceneMapper sceneMapper;

    @Override
    public List<ItemInfo> itemList() {

        List<Item> itemList = itemMapper.findAllItem();

        List<ItemInfo> itemInfoList = new ArrayList<>();

        for(Item item:itemList){
            ItemInfo itemInfo = new ItemInfo();
            itemInfo.setId(item.getId());
            itemInfo.setItemName(item.getCname());
            Scene scene = sceneMapper.findSceneById(item.getSid());
            itemInfo.setSceneName(scene.getSceneName());
            itemInfo.setItemType(ItemTypeUtil.type(item.getWtlx()));
            itemInfo.setUid(item.getUid());
            itemInfo.setText(item.getContent());

            itemInfoList.add(itemInfo);

        }

        return itemInfoList;
    }

    @Override
    public Integer getTotal(int type,String scene,String name,String uid) {

        Item item = new Item();

        item.setCname(name);
        item.setWtlx(type);
        item.setUid(uid);
        item.setSid(scene);

        return itemMapper.getTotal(item);
    }

    @Override
    public List<ItemInfo> getItemListPage(int type,String scene,String name,String uid,int limit,int offset) {

        Item item = new Item();

        item.setCname(name);
        item.setWtlx(type);
        item.setUid(uid);
        item.setSid(scene);
        item.setOffset(offset);
        item.setLimit(limit);

        List<Item> itemList = itemMapper.getItemListPage(item);

        List<ItemInfo> itemInfoList = new ArrayList<>();

        for(Item item1:itemList){
            ItemInfo itemInfo1 = new ItemInfo();
            itemInfo1.setId(item1.getId());
            itemInfo1.setText(item1.getContent());
            itemInfo1.setUid(item1.getUid());
            itemInfo1.setItemType(ItemTypeUtil.type(item1.getWtlx()));
            Scene scene1 = sceneMapper.findSceneById(item1.getSid());
            itemInfo1.setSceneName(scene1.getSceneName());
            itemInfo1.setItemName(item1.getCname());

            itemInfoList.add(itemInfo1);
        }

        return itemInfoList;
    }

    //添加物体
    @Override
    public void addItem(ItemInfo itemInfo) {

        Item item = new Item();
        item.setWtlx(ItemTypeUtil.nameToId(itemInfo.getItemType()));
        Scene scene = sceneMapper.findSceneByName(itemInfo.getSceneName());

        item.setSid(scene.getSceneId());
        item.setUid(itemInfo.getUid());
        item.setAddtime(new Date());
        item.setCname(itemInfo.getItemName());
        item.setContent(itemInfo.getText());

        itemMapper.addItem(item);

    }

    //删除物体
    @Override
    public void deleteItem(Integer id) {
        itemMapper.deleteItemById(id);
    }

    //修改物体
    @Override
    public void updateItem(ItemInfo itemInfo) {
        int id = itemInfo.getId();
        Scene scene = sceneMapper.findSceneByName(itemInfo.getSceneName());
        String sid = scene.getSceneId();
        String uid = itemInfo.getUid();
        int wtlx = ItemTypeUtil.nameToId(itemInfo.getItemType());
        String cname = itemInfo.getItemName();
        String content = itemInfo.getText();

        itemMapper.updateItemById(id,sid,uid,wtlx,cname,content);
    }

    //展示数据源
    @Override
    public List<ItemDataInfo> checkItemData(Integer itemId) {

        List<ItemData> itemDataList = itemMapper.findDataByItemId(itemId);

        List<ItemDataInfo> itemDataInfoList = new ArrayList<>();

        for(ItemData itemData:itemDataList){
            ItemDataInfo itemDataInfo = new ItemDataInfo();
            itemDataInfo.setId(itemData.getId());
            itemDataInfo.setPid(itemData.getPid());

            itemDataInfoList.add(itemDataInfo);
        }

        return itemDataInfoList;
    }

    //添加数据源
    @Override
    public List addDataSource(Integer itemId, Integer[] dataSourceList) {

        List<Integer> errorList = new ArrayList<>(Arrays.asList(dataSourceList));

        Item item = itemMapper.findItemById(itemId);
        Scene scene = sceneMapper.findSceneById(item.getSid());
        List<ItemData> itemDataList = itemMapper.findDataByScadaId(scene.getScadaSid());
        for(ItemData itemData:itemDataList){
            for(Integer dataSource:dataSourceList){
                if(itemData.getPid()==dataSource){
                    itemMapper.updateItemData(scene.getScadaSid(),itemId,itemData.getPid());
                    errorList.remove(dataSource);
                }
            }
        }
        return errorList;

    }
}
