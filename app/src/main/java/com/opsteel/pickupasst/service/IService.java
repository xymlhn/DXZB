package com.opsteel.pickupasst.service;


import com.opsteel.pickupasst.login.model.bean.Result;
import com.opsteel.pickupasst.login.model.bean.Token;
import com.opsteel.pickupasst.login.model.bean.User;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 文 件 名: IService
 * 创 建 人: Cartman
 * 创建日期: 2017/10/27 14:52
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public interface IService {
    @POST("user/login")
    @FormUrlEncoded
    Observable<Result<Token>> login(@FieldMap Map<String,Object> map);

    @GET("user/getUserInfo")
    Observable<Result<User>> getUser();

    @Multipart
    @POST("file/uploadFile")
    Observable<Result> upload(@Part("uploadFile") RequestBody description,
                                    @Part MultipartBody.Part file);
}
