package com.cxyliuyu.www.cookingdiy_android.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.cxyliuyu.www.cookingdiy_android.Activity.listener.RegisterOnClickListener;
import com.cxyliuyu.www.cookingdiy_android.R;

import info.hoang8f.widget.FButton;

public class RegisterActivity extends AppCompatActivity {


    FButton registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = (FButton)findViewById(R.id.register_ok);
        RegisterOnClickListener registerOnClickListener = new RegisterOnClickListener(RegisterActivity.this);
        registerButton.setOnClickListener(registerOnClickListener);
        ImageView backimg = (ImageView)findViewById(R.id.toolbar_back);
        backimg.setOnClickListener(new View.OnClickListener() {
            //返回上个页面
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
    }

}
