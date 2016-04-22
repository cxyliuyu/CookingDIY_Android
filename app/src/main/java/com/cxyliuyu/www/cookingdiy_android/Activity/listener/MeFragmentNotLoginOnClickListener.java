package com.cxyliuyu.www.cookingdiy_android.Activity.listener;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.cxyliuyu.www.cookingdiy_android.Activity.LoginActivity;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

/**
 * Created by ly on 2016/4/22.
 */
public class MeFragmentNotLoginOnClickListener implements View.OnClickListener{

    Activity activity;
    public MeFragmentNotLoginOnClickListener(Activity activity){
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mefragment_userall:
                Log.e(ValueUtils.LOGTAG,"前往登录按钮被点击了");
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
                break;
        }
    }
}
