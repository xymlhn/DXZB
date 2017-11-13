package com.opsteel.pickupasst.mission.model;

import com.opsteel.pickupasst.mission.model.bean.DtList;
import com.opsteel.pickupasst.mission.model.bean.Green;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: MissionDetailModel
 * 创 建 人: Cartman
 * 创建日期: 2017/11/1 09:03
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class MissionDetailModel implements IMissionDetailModel {
    @Override
    public Green getDetail() {
        Green green = new Green();
        green.setNum(1);
        green.setCufullname("12313450000");
        green.setBlid("101");
        green.setBldate("2010-10-3");
        green.setWeight(10.23);

        List<DtList> dtLists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DtList dtList = new DtList();
            dtList.setGrade("材质");
            dtList.setGreennum(1);
            dtList.setGreenweight(10.7);
            dtList.setHandCode("货物编号");
            dtList.setSpecname("规格");
            dtList.setParea("钢厂");
            dtList.setTypename("材质");
            dtLists.add(dtList);
        }
        green.setDtList(dtLists);
        return green;
    }
}
