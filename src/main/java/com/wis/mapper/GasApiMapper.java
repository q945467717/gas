package com.wis.mapper;

import com.wis.pojo.po.Item;
import com.wis.pojo.po.ItemData;
import com.wis.pojo.po.Scene;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GasApiMapper {

    @ResultMap("SceneResultMap")
    @Select("select * from wis_scene where sceneid=#{sceneId}")
    Scene findSceneBySceneId(String sceneId);

    @Select("select * from wis_item where sid=#{sceneId} order by id ASC")
    List<Item> findItemBySid(String sceneId);

    @Select("select * from wis_item where sid=#{sceneId} and wtlx IN (2,3,4) order by id ASC")
    List<Item> findItemBySidAndLx(String sceneId);

    @Select("select * from wis_item where sid=#{sceneId} and uid=#{uid}")
    Item findItemBySidAndUid(String sceneId,String uid);

    @ResultMap("ItemDataResultMap")
    @Select("SELECT * FROM wis_item_data WHERE item_id=#{itemId} AND s_id=#{scadaSid}")
    List<ItemData> findItemDataByItemIdAndScadaSid(Integer itemId,Integer scadaSid);

    @ResultMap("ItemDataResultMap")
    @Select("SELECT * FROM wis_item_data WHERE item_id=#{itemId} AND s_id=#{scadaSid} and ptype='A'")
    List<ItemData> findItemDataByItemIdAndSSidAndType(Integer itemId,Integer scadaSid);

    @Select("select * from wis_item_data where item_id=#{itemId}")
    List<ItemData> findDataByItemId(Integer itemId);

    @Select("SELECT * FROM wis_item WHERE sid=#{sceneId} AND wtlx IN (3,4) ORDER BY id ASC")
    List<Item> findItemBySidAndWarning(String sceneId);

    @Update("update wis_item set wtzt=#{wtzt} where id=#{id}")
    void updateWtzt(Integer wtzt,Integer id);

    @Select("select * from wis_item_data where pname=#{Pname} and s_id=#{scadaSid}")
    ItemData findItemDataByPnameAndScadaSid(String Pname,String  scadaSid);

}
