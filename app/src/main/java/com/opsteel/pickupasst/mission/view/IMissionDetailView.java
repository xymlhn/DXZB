package com.opsteel.pickupasst.mission.view;

import com.opsteel.pickupasst.base.IBaseView;
import com.opsteel.pickupasst.mission.model.bean.Green;

/**
 * 文 件 名: IMissionDetailView
 * 创 建 人: Cartman
 * 创建日期: 2017/11/1 09:00
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public interface IMissionDetailView extends IBaseView {
    void getDetail(Green green);
}
