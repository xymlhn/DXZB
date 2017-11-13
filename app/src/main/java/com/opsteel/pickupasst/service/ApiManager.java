package com.opsteel.pickupasst.service;

import com.google.gson.Gson;
import com.opsteel.pickupasst.App;
import com.opsteel.pickupasst.BuildConfig;
import com.opsteel.pickupasst.login.model.bean.Token;
import com.opsteel.pickupasst.utils.SharedPrefUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 文 件 名: ApiManager
 * 创 建 人: Cartman
 * 创建日期: 2017/10/27 16:12
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：API管理类
 */


public class ApiManager {
    private IService mUserApi;

    private static ApiManager sApiManager;
    public static ApiManager getInstence() {
        if (sApiManager == null) {
            synchronized (ApiManager.class) {
                if (sApiManager == null) {
                    sApiManager = new ApiManager();
                }
            }
        }
        return sApiManager;
    }

    /**
     * 封装配置API
     * @return
     */
    public IService getService(){

            Token token = SharedPrefUtils.getToken();
            final String bearer = token == null ? "" : token.getAccessToken();
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Authorization", "bearer " + bearer)
                            .header("Accept", "application/json")
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            }).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BuildConfig.SERVER_URL)
                    .addConverterFactory(CGsonConverterFactory.create())
                    .build();

            mUserApi = retrofit.create(IService.class);

        return mUserApi;
    }


}
