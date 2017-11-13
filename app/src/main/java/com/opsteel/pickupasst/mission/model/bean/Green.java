package com.opsteel.pickupasst.mission.model.bean;

import java.util.List;

/**
 * 文 件 名: Green
 * 创 建 人: Cartman
 * 创建日期: 2017/10/27 11:18
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */

public class Green {
    //提单id
    private String blid;
    //单据变化
    private String blno;
    //单据日期
    private String bldate;
    //货主
    private String cufullname;
    //重量
    private double weight;
    //数量
    private int num;
    //状态，0未缴费，1可提货，
    private int greenstatus;

    private boolean lastone;

    private List<DtList> dtList;

    public String getBlid() {
        return blid;
    }

    public void setBlid(String blid) {
        this.blid = blid;
    }

    public String getBlno() {
        return blno;
    }

    public void setBlno(String blno) {
        this.blno = blno;
    }

    public String getBldate() {
        return bldate;
    }

    public void setBldate(String bldate) {
        this.bldate = bldate;
    }

    public String getCufullname() {
        return cufullname;
    }

    public void setCufullname(String cufullname) {
        this.cufullname = cufullname;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getGreenstatus() {
        return greenstatus;
    }

    public void setGreenstatus(int greenstatus) {
        this.greenstatus = greenstatus;
    }

    public boolean isLastone() {
        return lastone;
    }

    public void setLastone(boolean lastone) {
        this.lastone = lastone;
    }

    public List<DtList> getDtList() {
        return dtList;
    }

    public void setDtList(List<DtList> dtList) {
        this.dtList = dtList;
    }
}
