package com.opsteel.pickupasst.login.model;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.opsteel.pickupasst.App;
import com.opsteel.pickupasst.login.model.bean.Result;
import com.opsteel.pickupasst.login.model.bean.Token;
import com.opsteel.pickupasst.login.model.bean.User;
import com.opsteel.pickupasst.login.view.LoginActivity;
import com.opsteel.pickupasst.mission.view.MissionListActivity;
import com.opsteel.pickupasst.service.ApiManager;
import com.opsteel.pickupasst.service.CallbackWrapper;
import com.opsteel.pickupasst.utils.LogUtils;
import com.opsteel.pickupasst.utils.SharedPrefUtils;

import org.reactivestreams.Subscriber;

import java.io.File;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.objectbox.Box;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.reactive.DataObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 文 件 名: UserModel
 * 创 建 人: Cartman
 * 创建日期: 2017/10/31 13:30
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class UserModel implements IUserModel {
    public UserModel() {
    }

    @Override
    public void login(final Map<String, Object> param, CallbackWrapper<Result<User>> callbackWrapper) {
        //先请求获取token然后获取user
        ApiManager.getInstence().getService().login(param)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<Result<Token>>() {
                    @Override
                    public void accept(Result<Token> tokenResult) throws Exception {
                        //缓存token
                        SharedPrefUtils.setParam(App.getContext(),SharedPrefUtils.TOKEN, new Gson().toJson(tokenResult.getData()));
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<Result<Token>, ObservableSource<Result<User>>>() {
                    @Override
                    public ObservableSource<Result<User>> apply(Result<Token> tokenResult) throws Exception {
                        return ApiManager.getInstence().getService().getUser();
                    }
                })
                .doOnNext(new Consumer<Result<User>>() {
                    @Override
                    public void accept(Result<User> userResult) throws Exception {
                        //缓存user
                        SharedPrefUtils.setParam(App.getContext(),SharedPrefUtils.USER, new Gson().toJson(userResult.getData()));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(callbackWrapper);
    }

    @Override
    public void uploadFile(File file,CallbackWrapper<Result> callbackWrapper) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("uploadFile", file.getName(), requestFile);

        String descriptionString = "This is a description";
        RequestBody description =
                RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
        ApiManager.getInstence().getService().upload(requestFile,body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(callbackWrapper);

    }

    //
    /**
     * 同时并发做三个请求
     *
     * @param param
     */
    private void concurrence(Map<String, Object> param) {
        Observable.zip(
                ApiManager.getInstence().getService().login(param),
                ApiManager.getInstence().getService().login(param),
                ApiManager.getInstence().getService().login(param),
                new Function3<Result<Token>, Result<Token>, Result<Token>, List<Result<Token>>>() {
                    @Override
                    public List<Result<Token>> apply(Result<Token> tokenResult, Result<Token> tokenResult2, Result<Token> tokenResult3) throws Exception {
                        List<Result<Token>> list = new ArrayList<>();
                        list.add(tokenResult);
                        list.add(tokenResult2);
                        list.add(tokenResult3);
                        return list;
                    }
                })
                .subscribeOn(Schedulers.newThread())//这里需要注意的是，网络请求在非ui线程。如果返回结果是依赖于Rxjava的，则需要变换线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Result<Token>>>() {
                    @Override
                    public void onNext(List<Result<Token>> s) {
                        LogUtils.d("");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 串行两个个请求
     * @param param
     */
    private void serialization(Map<String, Object> param) {
        ApiManager.getInstence().getService().login(param)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<Result<Token>>() {
                    @Override
                    public void accept(Result<Token> tokenResult) throws Exception {
                        LogUtils.d("");
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<Result<Token>, ObservableSource<Result<Token>>>() {
                    @Override
                    public ObservableSource<Result<Token>> apply(Result<Token> tokenResult) throws Exception {
                        return ApiManager.getInstence().getService().login(new HashMap<String, Object>());
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Result<Token>>() {
                    @Override
                    public void onNext(Result<Token> tokenResult) {
                        LogUtils.d("");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}