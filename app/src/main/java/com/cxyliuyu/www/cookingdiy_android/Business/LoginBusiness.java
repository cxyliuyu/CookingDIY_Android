package com.cxyliuyu.www.cookingdiy_android.Business;

import android.content.Context;
import android.util.Log;


import com.cxyliuyu.www.cookingdiy_android.utils.MD5Utils;
import com.cxyliuyu.www.cookingdiy_android.utils.NetWorkUtils;
import com.cxyliuyu.www.cookingdiy_android.utils.URLUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ly on 2016/3/6.
 */
public class LoginBusiness {

    private Context context;


    public LoginBusiness(Context context){
        this.context = context;
    }


    public Boolean login(String username ,String password){
        //登录
        String md5UserName = MD5Utils.string2MD5(username);
        String md5password = MD5Utils.string2MD5(password);
        String value = username+password+URLUtils.key;
        String md5Value = MD5Utils.string2MD5(value);
        Log.i("COOKINGDIY", value);
        HashMap<String ,String> params = new HashMap<String,String>();
        params.put("username",username);
        params.put("password",password);
        params.put("value", md5Value);
        Log.i(URLUtils.LOGTAG,md5Value);

        //发送post请求
        NetWorkUtils.sendHttpPost(URLUtils.loginURL,params);

        return true;
    }

    public static boolean isLogin(){
        return false;
    }

}
