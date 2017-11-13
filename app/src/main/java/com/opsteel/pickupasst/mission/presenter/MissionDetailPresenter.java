package com.opsteel.pickupasst.mission.presenter;

import com.opsteel.pickupasst.base.IBasePresenter;
import com.opsteel.pickupasst.mission.model.MissionDetailModel;
import com.opsteel.pickupasst.mission.view.IMissionDetailView;

/**
 * 文 件 名: MissionDetailPresenter
 * 创 建 人: Cartman
 * 创建日期: 2017/11/1 09:05
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class MissionDetailPresenter implements IBasePresenter {
    private MissionDetailModel missionDetailModel;
    private IMissionDetailView missionDetailView;

    public MissionDetailPresenter(IMissionDetailView missionDetailView) {
        this.missionDetailModel = new MissionDetailModel();
        this.missionDetailView = missionDetailView;
    }

    public void getDetail(){
        missionDetailView.getDetail(missionDetailModel.getDetail());
    }
}
