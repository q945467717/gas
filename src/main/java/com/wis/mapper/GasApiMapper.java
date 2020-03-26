package com.wis.mapper;

import com.wis.pojo.po.Item;
import com.wis.pojo.po.ItemData;
import com.wis.pojo.po.Scene;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

@Repository
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

    @Update("update wis_scene set s_name=#{scadaName},s_val=#{stationStatus},uptime=#{updateTime} where id=#{id}")
    void updateSceneDate(String scadaName, String stationStatus, String updateTime,Integer id);

    @Select("select * from wis_item_data where s_id=#{scadaSid}")
    List<ItemData> findDataByScadaSid(Integer scadaSid);

    @Insert("insert into wis_item_data(s_id,pid,pname,ptype,pvalue,pstatus,unit) values(#{scadaSid},#{pid},#{pname},#{ptype},#{pvalue},#{pstatus},#{unit})")
    void addData(ItemData itemData);

    @Update("update wis_item_data set pvalue=#{pvalue} where pid=#{pid} and s_id=#{scadaSid} and ptype=#{ptype}")
    void updateData(Integer scadaSid,String pvalue,Integer pid,String ptype);


    @ResultMap("ItemDataResultMap")
    @Select("select * from wis_item_data where s_id=#{scadaSid} and pid=#{pid} and ptype=#{ptype}")
    ItemData findDataByScadaSidAndPid(Integer scadaSid,Integer pid,String ptype);



}
