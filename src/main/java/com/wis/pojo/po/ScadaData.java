package com.wis.pojo.po;

import lombok.Data;

import java.util.List;

@Data
public class ScadaData {

        private List<Pvalues> pvalues;
        private int sid;
        private String statName;
        private List<String> stationParameterDTOs;
        private String status;
        private String  timeCreated;

}
