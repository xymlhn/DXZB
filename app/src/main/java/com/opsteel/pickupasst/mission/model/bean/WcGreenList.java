package com.opsteel.pickupasst.mission.model.bean;

import java.util.List;

/**
 * 文 件 名: WcGreenList
 * 创 建 人: Cartman
 * 创建日期: 2017/10/27 11:18
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */

public class WcGreenList {
    //网仓名称
    private String wcshowname;
    //地址
    private String address;
    //电话
    private String telephone;

    private List<StoreGreen> storeGreenList;

    public String getWcshowname() {
        return wcshowname;
    }

    public void setWcshowname(String wcshowname) {
        this.wcshowname = wcshowname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<StoreGreen> getStoreGreenList() {
        return storeGreenList;
    }

    public void setStoreGreenList(List<StoreGreen> storeGreenList) {
        this.storeGreenList = storeGreenList;
    }

}
