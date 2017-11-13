package com.opsteel.pickupasst.base;

/**
 * 文 件 名: IBaseView
 * 创 建 人: Cartman
 * 创建日期: 2017/10/30 09:17
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */

public interface IBaseView {
    void showLoading(int resId);

    void showLoading();

    void hideLoading();

    void onUnknownError(String error);

    void onTimeout();

    void onNetworkError();


}
