package com.wis.mapper;

import com.wis.pojo.po.CheckedData;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckMapper {

    List<CheckedData> findAll();
}
