package com.opsteel.pickupasst.mission.model.bean;

import java.util.List;

/**
 * 文 件 名: StoreGreen
 * 创 建 人: Cartman
 * 创建日期: 2017/10/27 11:18
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */

public class StoreGreen {
    //仓库名称
    private String storename;
    //单数
    private int billnum;
    //总重量
    private double weightsum;
    //总数量
    private List<Green> greenList;

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public int getBillnum() {
        return billnum;
    }

    public void setBillnum(int billnum) {
        this.billnum = billnum;
    }

    public double getWeightsum() {
        return weightsum;
    }

    public void setWeightsum(double weightsum) {
        this.weightsum = weightsum;
    }

    public List<Green> getGreenList() {
        return greenList;
    }

    public void setGreenList(List<Green> greenList) {
        this.greenList = greenList;
    }
}
