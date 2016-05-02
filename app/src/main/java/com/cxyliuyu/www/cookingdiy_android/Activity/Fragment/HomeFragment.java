package com.cxyliuyu.www.cookingdiy_android.Activity.Fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.cxyliuyu.www.cookingdiy_android.Activity.AsyncTask.FoodAsyncTask;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.FoodListviewAdapter;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomeFragment extends Fragment {

    private Activity activity;
    private View rootView = null;
    private ImageView toSearchImg = null;
    private ListView foodListView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = this.getActivity();
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        toSearchImg = (ImageView)rootView.findViewById(R.id.homefragment_search);
        foodListView = (ListView)rootView.findViewById(R.id.homefragment_foodlistview);
        HashMap<String,String> hashMap = new HashMap<String,String>();
        hashMap.put("pageSize","10");
        hashMap.put("pageNum","1");
        FoodAsyncTask foodAsyncTask = new FoodAsyncTask("GETFOODS",activity);
        foodAsyncTask.homeFragment = this;
        foodAsyncTask.execute(hashMap);
        return rootView;
    }
    public void refreshView(JSONObject jsonObject){
        //刷新页面

        try {
            String code = jsonObject.getString("code");
            if(code.equals("200")){
                //返回菜谱列表成功
                JSONArray foodArray = jsonObject.getJSONArray("list");
                ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
                for(int i=0;i<foodArray.length();i++){
                    JSONObject foodJsonObject = foodArray.getJSONObject(i);
                    String foodId = foodJsonObject.getString("id");
                    String foodName = foodJsonObject.getString("foodname");
                    String foodImg = foodJsonObject.getString("foodimg");
                    String userId = foodJsonObject.getString("userid");
                    String content = foodJsonObject.getString("content");
                    HashMap<String,String>map = new HashMap<String,String>();
                    map.put("foodId",foodId);
                    map.put("foodName",foodName);
                    map.put("foodImg",foodImg);
                    map.put("content",content);
                    map.put("userId",userId);
                    list.add(map);
                }
                FoodListviewAdapter foodListviewAdapter = new FoodListviewAdapter(list,activity);
                foodListView.setAdapter(foodListviewAdapter);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
