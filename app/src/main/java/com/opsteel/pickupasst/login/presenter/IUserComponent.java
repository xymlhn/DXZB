package com.opsteel.pickupasst.login.presenter;

import com.opsteel.pickupasst.login.view.LoginActivity;

import dagger.Component;

/**
 * 文 件 名: IUserComponent
 * 创 建 人: Cartman
 * 创建日期: 2017/11/9 16:17
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：负责将对象注入到依赖需求方，编译时Dagger2会自动为它生成一个实现类
 */

@Component(modules = UserModule.class)
public interface IUserComponent {
    void inject(LoginActivity activity);
}
