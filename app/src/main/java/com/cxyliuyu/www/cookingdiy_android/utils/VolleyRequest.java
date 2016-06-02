package com.cxyliuyu.www.cookingdiy_android.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.cxyliuyu.www.cookingdiy_android.MyApplication;

import java.util.Map;

/**
 * Created by ly on 2016/5/31.
 */
public class VolleyRequest {

    public static void requestGet(Context context,String url,String tag,VolleyListener volleyListener){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,volleyListener.responseListener(),
                volleyListener.errorListener());
        stringRequest.setTag(tag);
        MyApplication.getVolleyRequestQueue().add(stringRequest);
    }
    public static void requestPost(Context context,String url,String tag,VolleyListener volleyListener,final Map<String,String>map){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                volleyListener.responseListener(),volleyListener.errorListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        MyApplication.getVolleyRequestQueue().add(stringRequest);
    }
}
