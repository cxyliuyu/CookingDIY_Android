package com.cxyliuyu.www.cookingdiy_android.Activity.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.cxyliuyu.www.cookingdiy_android.Activity.FoodListActivity;
import com.cxyliuyu.www.cookingdiy_android.Activity.LoginActivity;
import com.cxyliuyu.www.cookingdiy_android.Business.UserBusiness;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.SharedpreferencesUtil;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

/**
 * Created by ly on 2016/4/23.
 */
public class MeFragmentOnClickListener implements View.OnClickListener{
    Boolean isLogin = null;
    Activity activity;

    public MeFragmentOnClickListener(Activity activity){
        this.activity = activity;
    }
    @Override
    public void onClick(View v) {
        isLogin = SharedpreferencesUtil.getBoolean(activity, ValueUtils.ISLOGIN,false);
        if(isLogin == true){
            //用户已经登录
            switch (v.getId()){
                case R.id.mefragment_userall:
                    break;
                case R.id.mefragment_mysave:
                    toMySave(activity);
                    break;
                case R.id.mefragment_upload:
                    break;
                case R.id.mefragment_myfood:
                    break;
                case R.id.meFragment_quit:
                    quit();
                    break;
            }
        }else{
            //用户还没登录
            switch (v.getId()){
                case R.id.mefragment_userall:
                    toLogin();
                    break;
            }
        }
    }
    private void toChangeUserInfo(){

    }
    private void toUpload(){

    }
    private void toMyFood(){

    }
    private void quit(){
        UserBusiness userBusiness = new UserBusiness(activity);
        userBusiness.quitLogin();
        Intent intent = new Intent();
        intent.setAction(ValueUtils.refreshmefragment);
        activity.sendBroadcast(intent);
    }
    private void toLogin(){
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }
    private void toMySave(Activity activity){
        Intent intent = new Intent(activity, FoodListActivity.class);
        intent.putExtra("title","我的收藏");
        intent.putExtra("action","SHOWSAVE");
        activity.startActivity(intent);
    }
}
