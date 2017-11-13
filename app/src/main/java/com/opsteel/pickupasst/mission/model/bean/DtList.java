package com.opsteel.pickupasst.mission.model.bean;

/**
 * 文 件 名: DtList
 * 创 建 人: Cartman
 * 创建日期: 2017/10/27 11:18
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */

public class DtList {
    //货物编号
    private String handCode;
    //钢卷号
    private String steelcode;
    //钢厂
    private String parea;
    //材质
    private String grade;
    //规格
    private String specname;
    //材质
    private String typename;
    //重量
    private double greenweight;
    //数量
    private int greennum;

    public String getHandCode() {
        return handCode;
    }

    public void setHandCode(String handCode) {
        this.handCode = handCode;
    }

    public String getSteelcode() {
        return steelcode;
    }

    public void setSteelcode(String steelcode) {
        this.steelcode = steelcode;
    }

    public String getParea() {
        return parea;
    }

    public void setParea(String parea) {
        this.parea = parea;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSpecname() {
        return specname;
    }

    public void setSpecname(String specname) {
        this.specname = specname;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public double getGreenweight() {
        return greenweight;
    }

    public void setGreenweight(double greenweight) {
        this.greenweight = greenweight;
    }

    public int getGreennum() {
        return greennum;
    }

    public void setGreennum(int greennum) {
        this.greennum = greennum;
    }
}
