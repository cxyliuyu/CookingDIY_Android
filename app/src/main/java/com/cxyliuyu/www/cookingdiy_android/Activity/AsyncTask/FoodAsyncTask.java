package com.cxyliuyu.www.cookingdiy_android.Activity.AsyncTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.cxyliuyu.www.cookingdiy_android.Activity.FoodDetailActivity;
import com.cxyliuyu.www.cookingdiy_android.Activity.Fragment.HomeFragment;
import com.cxyliuyu.www.cookingdiy_android.Activity.SearchFoodActivity;
import com.cxyliuyu.www.cookingdiy_android.Business.FoodBusiness;
import com.cxyliuyu.www.cookingdiy_android.utils.NetWorkUtils;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

import org.json.JSONObject;

import java.util.HashMap;


/**
 * Created by ly on 2016/5/1.
 */
public class FoodAsyncTask extends AsyncTask{

    String order = null;
    Activity activity = null;
    public HomeFragment homeFragment = null;
    public FoodDetailActivity foodDetailActivity = null;
    public SearchFoodActivity searchFoodActivity = null;

    public FoodAsyncTask(String order,Activity activity){
        this.order = order;
        this.activity = activity;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        HashMap<String,String> hashMap = (HashMap<String,String>)params[0];
        JSONObject jsonObject = null;
        FoodBusiness foodBusiness = new FoodBusiness(activity);
        switch (order){
            case "GETFOODS":
                //获取食物列表
                String pageSize = hashMap.get("pageSize");
                String pageNum = hashMap.get("pageNum");
                if(NetWorkUtils.isNetWorkConnected(activity)){
                    jsonObject = foodBusiness.getFoodsByPage(pageSize,pageNum);
                    return jsonObject;
                }else{
                    return null;
                }
            case "GETFOODBYID":
                String foodId = hashMap.get("foodId");
                if(NetWorkUtils.isNetWorkConnected(activity)){
                    jsonObject = foodBusiness.getFoodById(foodId);
                    return jsonObject;
                }else{
                    return null;
                }
            case "SEARCHFOODS":
                String key = hashMap.get("key");
                if(NetWorkUtils.isNetWorkConnected(activity)){
                    jsonObject = foodBusiness.searchFoods(key);
                    return jsonObject;
                }else{
                    return null;
                }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        switch (order){
            case "GETFOODS":
                //调用HomeFragment页面的刷新页面方法
                if(o != null){
                    JSONObject jsonObject = (JSONObject)o;
                    homeFragment.refreshView(jsonObject);
                }else {
                    Toast.makeText(activity, "网络连接不可用，请检查网络", Toast.LENGTH_LONG).show();
                }
                break;
            case "GETFOODBYID":
                if(o != null){
                    JSONObject jsonObject = (JSONObject)o;
                    foodDetailActivity.refreshView(jsonObject);
                }else {
                    Toast.makeText(activity, "网络连接不可用，请检查网络", Toast.LENGTH_LONG).show();
                }
                break;
            case "SEARCHFOODS":
                if(o != null){
                    JSONObject jsonObject = (JSONObject)o;
                    searchFoodActivity.refreshView(jsonObject);
                }else{
                    Toast.makeText(activity, "网络连接不可用，请检查网络", Toast.LENGTH_LONG).show();
                }
        }
    }



}
