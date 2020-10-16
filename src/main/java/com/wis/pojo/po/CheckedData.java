package com.wis.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("wis_item_checked")
public class CheckedData {


    private Integer id;
    private String checkMember;
    private String checkLog;
    private LocalDateTime checkTime;
    @TableField("check_item_aid")
    private Integer itemAid;
    @TableField("item_status")
    private Integer status;
    @TableField("check_item_sid")
    private Integer sid;
}
