package com.cxyliuyu.www.cookingdiy_android.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ly on 2016/3/9.
 */
public class SharedpreferencesUtil {
    //SharedPreferences工具类
    public static String getString(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("COOKINGDIY",Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key,null);
        return value;
    }
    public static void setString(Context context,String key,String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences("COOKINGDIY",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }
    public static Boolean getBoolean(Context context,String key,Boolean defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences("COOKINGDIY",Context.MODE_PRIVATE);
        Boolean value = sharedPreferences.getBoolean(key,defaultValue);
        return value;
    }
    public static void setBoolean(Context context,String key,Boolean value){
        SharedPreferences sharedPreferences = context.getSharedPreferences("COOKINGDIY",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

}
