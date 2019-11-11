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

    @Insert("insert into wis_scene(sceneid,cname,addtime,scada_sid,uptime) values(#{sceneId},#{sceneName},#{addTime},#{scadaSid},#{upTime})")
    void addScene(Scene scene);

    @Update("update wis_scene set sceneid=#{sceneId},cname=#{sceneName},scada_sid=#{scadaSid} where id=#{id}")
    void updateSceneById(Integer id,String sceneId,String sceneName,Integer scadaSid);

}
