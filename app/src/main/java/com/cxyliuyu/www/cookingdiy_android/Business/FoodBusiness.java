package com.cxyliuyu.www.cookingdiy_android.Business;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cxyliuyu.www.cookingdiy_android.Activity.FoodDetailActivity;
import com.cxyliuyu.www.cookingdiy_android.Activity.SearchFoodActivity;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.NetWorkUtils;
import com.cxyliuyu.www.cookingdiy_android.utils.SharedpreferencesUtil;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ly on 2016/5/2.
 */
public class FoodBusiness {
    private Context context;
    public FoodBusiness(Context context){
        this.context = context;
    }

    public JSONObject getFoodsByPage(String pageSize,String pageNum){
        HashMap<String ,String> params = new HashMap<String,String>();
        params.put("pageSize",pageSize);
        params.put("pageNum",pageNum);

        JSONObject jsonObject = NetWorkUtils.sendHttpPost(ValueUtils.GETFOODBYPAGE,params);

        return jsonObject;
    }
    public JSONObject getFoodById(String foodId){
        HashMap<String,String> params = new HashMap<String,String>();
        params.put("id",foodId);
        JSONObject jsonObject = NetWorkUtils.sendHttpPost(ValueUtils.GETFOODBYID,params);
        return jsonObject;
    }
    public JSONObject searchFoods(String key){
        HashMap<String,String> params = new HashMap<String,String>();
        params.put("key",key);
        JSONObject jsonObject = NetWorkUtils.sendHttpPost(ValueUtils.SEARCHFOODS,params);
        return jsonObject;
    }
    public void initSave(final ImageView saveImageView,final String foodId,final FoodDetailActivity activity){
        //初始化某食物是否被保存,返回值是是否保存
        Boolean islogin = SharedpreferencesUtil.getBoolean(context, ValueUtils.ISLOGIN, false);
        if(islogin = true){
            //检查是否收藏
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    ValueUtils.ISSAVED,new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String code = jsonObject.getString("code");
                        if(code.equals("200")){
                            Log.i(ValueUtils.LOGTAG, "200,response = " + response);
                            saveImageView.setImageResource(R.drawable.shoucang_y);
                            activity.isSaved = true;
                        }else{
                            Log.i(ValueUtils.LOGTAG, "201,response = " + response);
                            saveImageView.setImageResource(R.drawable.shoucang_n);
                            activity.isSaved = false;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.i(ValueUtils.LOGTAG,"请求错误");
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("userId", SharedpreferencesUtil.getString(context,ValueUtils.USERID));
                    params.put("foodId", foodId);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Content-Type","application/x-www-form-urlencoded");
                    return headers;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(stringRequest);

        }else{
            //什么也不干
            Log.i(ValueUtils.LOGTAG, "haha = " + "没登陆");
        }
    }
}
