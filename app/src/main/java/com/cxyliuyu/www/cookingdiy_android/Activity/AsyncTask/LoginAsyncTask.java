package com.cxyliuyu.www.cookingdiy_android.Activity.AsyncTask;

import android.content.Intent;
import android.os.AsyncTask;

import com.cxyliuyu.www.cookingdiy_android.Activity.LoginActivity;
import com.cxyliuyu.www.cookingdiy_android.Business.UserBusiness;
import com.cxyliuyu.www.cookingdiy_android.R;

import java.util.HashMap;

/**
 * Created by ly on 2016/3/14.
 */
public class LoginAsyncTask extends AsyncTask<Object,Integer,Object>{

    //登录的异步任务类
    String order = null;
    LoginActivity activity = null;
    public LoginAsyncTask(String order,LoginActivity activity){
        this.order = order;
        this.activity = activity;
    }

    @Override
    protected Object doInBackground(Object... params) {
        switch (order){
            case "LOGIN":
                try {
                    HashMap<String, String> hashMap = (HashMap<String, String>) params[0];
                    String userName = hashMap.get("userName");
                    String password = hashMap.get("password");
                    UserBusiness userBusiness = new UserBusiness(activity);
                    Boolean result = userBusiness.login(userName, password);
                    return result;
                }catch (Exception e){
                    e.printStackTrace();
                }
            case "REGISTER":
                break;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        switch (order){
            case "LOGIN":
                activity.showProgress(false);
                try{
                    Boolean result = (Boolean)o;
                    if(result){
                        //发送让meFragment更新页面的广播
                        Intent intent = new Intent();
                        intent.setAction("com.cxyliuyu.cookingdiy.refreshmefragment");
                        activity.sendBroadcast(intent);
                        //关闭当前登录页面
                        activity.finish();
                    }else {
                        activity.mPasswordView.setError(activity.getString(R.string.error_incorrect_password));
                        activity.mPasswordView.requestFocus();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case "REGISTER":
                break;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
