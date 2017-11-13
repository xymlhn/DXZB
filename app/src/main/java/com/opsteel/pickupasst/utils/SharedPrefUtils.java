package com.opsteel.pickupasst.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.opsteel.pickupasst.App;
import com.opsteel.pickupasst.login.model.bean.Token;
import com.opsteel.pickupasst.login.model.bean.User;

/**
 * Created by C560 on 2017/10/25.
 */

public class SharedPrefUtils {
    private static final String FILE_NAME = "share_date";
    public static final String IS_LOGIN="isLogin";
    public static final String TOKEN = "token";
    public static final String USER = "user";

    /**
     * save data into FILE_NAME ,this path is data/data/POCKET_NAME/shared_prefs
     * @param context
     * @param key
     * @param object
     */
    public static void setParam(Context context , String key, Object object){

        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if("String".equals(type)){
            editor.putString(key, (String)object);
        }
        else if("Integer".equals(type)){
            editor.putInt(key, (Integer)object);
        }
        else if("Boolean".equals(type)){
            editor.putBoolean(key, (Boolean)object);
        }
        else if("Float".equals(type)){
            editor.putFloat(key, (Float)object);
        }
        else if("Long".equals(type)){
            editor.putLong(key, (Long)object);
        }

        editor.apply();
    }


    /**
     * get value via enter key
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object getParam(Context context , String key, Object defaultObject){
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if("String".equals(type)){
            return sp.getString(key, (String)defaultObject);
        }
        else if("Integer".equals(type)){
            return sp.getInt(key, (Integer)defaultObject);
        }
        else if("Boolean".equals(type)){
            return sp.getBoolean(key, (Boolean)defaultObject);
        }
        else if("Float".equals(type)){
            return sp.getFloat(key, (Float)defaultObject);
        }
        else if("Long".equals(type)){
            return sp.getLong(key, (Long)defaultObject);
        }

        return null;
    }

    /**
     * delete key
     * @param context
     * @param key
     */
    public static void removeParam(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    public static Token getToken(){
        String json = (String) getParam(App.getContext(),TOKEN,"");
        return new Gson().fromJson(json,Token.class);
    }

    public static User getUser(){
        String json = (String) getParam(App.getContext(),USER,"");
        return new Gson().fromJson(json,User.class);
    }
}
