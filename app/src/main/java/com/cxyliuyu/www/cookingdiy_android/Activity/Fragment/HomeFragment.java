package com.cxyliuyu.www.cookingdiy_android.Activity.Fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.baoyz.widget.PullRefreshLayout;
import com.cxyliuyu.www.cookingdiy_android.Activity.AsyncTask.FoodAsyncTask;
import com.cxyliuyu.www.cookingdiy_android.Activity.SearchFoodActivity;
import com.cxyliuyu.www.cookingdiy_android.Business.FoodBusiness;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.FoodListviewAdapter;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {

    private Activity activity;
    private View rootView = null;
    private ImageView toSearchImg = null;
    private ListView foodListView = null;
    private PullRefreshLayout pullRefreshLayout = null;//下拉刷新组件
    public int pageNum = 1;//当前页数

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView(inflater, container);

        return rootView;
    }
    public void initView(LayoutInflater inflater,ViewGroup container){
        //初始化页面
        activity = this.getActivity();
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        toSearchImg = (ImageView)rootView.findViewById(R.id.homefragment_search);
        foodListView = (ListView)rootView.findViewById(R.id.homefragment_foodlistview);
        pullRefreshLayout = (PullRefreshLayout)rootView.findViewById(R.id.homefragment_pullrefreshlayout);

        FoodBusiness foodBusiness = new FoodBusiness(getActivity());
        foodBusiness.getFoodsByPage("10",""+pageNum,foodListView);

        toSearchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SearchFoodActivity.class);
                activity.startActivity(intent);
            }
        });
    }
}
