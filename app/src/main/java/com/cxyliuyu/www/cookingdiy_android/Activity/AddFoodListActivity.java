package com.cxyliuyu.www.cookingdiy_android.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cxyliuyu.www.cookingdiy_android.R;

public class AddFoodListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_list);
        ImageView backimg = (ImageView)findViewById(R.id.toolbar_back);
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFoodListActivity.this.finish();
            }
        });
    }
}
