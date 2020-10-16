package com.wis.service;

import com.wis.dto.CheckedDataFilterDTO;
import com.wis.dto.CheckedDateDTO;
import com.wis.pojo.vo.CheckInfo;
import com.wis.pojo.vo.PageHelper;

import java.util.List;

public interface CheckDataService {

    //获取所有巡检数据
    List<CheckInfo> getCheckedDateList();

    PageHelper<CheckInfo> getCheckedDateList(CheckedDataFilterDTO checkedDataFilterDTO);

    CheckInfo getCheckedDate(Integer id);

    void add(CheckedDateDTO checkedDateDTO);


}
