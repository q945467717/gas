package com.wis.pojo.po;

import com.wis.webservice.Timestamp;
import lombok.Data;

@Data
public class Pvalues {

    private String isSound;
    private long pid;
    private String pname;
    private Timestamp ptime;
    private String ptype;
    private String pvalue;
    private String status;
    private String unit;
    private String warning;

}
