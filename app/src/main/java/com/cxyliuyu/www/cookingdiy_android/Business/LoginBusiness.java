package com.cxyliuyu.www.cookingdiy_android.Business;

import android.content.Context;
import android.os.Handler;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ly on 2016/3/6.
 */
public class LoginBusiness {

    private Context context;
    public LoginBusiness(Context context){
        this.context = context;
    }
    public void login(/*String username ,String password*/){
        //登录
        RequestQueue mQueue = Volley.newRequestQueue(context);
    }

    public static boolean isLogin(){
        return false;
    }

}
