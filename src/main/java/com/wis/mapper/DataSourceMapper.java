package com.wis.mapper;

import com.wis.pojo.po.ItemData;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataSourceMapper {


    List<ItemData> findAllData(Integer sid, Integer pid, Integer limit, Integer offset);

    Integer getTotal(Integer sid, Integer pid);

    @Update("update wis_item_data set data_name=#{dataName} where id=#{id}")
    void update(Integer id,String dataName);

}
