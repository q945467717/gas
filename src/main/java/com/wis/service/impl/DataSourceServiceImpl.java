package com.wis.service.impl;

import com.wis.mapper.DataSourceMapper;
import com.wis.mapper.SceneMapper;
import com.wis.pojo.po.ItemData;
import com.wis.pojo.po.Scene;
import com.wis.pojo.vo.DataSourceVo;
import com.wis.pojo.vo.PageHelper;
import com.wis.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataSourceMapper dataSourceMapper;
    @Autowired
    private SceneMapper sceneMapper;

    @Override
    public PageHelper<DataSourceVo> dataSourceList(Integer scadaSid, Integer pid, Integer limit, Integer offset) {

        List<ItemData> itemDataList = dataSourceMapper.findAllData(scadaSid,pid,limit,offset);

        List<DataSourceVo> dataSourceVoList = new ArrayList<>();

        for(ItemData itemData:itemDataList){

            Scene scene = sceneMapper.findBySid(itemData.getScadaSid());

            DataSourceVo dataSourceVo = new DataSourceVo(){{
                setDataName(itemData.getDataName());
                setPid(itemData.getPid());
                setPname(itemData.getPname());
                setId(itemData.getId());
                setStationName(scene.getSceneName());

            }};
            dataSourceVoList.add(dataSourceVo);
        }

        //获取分页总数
        Integer total = dataSourceMapper.getTotal(scadaSid, pid);
        //封装结果
        PageHelper<DataSourceVo> pageHelper = new PageHelper<>();
        pageHelper.setRows(dataSourceVoList);
        pageHelper.setTotal(total);

        return pageHelper;
    }

    @Override
    public void updateSource(Integer id, String dataName) {

        dataSourceMapper.update(id,dataName);
    }
}
