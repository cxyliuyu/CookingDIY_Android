package com.cxyliuyu.www.cookingdiy_android.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.cxyliuyu.www.cookingdiy_android.Activity.listener.UploadFoodActivityOnClickListener;
import com.cxyliuyu.www.cookingdiy_android.R;

import info.hoang8f.widget.FButton;

public class UploadFoodActivity extends AppCompatActivity {

    EditText foodNameEditText = null;
    EditText foodContentEditText = null;
    LinearLayout addFoodListLayout = null;
    LinearLayout addFoodStepLayout = null;
    FButton okButton = null;
    FButton cancelButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_food);
        initView();
    }
    public void initView(){
        foodNameEditText = (EditText)findViewById(R.id.uploadactivity_foodnameedittext);
        foodContentEditText = (EditText)findViewById(R.id.uploadactivity_foodcontentedittext);
        addFoodListLayout = (LinearLayout)findViewById(R.id.uploadactivity_addfoodlistlayout);
        addFoodStepLayout = (LinearLayout)findViewById(R.id.uploadactivity_addfoodsteplayout);
        okButton = (FButton)findViewById(R.id.uploadactivity_ok);
        cancelButton = (FButton)findViewById(R.id.uploadactivity_cancel);
        UploadFoodActivityOnClickListener uploadListener = new UploadFoodActivityOnClickListener(UploadFoodActivity.this);
        addFoodStepLayout.setOnClickListener(uploadListener);
        addFoodListLayout.setOnClickListener(uploadListener);
        okButton.setOnClickListener(uploadListener);
        cancelButton.setOnClickListener(uploadListener);
    }
}
