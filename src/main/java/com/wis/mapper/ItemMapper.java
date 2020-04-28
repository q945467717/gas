package com.wis.mapper;

import com.wis.pojo.po.Item;
import com.wis.pojo.po.ItemData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMapper {

    @Select("select * from wis_item where 1=1")
    List<Item> findAllItem();

    @Delete("delete from wis_item where id=#{id}")
    void deleteItemById(Integer id);

    Integer getTotal(Item item);

    List<Item> getItemListPage(Item item);

    @Insert("insert into wis_item(sid,uid,wtlx,addtime,cname,content) values(#{sid},#{uid},#{wtlx},#{addtime},#{cname},#{content})")
    void addItem(Item item);

    @Select("select * from wis_item where id=#{id}")
    Item findItemById(Integer id);

    @Update("update wis_item set sid=#{sid},uid=#{uid},wtlx=#{wtlx},cname=#{cname},content=#{content} where id=#{id}")
    void updateItemById(Integer id,String sid,String uid,int wtlx,String cname,String content);

    @Select("select * from wis_item_data where item_id=#{itemId}")
    List<ItemData> findDataByItemId(Integer itemId);

    @Select("select * from wis_item_data where s_id=#{scadaId}")
    List<ItemData> findDataByScadaId(Integer scadaId);

    @Update("update wis_item_data set item_id=#{itemId} where s_id=#{scadaId} and pid=#{pid} and ptype='A")
    void updateItemData(Integer scadaId,Integer itemId,Integer pid);

    @Select("select uid from wis_item where sid=#{sid} and aid=#{aid}")
    Item findByAidAndSid(String sid,Integer aid);

    @Select("select uid,id,cname from wis_item where sid=#{sid} and wtlx=3")
    List<Item> findUidByLxAndSceneId(String sid);

    @Select("select * from wis_item where sid=#{sceneId} and wtlx in (2,3,4)")
    List<Item> findBySceneId(String sceneId);

    @Update("update wis_item set aid=#{aid} where sid=#{sceneId} and uid=#{uid}")
    void updateAid(Integer aid,String sceneId,String uid);



}
