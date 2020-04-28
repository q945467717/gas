package com.wis.mapper;

import com.wis.pojo.po.Assets;
import com.wis.pojo.po.Item;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetsMapper {

    List<Assets> findAllAssets(Integer assetsSid,Integer aid,String assetsName,Integer limit,Integer offset);

    @ResultMap("AssetsResultMap")
    @Select("select * from wis_item_assets where id=#{id}")
    Assets findById(Integer id);

    Integer getTotal(Assets assets);

    @ResultMap("AssetsResultMap")
    @Select("select * from wis_item_assets where assets_aid = #{aid}")
    Assets findByAid(Integer aid);

}
