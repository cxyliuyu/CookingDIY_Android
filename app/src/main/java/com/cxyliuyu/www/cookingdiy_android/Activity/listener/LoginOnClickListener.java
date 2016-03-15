package com.cxyliuyu.www.cookingdiy_android.Activity.listener;


import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;


import com.cxyliuyu.www.cookingdiy_android.Activity.AsyncTask.LoginAsyncTask;
import com.cxyliuyu.www.cookingdiy_android.Activity.LoginActivity;
import com.cxyliuyu.www.cookingdiy_android.R;

import java.util.HashMap;


/**
 * Created by ly on 2016/3/14.
 */

public class LoginOnClickListener implements OnClickListener {

    private LoginActivity activity;
    public LoginOnClickListener(LoginActivity activity){
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in_button:
                //登录按钮被点击
                AutoCompleteTextView mUserNameView = (AutoCompleteTextView)  activity.findViewById(R.id.login_username);
                EditText mPasswordView = (EditText) activity.findViewById(R.id.login_password);
                String userName = mUserNameView.getText().toString();
                String password = mPasswordView.getText().toString();
                HashMap<String,String> hashMap = new HashMap<String,String>();
                hashMap.put("userName",userName);
                hashMap.put("password",password);
                LoginAsyncTask loginAsyncTask = new LoginAsyncTask("LOGIN",activity);
                loginAsyncTask.execute(hashMap);
                break;
            case R.id.register_button:

                break;
        }
    }
}
