package com.wis.mapper;

import com.wis.dto.CheckedDateDTO;
import com.wis.pojo.po.*;
import com.wis.pojo.vo.GroupInfo;
import com.wis.pojo.vo.WarningInfo;
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

    // @Select("select * from wis_item where sid=#{sceneId} and wtlx IN (2,3,4) order by id ASC")
    List<Item> findItemBySidAndLx(String sceneId);

    @Select("select * from wis_item where sid=#{sceneId} and uid=#{uid}")
    Item findItemBySidAndUid(String sceneId, String uid);

    @ResultMap("ItemDataResultMap")
    @Select("SELECT * FROM wis_item_data WHERE item_id=#{itemId} AND s_id=#{scadaSid}")
    List<ItemData> findItemDataByItemIdAndScadaSid(Integer itemId, Integer scadaSid);

    @ResultMap("ItemDataResultMap")
    @Select("SELECT * FROM wis_item_data WHERE item_id=#{itemId} AND s_id=#{scadaSid} and ptype='A'")
    List<ItemData> findItemDataByItemIdAndSSidAndType(Integer itemId, Integer scadaSid);

    @Select("select * from wis_item_data where item_id=#{itemId}")
    List<ItemData> findDataByItemId(Integer itemId);

    @Select("SELECT * FROM wis_item WHERE sid=#{sceneId} AND wtlx IN (3,4) ORDER BY id ASC")
    List<Item> findItemBySidAndWarning(String sceneId);

    @Update("update wis_item set wtzt=#{wtzt} where id=#{id}")
    void updateWtzt(Integer wtzt, Integer id);

    @Select("select * from wis_item_data where pname like #{Pname} and s_id=#{scadaSid} and ptype='D'")
    ItemData findItemDataByPnameAndScadaSid(String Pname, Integer scadaSid);

    @Update("update wis_scene set s_name=#{scadaName},s_val=#{stationStatus},uptime=#{updateTime} where id=#{id}")
    void updateSceneDate(String scadaName, String stationStatus, String updateTime, Integer id);

    @Select("select * from wis_item_data where s_id=#{scadaSid}")
    List<ItemData> findDataByScadaSid(Integer scadaSid);

    @Insert("insert into wis_item_data(s_id,pid,pname,ptype,pvalue,pstatus,unit) values(#{scadaSid},#{pid},#{pname},#{ptype},#{pvalue},#{pstatus},#{unit})")
    void addData(ItemData itemData);

    @Update("update wis_item_data set pvalue=#{pvalue} where pid=#{pid} and s_id=#{scadaSid} and ptype=#{ptype}")
    void updateData(Integer scadaSid, String pvalue, Integer pid, String ptype);


    @ResultMap("ItemDataResultMap")
    @Select("select * from wis_item_data where s_id=#{scadaSid} and pid=#{pid} and ptype=#{ptype}")
    ItemData findDataByScadaSidAndPid(Integer scadaSid, Integer pid, String ptype);


    @ResultMap("CheckedDataResultMap")
    @Select("select * from wis_item_checked where check_item_sid = #{sid}")
    List<CheckedDateDTO> findAllCheckDate(Integer sid);

    @ResultMap("AssetsResultMap")
    @Select("select * from wis_item_assets where assets_aid=#{aid} and assets_sid = #{sid}")
    Assets findByAid(Integer aid, Integer sid);

    @ResultMap("CheckedDataResultMap")
    @Select("select * from wis_item_checked where check_item_aid=#{aid} and check_item_sid = #{sid}")
    List<CheckedData> findCheckDataByAid(Integer aid, Integer sid);

    @Select("select * from wis_item where wtlx IN (3,4)")
    List<Item> findAllItem();

    //产生告警的设备
    @Select("select * from wis_item where sid=#{sceneId} and wtlx = 3 and wtzt = 3")
    List<Item> findWarningItem(String sceneId);

    //产生告警的数据
    @Select("select pname,pvalue,unit,pstatus,data_name dataName from wis_item_data where s_id=#{sid} and item_id=#{itemId} and pstatus>0")
    List<ItemData> findWarningItemData(Integer sid, Integer itemId);


    //查询当前场站分组信息
    @ResultMap("GroupResultMap")
    @Select("select * from wis_item_group where scene_id=#{sceneId}")
    List<Group> findAllGroup(String sceneId);

    //查询当前分组包含的物体信息
    @Select("select wi.cname ,wi.id,wi.uid from wis_item_group_relation wigr left join wis_item wi on wigr.item_id = wi.id where wigr.group_id = #{id}")
    List<Item> findGroupById(Integer id);

    //查询当前场站所有告警数据
    @Select("select wi.id,wi.cname title,wid.pname,wid.data_name name,wi.uid,wid.pvalue,wid.unit,wid.pstatus,wi.wtzt,wi.wtlx,wi.qpzt from wis_item_data wid left join wis_item wi on wid.item_id = wi.id where wid.s_id=#{sid} and wid.pstatus>0;")
    List<WarningInfo> findWarningDataBySid(Integer sid);


}
