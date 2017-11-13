package com.opsteel.pickupasst.mission.presenter;

import com.opsteel.pickupasst.base.IBasePresenter;
import com.opsteel.pickupasst.mission.model.IMissionModel;
import com.opsteel.pickupasst.mission.model.MissionModel;
import com.opsteel.pickupasst.mission.view.IMissionListView;

/**
 * 文 件 名: MissionListPresenter
 * 创 建 人: Cartman
 * 创建日期: 2017/11/1 08:47
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class MissionListPresenter implements IBasePresenter {
    private IMissionListView iMissionView;
    private IMissionModel missionModel;
    public MissionListPresenter(IMissionListView iMissionView) {
        this.iMissionView = iMissionView;
        missionModel = new MissionModel();
    }

    public void getMissionList(){
        iMissionView.getMissionList(missionModel.getMissionList());
    }
}
