package com.opsteel.pickupasst;
/**
 * 文 件 名: App
 * 创 建 人: Cartman
 * 创建日期: 2017/10/27 11:18
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：自定义Application类
 */

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;



public class App extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LeakCanary.install(this);
    }


    public static Context getContext(){
        return context;
    }
}
