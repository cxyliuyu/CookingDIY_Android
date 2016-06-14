package com.cxyliuyu.www.cookingdiy_android.Activity;

/**
 * Created by ly on 2016/6/14.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cxyliuyu.www.cookingdiy_android.Business.CommentBusiness;
import com.cxyliuyu.www.cookingdiy_android.R;

public class FoodCommentActivity extends AppCompatActivity {
    int pageNum = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_comment);
        String foodId = getIntent().getStringExtra("foodId");
        CommentBusiness commentBusiness = new CommentBusiness();
        commentBusiness.getComment(foodId,FoodCommentActivity.this,pageNum);

    }

}