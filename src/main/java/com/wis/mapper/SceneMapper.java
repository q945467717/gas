package com.wis.mapper;

import com.wis.pojo.po.Scene;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SceneMapper {


    @ResultMap("SceneResultMap")
    @Select("select * from wis_scene")
    List<Scene> findAllScene();

    @ResultMap("SceneResultMap")
    @Select("select * from wis_scene where sceneid=#{sceneId}")
    Scene findSceneById(String sceneId);

    @ResultMap("SceneResultMap")
    @Select("select * from wis_scene where cname=#{sceneName}")
    Scene findSceneByName(String sceneName);

    @Delete("delete from wis_scene where id=#{id}")
    void deleteSceneById(Integer id);

    @Insert("insert into wis_scene(momoda_id,cname,addtime,scada_sid,uptime) values(#{momodaId},#{sceneName},#{addTime},#{scadaSid},#{upTime})")
    void addScene(Scene scene);

    @Update("update wis_scene set momoda_id=#{momodaId},cname=#{sceneName},scada_sid=#{scadaSid} where id=#{id}")
    void updateSceneById(Integer id,String momodaId,String sceneName,Integer scadaSid);

    @ResultMap("SceneResultMap")
    @Select("select sceneid,s_name from wis_scene where scada_sid=#{sid}")
    Scene findBySid(Integer sid);

    //匹配sid和momodaid
    @Update("update wis_scene set sceneid=#{sceneId} where momoda_id=#{mid}")
    void matchingId(String sceneId,String mid);

    @Select("select * from wis_scene where momoda_id=#{mid}")
    Scene findByMomodaId(String mid);

}
