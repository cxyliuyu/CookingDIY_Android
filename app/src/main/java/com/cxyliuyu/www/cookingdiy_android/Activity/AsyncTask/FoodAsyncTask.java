package com.cxyliuyu.www.cookingdiy_android.Activity.AsyncTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cxyliuyu.www.cookingdiy_android.Activity.Fragment.HomeFragment;
import com.cxyliuyu.www.cookingdiy_android.Business.FoodBusiness;
import com.cxyliuyu.www.cookingdiy_android.utils.NetWorkUtils;

import org.json.JSONObject;

import java.util.HashMap;


/**
 * Created by ly on 2016/5/1.
 */
public class FoodAsyncTask extends AsyncTask{

    String order = null;
    Activity activity = null;
    public HomeFragment homeFragment = null;

    public FoodAsyncTask(String order,Activity activity){
        this.order = order;
        this.activity = activity;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        switch (order){
            case "GETFOODS":
                //获取食物列表
                HashMap<String,String> hashMap = (HashMap<String,String>)params[0];
                String pageSize = hashMap.get("pageSize");
                String pageNum = hashMap.get("pageNum");
                JSONObject jsonObject = null;
                if(NetWorkUtils.isNetWorkConnected(activity)){
                    FoodBusiness foodBusiness = new FoodBusiness(activity);
                    jsonObject = foodBusiness.getFoodsByPage(pageSize,pageNum);
                    return jsonObject;
                }else{
                    Toast.makeText(activity,"网络连接不可用，请检查网络",Toast.LENGTH_LONG).show();
                }
                break;
            case "REGISTER":
                break;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        switch (order){
            case "GETFOODS":
                //调用HomeFragment页面的刷新页面方法
                JSONObject jsonObject = (JSONObject)o;
                homeFragment.refreshView(jsonObject);
            case "REGISTER":
                break;
        }
    }



}
