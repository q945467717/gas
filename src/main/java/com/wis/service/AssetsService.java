package com.wis.service;

import com.wis.pojo.po.Assets;
import com.wis.pojo.vo.AssetsInfo;
import com.wis.pojo.vo.PageHelper;
import io.swagger.models.auth.In;

import java.util.List;

public interface AssetsService {

    PageHelper<AssetsInfo> assetsList(String sceneId, Integer aid, String assetsName, Integer limit, Integer offset);

    //根据ID查资产信息
    AssetsInfo assets(Integer id);

    //修改资产信息所属uid
    void updateAssets(Integer id,String uid) throws Exception;
}
