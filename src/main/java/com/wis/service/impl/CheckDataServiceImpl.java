package com.wis.service.impl;

import com.wis.dto.CheckedDataFilterDTO;
import com.wis.mapper.AssetsMapper;
import com.wis.mapper.CheckMapper;
import com.wis.mapper.SceneMapper;
import com.wis.pojo.po.Assets;
import com.wis.pojo.po.CheckedData;
import com.wis.pojo.po.Scene;
import com.wis.pojo.vo.CheckInfo;
import com.wis.pojo.vo.PageHelper;
import com.wis.service.CheckDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckDataServiceImpl implements CheckDataService {

    @Autowired
    private CheckMapper checkMapper;
    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private AssetsMapper assetsMapper;


    @Override
    public List<CheckInfo> getCheckedDateList() {

        List<CheckedData> dataList = checkMapper.findAll();

        List<CheckInfo> checkInfoList = new ArrayList<>();


        if(dataList.size()>0){
            for(CheckedData checkedData:dataList){

                Scene scene = sceneMapper.findBySid(checkedData.getSid());

                Assets assets = assetsMapper.findByAid(checkedData.getItemAid());

                CheckInfo checkInfo = new CheckInfo();
                checkInfo.setCheckMember(checkedData.getCheckMember());
                checkInfo.setCheckLog(checkedData.getCheckLog());
                checkInfo.setCheckTime(checkedData.getCheckTime());
                checkInfo.setId(checkedData.getId());

                if(assets!=null){
                    checkInfo.setAssetsName(assets.getAssetsName());

                }

                if(checkedData.getStatus()==1){
                    checkInfo.setStatus("正常");
                }else {
                    checkInfo.setStatus("异常");
                }
                if(scene!=null){
                    checkInfo.setSceneName(scene.getScadaName());
                }

                checkInfoList.add(checkInfo);
            }
        }



        return checkInfoList;
    }

    @Override
    public PageHelper<CheckInfo> getCheckedDateList(CheckedDataFilterDTO checkedDataFilterDTO) {


        List<CheckedData> filterList = checkMapper.findFilterList(checkedDataFilterDTO);

        List<CheckInfo> checkInfoList = new ArrayList<>();


        if(filterList.size()>0){
            for(CheckedData checkedData:filterList){

                Scene scene = sceneMapper.findBySid(checkedData.getSid());

                Assets assets = assetsMapper.findByAid(checkedData.getItemAid());

                CheckInfo checkInfo = new CheckInfo();
                checkInfo.setCheckMember(checkedData.getCheckMember());
                checkInfo.setCheckLog(checkedData.getCheckLog());
                checkInfo.setCheckTime(checkedData.getCheckTime());
                checkInfo.setId(checkedData.getId());

                if(assets!=null){
                    checkInfo.setAssetsName(assets.getAssetsName());

                }

                if(checkedData.getStatus()==1){
                    checkInfo.setStatus("正常");
                }else {
                    checkInfo.setStatus("异常");
                }
                if(scene!=null){
                    checkInfo.setSceneName(scene.getScadaName());
                }

                checkInfoList.add(checkInfo);
            }
        }

        Integer total = checkMapper.getDataTotal(checkedDataFilterDTO);

        PageHelper<CheckInfo> pageHelper = new PageHelper<>();

        pageHelper.setTotal(total);
        pageHelper.setRows(checkInfoList);

        return pageHelper;
    }

    @Override
    public CheckInfo getCheckedDate(Integer id) {

        CheckedData checkedData = checkMapper.findById(id);

        return new CheckInfo(){{
            setCheckLog(checkedData.getCheckLog());
        }};

    }
}
