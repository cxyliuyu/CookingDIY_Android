package com.cxyliuyu.www.cookingdiy_android.Business;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.cxyliuyu.www.cookingdiy_android.utils.VolleyListener;
import com.cxyliuyu.www.cookingdiy_android.utils.VolleyRequest;

import org.json.JSONException;
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

    public void initSave(final ImageView saveImageView,String foodId,final FoodDetailActivity activity){
        //初始化某食物是否被保存,返回值是是否保存
        Boolean islogin = SharedpreferencesUtil.getBoolean(context, ValueUtils.ISLOGIN, false);
        if(islogin = true){
            //检查是否收藏
            //使用二次封装的volley方法
            Map<String,String> map = new HashMap<String,String>();
            map.put("userId", SharedpreferencesUtil.getString(context,ValueUtils.USERID));
            map.put("foodId", foodId);
            VolleyRequest.requestPost(activity, ValueUtils.ISSAVED, "FOODDETAIL", new VolleyListener() {

                @Override
                public void onMySuccess(String result) {
                    //Log.i(ValueUtils.LOGTAG,"请求成功 result ="+result);
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        if (code.equals("200")) {
                            Log.i(ValueUtils.LOGTAG, "200,response = " + result);
                            saveImageView.setImageResource(R.drawable.shoucang_y);
                            activity.isSaved = true;
                        } else {
                            Log.i(ValueUtils.LOGTAG, "201,response = " + result);
                            saveImageView.setImageResource(R.drawable.shoucang_n);
                            activity.isSaved = false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onMyError(VolleyError error) {
                    Log.i(ValueUtils.LOGTAG, "请求失败");
                }
            }, map);
        }else{
            //什么也不干
            Log.i(ValueUtils.LOGTAG, "haha = " + "没登陆");
        }
    }

    public void deleteSava(String foodId, final FoodDetailActivity activity,final ImageView saveImageView){
        //取消收藏
        Map<String,String>map = new HashMap<String,String>();
        map.put("userId", SharedpreferencesUtil.getString(context,ValueUtils.USERID));
        map.put("foodId", foodId);
        VolleyRequest.requestPost(activity, ValueUtils.DELETESAVE, "FOODDETAIL", new VolleyListener() {
            @Override
            public void onMySuccess(String result) {
                //取消收藏成功
                try {
                    Log.i(ValueUtils.LOGTAG,"result = "+ result);
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if(code.equals("200")){
                        saveImageView.setImageResource(R.drawable.shoucang_n);
                        activity.isSaved = false;
                    }else{
                        Toast.makeText(activity,"取消收藏失败",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                //取消收藏失败
                Toast.makeText(activity,"取消收藏失败，请检查您的网络",Toast.LENGTH_LONG).show();
            }
        },map);
    }

    public void addSave(String foodId,final FoodDetailActivity activity,final ImageView saveImageView){
        Map<String,String>map = new HashMap<String,String>();
        map.put("userId", SharedpreferencesUtil.getString(context,ValueUtils.USERID));
        map.put("foodId", foodId);
        VolleyRequest.requestPost(activity, ValueUtils.ADDSAVE, "FOODDETAIL", new VolleyListener() {
            @Override
            public void onMySuccess(String result) {
                //添加收藏成功
                try {
                    Log.i(ValueUtils.LOGTAG, "result = " + result);
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("200")) {
                        saveImageView.setImageResource(R.drawable.shoucang_y);
                        activity.isSaved = true;
                    } else {
                        Toast.makeText(activity, "取消收藏失败", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                //添加收藏失败
                Toast.makeText(activity, "添加收藏失败，请检查您的网络", Toast.LENGTH_LONG).show();
            }
        }, map);
    }

}
