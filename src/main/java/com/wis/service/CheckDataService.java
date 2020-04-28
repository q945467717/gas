package com.wis.service;

import com.wis.pojo.vo.CheckInfo;

import java.util.List;

public interface CheckDataService {

    //获取所有巡检数据
    List<CheckInfo> getCheckedDateList();
}
