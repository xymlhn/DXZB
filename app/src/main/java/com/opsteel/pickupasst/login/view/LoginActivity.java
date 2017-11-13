package com.opsteel.pickupasst.login.view;

/**
 * 文 件 名: LoginActivity
 * 创 建 人: Cartman
 * 创建日期: 2017/10/27 11:18
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：验证码登录
 */

import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.opsteel.pickupasst.R;
import com.opsteel.pickupasst.base.BaseActivity;
import com.opsteel.pickupasst.login.model.bean.User;
import com.opsteel.pickupasst.login.presenter.DaggerIUserComponent;
import com.opsteel.pickupasst.login.presenter.UserModule;
import com.opsteel.pickupasst.login.presenter.UserPresenter;
import com.opsteel.pickupasst.mission.view.MissionListActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class LoginActivity extends BaseActivity implements ILoginView{

    @Inject
    UserPresenter userPresenter;
    private EditText phoneEditText;
    private EditText validEditText;
    private Button loginBtn;
    private Button vertifyCodeBtn;
    private static int MAX_COUNT_TIME = 10;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        initTitle(R.string.title_login);
        hideBack();
        phoneEditText = findViewById(R.id.phoneEditText);
        validEditText = findViewById(R.id.validEditText);
        loginBtn = findViewById(R.id.loginBtn);
        vertifyCodeBtn = findViewById(R.id.vertifyCodeBtn);
    }

    @Override
    protected void setListener() {

        Observable<CharSequence> observablePhone = RxTextView.textChanges(phoneEditText);
        Observable<CharSequence> observableValid = RxTextView.textChanges(validEditText);
        //根据手机输入框判断验证码是否可用
        observablePhone.map(new Function<CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence) throws Exception {
                return charSequence.toString().length() >= 6;
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        RxView.enabled(vertifyCodeBtn).accept(aBoolean);
                    }
                });

        RxView.clicks(vertifyCodeBtn)
                .throttleFirst(MAX_COUNT_TIME, TimeUnit.SECONDS)
                .flatMap(new Function<Object, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(Object o) throws Exception {
                        //更新发送按钮的状态并初始化显现倒计时文字
                        RxView.enabled(vertifyCodeBtn).accept(false);
                        RxTextView.text(vertifyCodeBtn).accept(MAX_COUNT_TIME + " 秒");

                        return Observable.interval(1, TimeUnit.SECONDS, Schedulers.io()).take(MAX_COUNT_TIME);
                    }
                })
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return MAX_COUNT_TIME - (aLong + 1);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (aLong == 0) {
                            RxView.enabled(vertifyCodeBtn).accept(true);
                            RxTextView.text(vertifyCodeBtn).accept("发送验证码");
                        } else {
                            RxTextView.text(vertifyCodeBtn).accept(aLong + " 秒");
                        }
                    }
                });

        Observable.combineLatest(observablePhone, observableValid, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence phone, CharSequence valid) throws Exception {
                return (phone.toString().length() == 11) && (valid.toString().length() >= 6);
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                RxView.enabled(loginBtn).accept(aBoolean);
                loginBtn.setBackgroundColor(getResources().getColor(aBoolean ? R.color.mainColor : R.color.disableColor));
            }
        });

        RxView.clicks(loginBtn)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Map<String, Object> map = new HashMap<String, Object>() {{
                            put("username", "15521314713");
                            put("password", "123456");
                            put("bundleId", "com.AiSiPai");
                            put("version", "1.0.0");
                        }};
                        userPresenter.login(map);
                    }
                });
    }

    @Override
    protected void initData() {
        DaggerIUserComponent.builder()
                .userModule(new UserModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onLoginSuccess(User user) {
        MissionListActivity.openActivity(this);
    }

    @Override
    public void onUploadSuccess() {

    }

}
