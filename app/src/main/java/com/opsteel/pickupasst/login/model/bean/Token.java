package com.opsteel.pickupasst.login.model.bean;

import java.io.Serializable;

/**
 * 文 件 名: Token
 * 创 建 人: Cartman
 * 创建日期: 2017/10/31 09:02
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class Token{
    private String accessToken;// 访问服务端Token凭证 ,
    private long expaireAt;// 过期时间戳

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpaireAt() {
        return expaireAt;
    }

    public void setExpaireAt(long expaireAt) {
        this.expaireAt = expaireAt;
    }
}
