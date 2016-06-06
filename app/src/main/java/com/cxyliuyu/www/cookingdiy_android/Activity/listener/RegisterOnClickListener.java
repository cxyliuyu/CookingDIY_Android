package com.cxyliuyu.www.cookingdiy_android.Activity.listener;

import android.view.View;
import android.widget.EditText;

import com.cxyliuyu.www.cookingdiy_android.Activity.RegisterActivity;
import com.cxyliuyu.www.cookingdiy_android.Business.UserBusiness;
import com.cxyliuyu.www.cookingdiy_android.R;

/**
 * Created by ly on 2016/6/6.
 */
public class RegisterOnClickListener implements View.OnClickListener{
    private RegisterActivity activity;
    EditText userNameEditText;
    EditText password1EditText;
    EditText password2EditText;
    EditText truenameEditText;


    public RegisterOnClickListener(RegisterActivity activity){
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        if(activity != null){
            userNameEditText = (EditText)activity.findViewById(R.id.register_username);
            password1EditText = (EditText)activity.findViewById(R.id.register_password1);
            password2EditText = (EditText)activity.findViewById(R.id.register_password2);
            truenameEditText = (EditText)activity.findViewById(R.id.register_username);
            switch (v.getId()){
                case R.id.register_ok:
                    String userName = userNameEditText.getText().toString();
                    String password1 = password1EditText.getText().toString();
                    String password2 = password2EditText.getText().toString();
                    String trueName = truenameEditText.getText().toString();
                    UserBusiness userBusiness = new UserBusiness(activity);
                    userBusiness.register(userName,password1,password2,trueName,activity);
                    break;
                default:
                    break;
            }
        }
    }
}
