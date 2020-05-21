package com.wis.service.impl;

import com.wis.mapper.ItemMapper;
import com.wis.mapper.SceneMapper;
import com.wis.mapper.SysUserMapper;
import com.wis.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        if("item".equals(typeName)){
            for(int check:checks){
                itemMapper.deleteItemById(check);
            }
        }
        if("scene".equals(typeName)){
            for(int check:checks){
                sceneMapper.deleteSceneById(check);
            }
        }
        if("admin".equals(typeName)){
            for(int check:checks){
                sysUserMapper.deleteUserById(check);
            }
        }
        if("group".equals(typeName)){
            for(int check:checks){
                sysUserMapper.deleteGroupById(check);
            }
        }
        if(typeName.startsWith("groupItem")){
            List<String> list = Arrays.asList(typeName.split("_"));

            for(int check:checks){
                sysUserMapper.deleteGroupItemById(check,Integer.parseInt(list.get(1)));
            }

        }

    }
}
