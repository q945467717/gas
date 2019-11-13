package com.wis.pojo.po;

import com.wis.pojo.vo.Page;

import java.io.Serializable;
import java.util.Date;

public class Item extends Page implements Serializable {

    private int id;
    private String sid;
    private String uid;
    private String lcmc;
    private int wtlx;
    private Date addtime;
    private String cname;
    private String content;
    private String bz;
    private int tblx;
    private int wtzt;
    private int qpzt;

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setLcmc(String lcmc) {
        this.lcmc = lcmc;
    }

    public void setWtlx(int wtlx) {
        this.wtlx = wtlx;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setTblx(int tblx) {
        this.tblx = tblx;
    }

    public void setWtzt(int wtzt) {
        this.wtzt = wtzt;
    }

    public void setQpzt(int qpzt) {
        this.qpzt = qpzt;
    }

    public int getId() {
        return id;
    }

    public String getSid() {
        return sid;
    }

    public String getUid() {
        return uid;
    }

    public String getLcmc() {
        return lcmc;
    }

    public int getWtlx() {
        return wtlx;
    }

    public Date getAddtime() {
        return addtime;
    }

    public String getCname() {
        return cname;
    }

    public String getContent() {
        return content;
    }

    public String getBz() {
        return bz;
    }

    public int getTblx() {
        return tblx;
    }

    public int getWtzt() {
        return wtzt;
    }

    public int getQpzt() {
        return qpzt;
    }
}
