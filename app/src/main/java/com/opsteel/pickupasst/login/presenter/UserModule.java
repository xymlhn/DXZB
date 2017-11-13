package com.opsteel.pickupasst.login.presenter;

import com.opsteel.pickupasst.login.view.ILoginView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 文 件 名: UserModule
 * 创 建 人: Cartman
 * 创建日期: 2017/11/9 16:18
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：依赖提供方，负责提供依赖中所需要的对象，实际编码中类似工厂类
 *
 * Dagger2的主要工作流程分为以下几步:
 * 1.将依赖需求方实例传入给Component实现类
 * 2.Component实现类根据依赖需求方实例中依赖声明,来确定该实例需要依赖哪些对象
 * 3.确定依赖对象后,Component会在与自己关联的Module类中查找有没有提供这些依赖对象的方法,
  有的话就将Module类中提供的对象设置到依赖需求方实例中
 */


//声明@Module表明该类是Module类，供Dagger2识别
@Module
public class UserModule {
    private final ILoginView loginView;

    public UserModule(ILoginView loginView) {
        this.loginView = loginView;
    }

    //声明Module类哪些方法是用来提供依赖对象的。
    // 当Component类需要依赖对象时,他就会根据返回值的类型来在有@Provides注解的方法中选择调用哪个方法.
    // 在一个方法上声明@Provides注解,就相当于创建了一条生产线,
    // 这条生产线的产物就是方法的返回值类型.有了这条生产线,供应商就能提供这种类型的商品了
    @Provides
    ILoginView provideLoginView(){
        return loginView;
    }
}
