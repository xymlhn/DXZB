package com.opsteel.pickupasst.mission.view;

import com.opsteel.pickupasst.base.IBaseView;

import java.util.List;

/**
 * 文 件 名: IMissionListView
 * 创 建 人: Cartman
 * 创建日期: 2017/11/1 08:48
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public interface IMissionListView extends IBaseView {
    void getMissionList(List<Object> list);
}
