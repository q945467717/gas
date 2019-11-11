package com.wis.service.impl;

import com.wis.mapper.ItemMapper;
import com.wis.mapper.SceneMapper;
import com.wis.mapper.SysUserMapper;
import com.wis.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private SysUserMapper sysUserMapper;



    @Override
    public void deleteAll(int[] checks, String typeName) {
        if(typeName.equals("item")){
            for(int check:checks){
                itemMapper.deleteItemById(check);
            }
        }
        if(typeName.equals("scene")){
            for(int check:checks){
                sceneMapper.deleteSceneById(check);
            }
        }
        if(typeName.equals("admin")){
            for(int check:checks){
                sysUserMapper.deleteUserById(check);
            }
        }
    }
}
