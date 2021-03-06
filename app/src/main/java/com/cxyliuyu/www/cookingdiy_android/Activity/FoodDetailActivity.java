package com.cxyliuyu.www.cookingdiy_android.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cxyliuyu.www.cookingdiy_android.Activity.AsyncTask.FoodAsyncTask;
import com.cxyliuyu.www.cookingdiy_android.Activity.listener.FoodDetailOnClickListener;
import com.cxyliuyu.www.cookingdiy_android.Business.FoodBusiness;
import com.cxyliuyu.www.cookingdiy_android.MyApplication;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.FoodListListviewAdapter;
import com.cxyliuyu.www.cookingdiy_android.utils.FoodStepListViewAdapter;
import com.cxyliuyu.www.cookingdiy_android.utils.ListViewUtils;
import com.cxyliuyu.www.cookingdiy_android.utils.SetImageViewUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;


public class FoodDetailActivity extends AppCompatActivity {

    ImageView foodImageView = null;
    TextView foodNameTextView = null;
    TextView foodContentTextView = null;
    ListView foodlistListView = null;
    ListView foodstepListView = null;
    LinearLayout saveLinearLayout = null;
    LinearLayout commentLinearLayout = null;
    ImageView saveImageView = null;
    ImageView commentImageView = null;
    public Boolean isSaved = false;//当前食谱是否保存
    public String foodId = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ImageView backimg = (ImageView)findViewById(R.id.toolbar_back);
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodDetailActivity.this.finish();
            }
        });
        TextView toobarTitleTextView = (TextView)findViewById(R.id.toolbar_title);
        toobarTitleTextView.setText("详情");

        foodImageView = (ImageView)findViewById(R.id.fooddetail_foodimg);
        foodNameTextView = (TextView)findViewById(R.id.fooddetail_foodnametextview);
        foodContentTextView = (TextView)findViewById(R.id.fooddtetail_foodcontenttextview);
        foodlistListView = (ListView)findViewById(R.id.fooddetail_listlistview);
        foodstepListView = (ListView)findViewById(R.id.fooddetail_steplistview);
        saveLinearLayout = (LinearLayout)findViewById(R.id.fooddetail_saveLinearLayout);
        saveImageView = (ImageView)findViewById(R.id.fooddetail_saveImageView);
        commentLinearLayout = (LinearLayout)findViewById(R.id.fooddetail_commentLinearLayout);
        commentImageView = (ImageView)findViewById(R.id.fooddetail_commentImageView);

        foodId = getIntent().getStringExtra("foodId");
        FoodAsyncTask foodAsyncTask = new FoodAsyncTask("GETFOODBYID",FoodDetailActivity.this);
        foodAsyncTask.foodDetailActivity = FoodDetailActivity.this;
        HashMap<String,String> params = new HashMap<String,String>();
        params.put("foodId",foodId);
        foodAsyncTask.execute(params);

        //初始化是否已经收藏本菜谱
        FoodBusiness foodBusiness = new FoodBusiness(FoodDetailActivity.this);
        foodBusiness.initSave(saveImageView,foodId,FoodDetailActivity.this);
        FoodDetailOnClickListener foodDetailOnClickListener = new FoodDetailOnClickListener(FoodDetailActivity.this,saveImageView,commentImageView);
        saveLinearLayout.setOnClickListener(foodDetailOnClickListener);
        commentLinearLayout.setOnClickListener(foodDetailOnClickListener);

    }

    public void refreshView(JSONObject jsonObject){
        String code = null;
        try{
            code = jsonObject.getString("code");
            if(code.equals("200")){
                JSONObject foodJSONObject = jsonObject.getJSONObject("food");
                JSONArray foodStepArray = jsonObject.getJSONArray("foodsteps");
                JSONArray foodListArray = jsonObject.getJSONArray("foodlist");
                String foodImg = foodJSONObject.getString("foodimg");
                String foodName = foodJSONObject.getString("foodname");
                String foodContent = foodJSONObject.getString("content");

                SetImageViewUtil.setImageToImageView(foodImageView, foodImg);
                foodNameTextView.setText(foodName);
                foodContentTextView.setText(foodContent);
                FoodListListviewAdapter foodListListviewAdapter = new FoodListListviewAdapter(foodListArray,FoodDetailActivity.this);
                FoodStepListViewAdapter foodStepListViewAdapter = new FoodStepListViewAdapter(foodStepArray,FoodDetailActivity.this);
                foodlistListView.setAdapter(foodListListviewAdapter);
                foodstepListView.setAdapter(foodStepListViewAdapter);
                ListViewUtils.setListViewHeightBasedOnChildren(foodlistListView, FoodDetailActivity.this);
                ListViewUtils.setListViewHeightBasedOnChildren(foodstepListView,FoodDetailActivity.this);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FoodBusiness foodBusiness = new FoodBusiness(FoodDetailActivity.this);
        foodBusiness.initSave(saveImageView, foodId, FoodDetailActivity.this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getVolleyRequestQueue().cancelAll("FOODDETAIL");
    }
}
