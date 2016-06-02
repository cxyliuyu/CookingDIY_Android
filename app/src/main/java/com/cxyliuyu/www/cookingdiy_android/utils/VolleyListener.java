package com.cxyliuyu.www.cookingdiy_android.utils;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by ly on 2016/6/1.
 */
public abstract class VolleyListener {

    //请求成功的回调函数
    public abstract void onMySuccess(String result);
    //请求错误的回调函数
    public abstract void onMyError(VolleyError error);

    public Response.Listener<String> responseListener(){
        return  new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                onMySuccess(response);
            }
        };
    }
    public Response.ErrorListener errorListener(){
        return new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                onMyError(error);
            }
        };
    }
}
