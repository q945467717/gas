package com.wis.controller;

import com.wis.annotation.ApiResponse;
import com.wis.dto.AssetsDTO;
import com.wis.dto.CheckedDateDTO;
import com.wis.pojo.vo.ApiResult;
import com.wis.service.AssetsService;
import com.wis.service.CheckDataService;
import com.wis.utils.ResponseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@ApiResponse
@RequestMapping("/api")
@Api(value = "数据推送接口")
public class ApiController {

    @Autowired
    private CheckDataService checkDataService;
    @Autowired
    private AssetsService assetsService;

    @ApiOperation("巡检数据推送")
    @PostMapping("/checkedDate")
    public ApiResult checkedDate(@RequestBody @Validated CheckedDateDTO checkedDateDTO) {

        checkDataService.add(checkedDateDTO);
        //return ResultUtil.success(10000,"推送巡检数据成功");
        return new ApiResult(ResponseCode.SUCCESS,"推送巡检数据成功");

    }

    @ApiOperation("资产数据推送")
    @PostMapping("/assets")
    public ApiResult assets(@RequestBody @Validated AssetsDTO assetsDTO) {

        if(assetsService.existAssets(assetsDTO)){
            assetsService.add(assetsDTO);
        }else {
            return new ApiResult(ResponseCode.VALIDATED_ERROR,"资产设备已存在");
        }
        return new ApiResult(ResponseCode.SUCCESS,"推送资产数据成功");

    }
}
