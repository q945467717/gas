package com.wis.controller;

import com.wis.annotation.ApiResponse;
import com.wis.exception.ApiException;
import com.wis.pojo.po.User;
import com.wis.pojo.vo.ItemInfo;
import com.wis.utils.ResponseCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@ApiResponse
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/checkedDate")
    public ItemInfo getUserById(@PathVariable Integer userId){
        if(userId.equals(0)){
            throw new ApiException(ResponseCode.RESOURCES_NOT_EXIST);
        }
        if(userId.equals(1)){
            throw new RuntimeException();
        }
        ItemInfo itemInfo=new ItemInfo();
        itemInfo.setItemName("zl");
        itemInfo.setItemType("111");
        return itemInfo;
    }
}
