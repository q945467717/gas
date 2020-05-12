package com.wis.service;

import com.wis.pojo.vo.DataSourceVo;
import com.wis.pojo.vo.PageHelper;

public interface DataSourceService {


    //获取所有数据源信息
    PageHelper<DataSourceVo> dataSourceList(Integer sid, Integer pid, Integer limit, Integer offset);

    void updateSource(Integer id,String dataName);
}
