package com.cxyliuyu.www.cookingdiy_android.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.cxyliuyu.www.cookingdiy_android.R;

public class RegisterActivity extends AppCompatActivity {

    EditText userNameEditText;
    EditText password1EditText;
    EditText password2EditText;
    EditText truenameEditText;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

}
