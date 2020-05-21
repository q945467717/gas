package com.wis.mapper;

import com.wis.pojo.po.ItemData;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataSourceMapper {


    List<ItemData> findAllData(Integer sid, Integer pid, Integer limit, Integer offset);

    Integer getTotal(Integer sid, Integer pid);

    @Update("update wis_item_data set item_id=#{itemId},data_name=#{dataName} where id=#{id}")
    void update(Integer id,String dataName,Integer itemId);

    @ResultMap("DataSourceResultMap")
    @Select("select * from wis_item_data where id=#{id}")
    ItemData findById(Integer id);

}
