package com.wis.service.impl;

import com.wis.exception.SceneNotFindException;
import com.wis.mapper.DataSourceMapper;
import com.wis.mapper.ItemMapper;
import com.wis.mapper.SceneMapper;
import com.wis.pojo.po.Item;
import com.wis.pojo.po.ItemData;
import com.wis.pojo.po.Scene;
import com.wis.pojo.vo.DataSourceVo;
import com.wis.pojo.vo.PageHelper;
import com.wis.service.DataSourceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataSourceMapper dataSourceMapper;
    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public PageHelper<DataSourceVo> dataSourceList(Integer scadaSid, Integer pid, Integer limit, Integer offset) {

        //查询当前分页数据
        List<ItemData> itemDataList = dataSourceMapper.findAllData(scadaSid,pid,limit,offset);

        List<DataSourceVo> dataSourceVoList = new ArrayList<>();

        DataSourceVo dataSourceVo;
        for(ItemData itemData:itemDataList){

            Scene scene = sceneMapper.findBySid(itemData.getScadaSid());
            Item item = itemMapper.findItemById(itemData.getItemId());
            dataSourceVo = new DataSourceVo(){{
                setDataName(itemData.getDataName());
                setPid(itemData.getPid());
                setPname(itemData.getPname());
                setId(itemData.getId());
                setStationName(scene.getSceneName());
                setUpdateTime(itemData.getUpdateTime());
            }};
            if(StringUtils.isEmpty(item)){
                dataSourceVo.setUid("未设置");
                dataSourceVo.setItemName("未设置");
            }else {
                dataSourceVo.setUid(item.getUid());
                dataSourceVo.setItemName(item.getCname());
            }
            dataSourceVoList.add(dataSourceVo);
        }

        //获取分页总数
        Integer total = dataSourceMapper.getTotal(scadaSid, pid);
        //封装结果
        //System.out.println(dataSourceVoList.size()+","+total);

        PageHelper<DataSourceVo> pageHelper = new PageHelper<>();
        pageHelper.setRows(dataSourceVoList);
        pageHelper.setTotal(total);

        return pageHelper;
    }

    @Override
    public void updateSource(Integer id, String dataName,String uid) {

        ItemData itemData = dataSourceMapper.findById(id);

        Scene scene = sceneMapper.findBySid(itemData.getScadaSid());
        if(StringUtils.isEmpty(scene)){
            throw new SceneNotFindException();
        }
        Item item = itemMapper.findByUidAndSid(uid,scene.getSceneId());

        dataSourceMapper.update(id,dataName,item.getId());
    }
}
