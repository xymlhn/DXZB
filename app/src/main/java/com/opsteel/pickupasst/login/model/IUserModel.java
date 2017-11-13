package com.opsteel.pickupasst.login.model;

import com.opsteel.pickupasst.login.model.bean.Note;
import com.opsteel.pickupasst.login.model.bean.Result;
import com.opsteel.pickupasst.login.model.bean.Token;
import com.opsteel.pickupasst.login.model.bean.User;
import com.opsteel.pickupasst.service.CallbackWrapper;

import java.io.File;
import java.util.Map;

/**
 * 文 件 名: IUserModel
 * 创 建 人: Cartman
 * 创建日期: 2017/10/31 13:28
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public interface IUserModel {
    void login(Map<String,Object> param, CallbackWrapper<Result<User>> callbackWrapper);

    void uploadFile(File file,CallbackWrapper<Result> callbackWrapper);
}
