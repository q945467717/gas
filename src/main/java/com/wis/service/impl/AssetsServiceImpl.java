package com.wis.service.impl;

import com.wis.dto.AssetsDTO;
import com.wis.exception.SceneNotFindException;
import com.wis.mapper.AssetsMapper;
import com.wis.mapper.GasApiMapper;
import com.wis.mapper.ItemMapper;
import com.wis.mapper.SceneMapper;
import com.wis.pojo.po.Assets;
import com.wis.pojo.po.Item;
import com.wis.pojo.po.Scene;
import com.wis.pojo.vo.AssetsInfo;
import com.wis.pojo.vo.PageHelper;
import com.wis.service.AssetsService;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssetsServiceImpl implements AssetsService {

    @Autowired
    private AssetsMapper assetsMapper;
    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public PageHelper<AssetsInfo> assetsList(String sceneId,Integer aid,String assetsName,Integer limit,Integer offset) {

        Scene scene1 = sceneMapper.findSceneById(sceneId);

        //查询分页数据
        Integer scadaSid = null;
        if(scene1!=null){
            scadaSid = scene1.getScadaSid();
        }
        List<Assets> assetsList = assetsMapper.findAllAssets(scadaSid,aid,assetsName,limit,offset);

        //查询筛选后的结果总数
        Assets assets1 = new Assets();
        assets1.setAid(aid);
        assets1.setAssetsName(assetsName);
        assets1.setAssetsSid(scadaSid);
        Integer total = assetsMapper.getTotal(assets1);

        //封装筛选过后的数据
        List<AssetsInfo> assetsInfoList = new ArrayList<>();

        AssetsInfo assetsInfo;
        for(Assets assets:assetsList){

            assetsInfo = new AssetsInfo();
            assetsInfo.setAssetsName(assets.getAssetsName());
            assetsInfo.setAssetsManufacturer(assets.getAssetsManufacturer());
            assetsInfo.setAssetsTime(assets.getAssetsTime());
            assetsInfo.setAid(assets.getAid());
            assetsInfo.setId(assets.getId());

            //查询资产设备所属场景
            Scene scene = sceneMapper.findBySid(assets.getAssetsSid());

            if(!StringUtils.isEmpty(scene)){
                assetsInfo.setSceneName(scene.getScadaName());
            }

            //查询该资产是否绑定场景设备
            Item item = itemMapper.findByAidAndSid(scene.getSceneId(), assets.getAid());

            if(item!=null){
                assetsInfo.setUid(item.getUid());
            }else {
                assetsInfo.setUid("未设置");
            }

            assetsInfoList.add(assetsInfo);

        }

        //封装结果并返回
        PageHelper<AssetsInfo> pageHelper = new PageHelper<>();

        pageHelper.setRows(assetsInfoList);
        pageHelper.setTotal(total);

        return pageHelper;
    }

    @Override
    public AssetsInfo assets(Integer id) {

        Assets assets = assetsMapper.findById(id);

        AssetsInfo assetsInfo = new AssetsInfo();

        assetsInfo.setSid(assets.getAssetsSid());
        assetsInfo.setAid(assets.getAid());

        return assetsInfo;
    }

    @Override
    @Transactional
    public void updateAssets(Integer id, String uid){
        Assets assets = assetsMapper.findById(id);
        Scene scene = sceneMapper.findBySid(assets.getAssetsSid());

        if(scene==null){
            throw new SceneNotFindException();
        }
        Item item = itemMapper.findByAidAndSid(scene.getSceneId(), assets.getAid());

        if(!StringUtils.isEmpty(item)){
            itemMapper.updateAid(0,scene.getSceneId(),item.getUid());
        }

        itemMapper.updateAid(assets.getAid(),scene.getSceneId(),uid);

    }

    @Override
    public void add(AssetsDTO assetsDTO) {
        Assets assets = new Assets();
        BeanUtils.copyProperties(assetsDTO,assets);
        assets.setAid(assetsDTO.getAssetsAid());
        assets.setMemberTel(assetsDTO.getAssetsMemberTel());

        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime assetsTime=LocalDateTime.parse(assetsDTO.getAssetsTime(),dtf);
        LocalDateTime assetsMaintenance=LocalDateTime.parse(assetsDTO.getAssetsMaintenance(),dtf);

        assets.setAssetsMaintenance(assetsMaintenance);
        assets.setAssetsTime(assetsTime);

        assetsMapper.insert(assets);

    }

    @Override
    public void deleteAssets(int id) {
        assetsMapper.deleteById(id);
    }
}
