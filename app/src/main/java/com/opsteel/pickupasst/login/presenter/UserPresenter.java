package com.opsteel.pickupasst.login.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.opsteel.pickupasst.App;
import com.opsteel.pickupasst.base.IBasePresenter;
import com.opsteel.pickupasst.login.model.IUserModel;
import com.opsteel.pickupasst.login.model.UserModel;
import com.opsteel.pickupasst.login.model.bean.Note;
import com.opsteel.pickupasst.login.model.bean.Result;
import com.opsteel.pickupasst.login.model.bean.Token;
import com.opsteel.pickupasst.login.model.bean.User;
import com.opsteel.pickupasst.login.view.ILoginView;
import com.opsteel.pickupasst.login.view.LoginActivity;
import com.opsteel.pickupasst.mission.view.MissionListActivity;
import com.opsteel.pickupasst.service.CallbackWrapper;
import com.opsteel.pickupasst.utils.SharedPrefUtils;

import java.io.File;
import java.util.Map;

import javax.inject.Inject;

/**
 * 文 件 名: UserPresenter
 * 创 建 人: Cartman
 * 创建日期: 2017/10/31 13:41
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class UserPresenter implements IBasePresenter {
    private ILoginView iLoginView;
    private IUserModel userModel;
    @Inject
    public UserPresenter(ILoginView iLoginView) {
        userModel = new UserModel();
        this.iLoginView = iLoginView;
    }

    public void login(Map<String,Object> params){
        iLoginView.showLoading();
        userModel.login(params, new CallbackWrapper<Result<User>>(iLoginView) {
            @Override
            protected void onSuccess(Result<User> tokenResult) {
                iLoginView.onLoginSuccess(tokenResult.getData());
                iLoginView.hideLoading();
            }
        });
    }

    public void uploadFile(File file){
        iLoginView.showLoading();
        userModel.uploadFile(file, new CallbackWrapper<Result>(iLoginView) {
            @Override
            protected void onSuccess(Result result) {
                iLoginView.onUploadSuccess();
                iLoginView.hideLoading();
            }
        });
    }

}
