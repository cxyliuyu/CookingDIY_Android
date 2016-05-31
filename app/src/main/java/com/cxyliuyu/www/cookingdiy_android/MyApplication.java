package com.cxyliuyu.www.cookingdiy_android;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ly on 2016/5/31.
 */
public class MyApplication extends Application{
    public static RequestQueue requestQueue = null;
    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getVolleyRequestQueue(){
        return requestQueue;
    }
}
