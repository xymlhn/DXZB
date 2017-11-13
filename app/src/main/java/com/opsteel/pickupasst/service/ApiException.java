package com.opsteel.pickupasst.service;

/**
 * 文 件 名: ApiException
 * 创 建 人: Cartman
 * 创建日期: 2017/10/31 10:01
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述:自定义异常
 */


public class ApiException extends RuntimeException {
    private int mErrorCode;

    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
    }
}
