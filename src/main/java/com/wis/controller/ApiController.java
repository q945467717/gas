package com.wis.controller;

import com.wis.annotation.ApiResponse;
import com.wis.dto.CheckedDateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@ApiResponse
@RequestMapping("/api")
@Api(value = "数据推送接口")
public class ApiController {

    @ApiOperation("巡检数据推送")
    @PostMapping("/checkedDate")
    public void checkedDate(@RequestBody @Validated CheckedDateDTO checkedDateDTO) {

    }

    @ApiOperation("资产数据推送")
    @PostMapping("/assets")
    public void assets(@RequestBody @Validated CheckedDateDTO checkedDateDTO) {

    }
}
