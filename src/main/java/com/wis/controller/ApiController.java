package com.wis.controller;

import com.wis.annotation.ApiResponse;
import com.wis.dto.CheckedDateDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@ApiResponse
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/checkedDate")
    public void checkedDate(@RequestBody @Validated CheckedDateDTO checkedDateDTO) {

    }
}
