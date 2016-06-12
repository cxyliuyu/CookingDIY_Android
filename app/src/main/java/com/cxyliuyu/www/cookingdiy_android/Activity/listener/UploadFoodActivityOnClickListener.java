package com.cxyliuyu.www.cookingdiy_android.Activity.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.cxyliuyu.www.cookingdiy_android.Activity.AddFoodListActivity;
import com.cxyliuyu.www.cookingdiy_android.Activity.AddFoodStepActivity;
import com.cxyliuyu.www.cookingdiy_android.R;

/**
 * Created by ly on 2016/6/12.
 */
public class UploadFoodActivityOnClickListener implements View.OnClickListener{
    Activity activity;

    public UploadFoodActivityOnClickListener(Activity activity){
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.uploadactivity_addfoodlistlayout:
                toAddFoodList();
                break;
            case R.id.uploadactivity_addfoodsteplayout:
                toAddFoodStep();
                break;
            case R.id.uploadactivity_ok:
                break;
            case R.id.uploadactivity_cancel:
                break;
        }
    }
    public void toAddFoodList(){
        Intent intent = new Intent(activity, AddFoodListActivity.class);
        activity.startActivity(intent);
    }
    public void toAddFoodStep(){
        Intent intent = new Intent(activity, AddFoodStepActivity.class);
        activity.startActivity(intent);
    }

}
