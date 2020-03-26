package com.wis.pojo.po;

import java.util.Date;
import java.util.List;

public class ScadaData {

        private List<Pvalues> pvalues;
        private int sid;
        private String statName;
        private List<String> stationParameterDTOs;
        private String status;
        private String  timeCreated;
        public void setPvalues(List<Pvalues> pvalues) {
            this.pvalues = pvalues;
        }
        public List<Pvalues> getPvalues() {
            return pvalues;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }
        public int getSid() {
            return sid;
        }

        public void setStatName(String statName) {
            this.statName = statName;
        }
        public String getStatName() {
            return statName;
        }

        public void setStationParameterDTOs(List<String> stationParameterDTOs) {
            this.stationParameterDTOs = stationParameterDTOs;
        }
        public List<String> getStationParameterDTOs() {
            return stationParameterDTOs;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }

        public void setTimeCreated(String  timeCreated) {
            this.timeCreated = timeCreated;
        }
        public String getTimeCreated() {
            return timeCreated;
        }
}
