package com.haha.simplenews.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.haha.simplenews.MyApplication;


/**
 * Created by 格格不入 on 2017/11/7.
 * SharedPreferences工具类
 */

public class SpUtils {
    /**
     * 存一个String
     * @param key   键
     * @param value  值
     */
    public static void setString(String key, String value){
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences("Config", Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    /**
     * 获取一个字符串
     * @param key  键
     * @param defultValue  默认返回值
     * @return  返回一个字符串
     */
    public static String getString(String key, String defultValue){
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences("Config", Context.MODE_PRIVATE);
        return  sp.getString(key,defultValue);
    }

    /**
     * 存一个boolean
     * @param key  键
     * @param value  值
     */
    public static void setBoolean(String key, boolean value){
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences("Config", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    /**
     * 获取一个boolean
     * @param key  键
     * @param defultValue  默认值
     * @return
     */
    public static boolean getBoolean(String key, boolean defultValue){
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences("Config", Context.MODE_PRIVATE);
        return  sp.getBoolean(key,defultValue);
    }
}
