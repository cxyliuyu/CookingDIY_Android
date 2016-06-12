package com.cxyliuyu.www.cookingdiy_android.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.cxyliuyu.www.cookingdiy_android.Business.FoodBusiness;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.SharedpreferencesUtil;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

public class FoodListActivity extends AppCompatActivity {

    PullRefreshLayout pullRefreshLayout = null;
    String action = null;
    ListView listView = null;
    int pageNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        String title = getIntent().getStringExtra("title");
        action = getIntent().getStringExtra("action");
        TextView toobarTitleTextView = (TextView)findViewById(R.id.toolbar_title);
        toobarTitleTextView.setText(title);
        ImageView backimg = (ImageView)findViewById(R.id.toolbar_back);
        listView = (ListView)findViewById(R.id.foodlist_listview);
        backimg.setOnClickListener(new View.OnClickListener() {
            //返回上个页面
            @Override
            public void onClick(View v) {
                FoodListActivity.this.finish();
            }
        });
        pullRefreshLayout = (PullRefreshLayout) findViewById(R.id.foodlist_pullrefreshlayout);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh
                Log.i(ValueUtils.LOGTAG, "下拉刷新");
                initFoodList();
                stopRefreshing();
            }
        });
        initFoodList();

    }
    private void stopRefreshing(){
        pullRefreshLayout.setRefreshing(false);
    }
    private void initFoodList(){
        if(action != null){
            String userId = SharedpreferencesUtil.getString(FoodListActivity.this,ValueUtils.USERID);
            FoodBusiness foodBusiness = new FoodBusiness(FoodListActivity.this);
            if(action.equals("SHOWSAVE")){
                //显示我的收藏
                foodBusiness.getSaveByPage(listView);
            }
            if(action.equals("SHOWFOOD")){
                foodBusiness.getFoodByUserId(listView);

            }
        }
    }
}
