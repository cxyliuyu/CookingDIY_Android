package com.cxyliuyu.www.cookingdiy_android.Business;

import android.content.Context;

import com.cxyliuyu.www.cookingdiy_android.Activity.SearchFoodActivity;
import com.cxyliuyu.www.cookingdiy_android.utils.NetWorkUtils;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

import org.json.JSONObject;

import java.util.HashMap;

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
}
