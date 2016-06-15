package com.cxyliuyu.www.cookingdiy_android.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cxyliuyu.www.cookingdiy_android.Activity.listener.UploadFoodActivityOnClickListener;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.AddFoodUtil;
import com.cxyliuyu.www.cookingdiy_android.utils.FoodListListviewAdapter;
import com.cxyliuyu.www.cookingdiy_android.utils.FoodStepListViewAdapter;
import com.cxyliuyu.www.cookingdiy_android.utils.ListViewUtils;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import info.hoang8f.widget.FButton;

public class UploadFoodActivity extends AppCompatActivity {

    public EditText foodNameEditText = null;
    public EditText foodContentEditText = null;
    LinearLayout addFoodListLayout = null;
    LinearLayout addFoodStepLayout = null;
    FButton okButton = null;
    FButton cancelButton = null;
    ListView foodlistListView = null;
    ListView foodStepListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_food);
        initView();
    }
    public void initView(){
        ImageView backimg = (ImageView)findViewById(R.id.toolbar_back);
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadFoodActivity.this.finish();
            }
        });
        foodNameEditText = (EditText)findViewById(R.id.uploadactivity_foodnameedittext);
        foodContentEditText = (EditText)findViewById(R.id.uploadactivity_foodcontentedittext);
        addFoodListLayout = (LinearLayout)findViewById(R.id.uploadactivity_addfoodlistlayout);
        addFoodStepLayout = (LinearLayout)findViewById(R.id.uploadactivity_addfoodsteplayout);
        okButton = (FButton)findViewById(R.id.uploadactivity_ok);
        cancelButton = (FButton)findViewById(R.id.uploadactivity_cancel);
        foodlistListView = (ListView)findViewById(R.id.uploadactivity_foodlistlistview);
        foodStepListView = (ListView)findViewById(R.id.uploadactivity_foodsteplistview);
        UploadFoodActivityOnClickListener uploadListener = new UploadFoodActivityOnClickListener(UploadFoodActivity.this);
        addFoodStepLayout.setOnClickListener(uploadListener);
        addFoodListLayout.setOnClickListener(uploadListener);
        okButton.setOnClickListener(uploadListener);
        cancelButton.setOnClickListener(uploadListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ValueUtils.LOGTAG, "onResume");
        refreshView();
    }

    public void refreshView(){
        try{
            JSONObject jsonObject = AddFoodUtil.getJSONObject(UploadFoodActivity.this);
            JSONArray foodlistArray = jsonObject.getJSONArray("foodlist");
            JSONArray foodstepArray = jsonObject.getJSONArray("foodsteps");

            FoodListListviewAdapter foodListListviewAdapter = new FoodListListviewAdapter(foodlistArray,UploadFoodActivity.this);
            FoodStepListViewAdapter foodStepListViewAdapter = new FoodStepListViewAdapter(foodstepArray,UploadFoodActivity.this);

            foodlistListView.setAdapter(foodListListviewAdapter);
            foodStepListView.setAdapter(foodStepListViewAdapter);

            ListViewUtils.setListViewHeightBasedOnChildren(foodlistListView,UploadFoodActivity.this);
            ListViewUtils.setListViewHeightBasedOnChildren(foodStepListView,UploadFoodActivity.this);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
