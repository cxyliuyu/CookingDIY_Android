package com.cxyliuyu.www.cookingdiy_android.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cxyliuyu.www.cookingdiy_android.Activity.listener.AddFoodStepListener;
import com.cxyliuyu.www.cookingdiy_android.R;

import java.util.Calendar;
import java.util.Locale;

public class AddFoodStepActivity extends AppCompatActivity {

    LinearLayout toCameraLayout = null;
    ImageView imageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_step);
        toCameraLayout = (LinearLayout)findViewById(R.id.addfoodstep_tocameralayout);
        AddFoodStepListener addFoodStepListener = new AddFoodStepListener(AddFoodStepActivity.this);
        toCameraLayout.setOnClickListener(addFoodStepListener);
        imageView = (ImageView)findViewById(R.id.addfoodstep_foodimg);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            if(bitmap != null){
                toCameraLayout.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
