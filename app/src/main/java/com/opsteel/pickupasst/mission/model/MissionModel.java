package com.opsteel.pickupasst.mission.model;

import com.opsteel.pickupasst.mission.model.bean.Green;
import com.opsteel.pickupasst.mission.model.bean.StoreGreen;
import com.opsteel.pickupasst.mission.model.bean.WcGreenList;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: MissionModel
 * 创 建 人: Cartman
 * 创建日期: 2017/11/1 08:37
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class MissionModel implements IMissionModel{


    @Override
    public List<Object> getMissionList() {
        List<Object> dataList = new ArrayList<>();
        for (WcGreenList wcGreenList : getWcGreenLists()) {
            dataList.add(wcGreenList);
            for (StoreGreen storeGreen : wcGreenList.getStoreGreenList()) {
                dataList.add(storeGreen);

                for (int i = 0; i < storeGreen.getGreenList().size(); i++) {
                    Green green = storeGreen.getGreenList().get(i);
                    if (i == storeGreen.getGreenList().size() - 1) {
                        green.setLastone(true);
                    }
                    dataList.add(green);
                }
            }
        }
        return dataList;
    }

    private List<WcGreenList> getWcGreenLists() {
        List<WcGreenList> wcGreenLists = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            WcGreenList wcGreenList = new WcGreenList();
            wcGreenList.setTelephone("15521313471");
            wcGreenList.setAddress("乐从");
            wcGreenList.setWcshowname("乐从欧浦仓");
            List<StoreGreen> greenList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                StoreGreen storeGreen = new StoreGreen();
                storeGreen.setWeightsum(100.0);
                storeGreen.setBillnum(1);
                storeGreen.setStorename("顺德区XXX贸易有限公司");
                List<Green> greens = new ArrayList<>();
                for (int k = 0; k < 3; k++) {
                    Green green = new Green();
                    green.setBldate("1990-02-20");
                    green.setBlid("1223");
                    green.setNum(1);
                    green.setGreenstatus(1);
                    green.setCufullname("fuck");
                    greens.add(green);
                }
                storeGreen.setGreenList(greens);

                greenList.add(storeGreen);
            }
            wcGreenList.setStoreGreenList(greenList);
            wcGreenLists.add(wcGreenList);
        }
        return wcGreenLists;
    }
}
