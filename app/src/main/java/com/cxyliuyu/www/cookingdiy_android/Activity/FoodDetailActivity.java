package com.cxyliuyu.www.cookingdiy_android.Activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.cxyliuyu.www.cookingdiy_android.Activity.AsyncTask.FoodAsyncTask;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.FoodListListviewAdapter;
import com.cxyliuyu.www.cookingdiy_android.utils.FoodStepListViewAdapter;
import com.cxyliuyu.www.cookingdiy_android.utils.SetImageViewUtil;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FoodDetailActivity extends AppCompatActivity {

    ImageView foodImageView = null;
    TextView foodNameTextView = null;
    TextView foodContentTextView = null;
    ListView foodlistListView = null;
    ListView foodstepListView = null;

    String foodId = null;
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

        foodId = getIntent().getStringExtra("foodId");
        FoodAsyncTask foodAsyncTask = new FoodAsyncTask("GETFOODBYID",FoodDetailActivity.this);
        foodAsyncTask.foodDetailActivity = FoodDetailActivity.this;
        HashMap<String,String> params = new HashMap<String,String>();
        params.put("foodId",foodId);
        foodAsyncTask.execute(params);


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
                setListViewHeightBasedOnChildren(foodlistListView);
                setListViewHeightBasedOnChildren(foodstepListView);
//                final Handler myHandler = new Handler() {
//                    public void handleMessage(Message msg) {
//                        setListViewHeightBasedOnChildren(foodstepListView);
//                        super.handleMessage(msg);
//                    }
//                };
//
//                class MyTimerTask1 extends TimerTask {
//                    public void run() {
//                        Message message = new Message();
//                        myHandler.sendMessage(message);
//                    }
//                }
//                Timer timer = new Timer();
//                timer.schedule(new MyTimerTask1(), 3000);// 两秒后启动任务

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void setListViewHeightBasedOnChildren(ListView listView){
        ListAdapter adapter = listView.getAdapter();
        if(adapter == null){
            return;
        }
        int totalHeight = 0;
        for(int i=0;i<adapter.getCount();i++){
            View listItem = adapter.getView(i,null,listView);
            listItem.measure(View.MeasureSpec.makeMeasureSpec(getResources().getDisplayMetrics().widthPixels, View.MeasureSpec.EXACTLY), 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+(listView.getDividerHeight()*(adapter.getCount()));
        listView.setLayoutParams(params);
    }
}
