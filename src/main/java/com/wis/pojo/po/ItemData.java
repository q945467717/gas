package com.wis.pojo.po;

public class ItemData {

    private int id;
    private int scadaSid;
    private int itemId;
    private int pid;
    private String pname;
    private String ptype;
    private String pvalue;
    private int pstatus;
    private String unit;
    private String warning;

    public void setPvalue(String pvalue) {
        this.pvalue = pvalue;
    }

    public void setScadaSid(int scadaSid) {
        this.scadaSid = scadaSid;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public void setPstatus(int pstatus) {
        this.pstatus = pstatus;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getPvalue() {
        return pvalue;
    }
    public int getItemId() {
        return itemId;
    }

    public int getId() {
        return id;
    }

    public int getScadaSid() {
        return scadaSid;
    }

    public int getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public String getPtype() {
        return ptype;
    }

    public int getPstatus() {
        return pstatus;
    }

    public String getUnit() {
        return unit;
    }

    public String getWarning() {
        return warning;
    }
}
