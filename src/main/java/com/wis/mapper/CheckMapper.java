package com.wis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wis.dto.CheckedDataFilterDTO;
import com.wis.pojo.po.CheckedData;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public interface CheckMapper extends BaseMapper<CheckedData> {

    List<CheckedData> findAll();

    @ResultMap("checkedResultMap")
    @Select("select * from wis_item_checked where check_item_aid=#{aid} and check_item_sid = #{sid} limit 4")
//    @Select("select * from wis_item_checked where check_item_aid=#{aid} and check_item_sid = #{sid} group by check_time desc limit 4")
    LinkedList<CheckedData> findShowCheckedData(Integer aid, Integer sid);

    @ResultMap("checkedResultMap")
    @Select("select * from wis_item_checked where id=#{id}")
    CheckedData findById(Integer id);


    List<CheckedData> findFilterList(CheckedDataFilterDTO checkedDataFilterDTO);
    Integer getDataTotal(CheckedDataFilterDTO checkedDataFilterDTO);

}
