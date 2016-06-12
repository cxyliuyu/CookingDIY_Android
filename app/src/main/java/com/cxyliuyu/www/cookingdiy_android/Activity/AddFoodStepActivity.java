package com.cxyliuyu.www.cookingdiy_android.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.cxyliuyu.www.cookingdiy_android.Activity.listener.AddFoodStepListener;
import com.cxyliuyu.www.cookingdiy_android.R;

public class AddFoodStepActivity extends AppCompatActivity {

    LinearLayout toCameraLayout = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_step);
        toCameraLayout = (LinearLayout)findViewById(R.id.addfoodstep_tocameralayout);
        AddFoodStepListener addFoodStepListener = new AddFoodStepListener(AddFoodStepActivity.this);
        toCameraLayout.setOnClickListener(addFoodStepListener);
    }

}
