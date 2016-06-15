package com.cxyliuyu.www.cookingdiy_android.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.AddFoodUtil;

import info.hoang8f.widget.FButton;

public class AddFoodListActivity extends AppCompatActivity {

    EditText listNameEditText = null;
    EditText listCountEditText = null;
    FButton addListButton = null;

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
        listNameEditText = (EditText)findViewById(R.id.addfoodlist_listnameedittext);
        listCountEditText = (EditText)findViewById(R.id.addfoodlist_listcountedittext);
        addListButton = (FButton)findViewById(R.id.addfoodlist_ok);

        addListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String listName = listNameEditText.getText().toString();
                String listCount = listCountEditText.getText().toString();
                try{
                    AddFoodUtil.addList(listName,listCount,AddFoodListActivity.this);
                    AddFoodListActivity.this.finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
