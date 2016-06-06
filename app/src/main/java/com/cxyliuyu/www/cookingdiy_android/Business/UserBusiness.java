package com.cxyliuyu.www.cookingdiy_android.Business;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.VolleyError;
import com.cxyliuyu.www.cookingdiy_android.Activity.RegisterActivity;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.DBUtils;
import com.cxyliuyu.www.cookingdiy_android.utils.MD5Utils;
import com.cxyliuyu.www.cookingdiy_android.utils.NetWorkUtils;
import com.cxyliuyu.www.cookingdiy_android.utils.SharedpreferencesUtil;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;
import com.cxyliuyu.www.cookingdiy_android.utils.VolleyListener;
import com.cxyliuyu.www.cookingdiy_android.utils.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ly on 2016/3/6.
 */
public class UserBusiness {

    private Context context;


    public UserBusiness(Context context){
        this.context = context;
    }


    public Boolean login(String username ,String password){
        //登录
        String value = username+password+ ValueUtils.key;
        String md5Value = MD5Utils.string2MD5(value);
        Log.i("COOKINGDIY", value);
        HashMap<String ,String> params = new HashMap<String,String>();
        params.put("username",username);
        params.put("password",password);
        params.put("value", md5Value);
        Log.i(ValueUtils.LOGTAG,md5Value);
        //发送post请求
        JSONObject jsonResult = NetWorkUtils.sendHttpPost(ValueUtils.loginURL, params);
        if(jsonResult != null){
            //解析登录返回的信息
            String code = null;
            String msg = null;
            try{
                code = jsonResult.getString("code");
                msg = jsonResult.getString("msg");
                if(code.equals("200")){
                    //保存用户信息
                    String userJSONString = jsonResult.getString("data");
                    JSONObject  userJSONObject = new JSONObject(userJSONString);
                    saveUser(userJSONObject);
                    return true;
                }else{
                    if(code.equals("201")){
                        //Toast.makeText(context,"用户名或密码错误",Toast.LENGTH_LONG).show();
                    }else if(code.equals("202")){
                        //Toast.makeText(context,context.getString(R.string.login_key_error),Toast.LENGTH_LONG).show();
                    }
                    return false;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            return false;
        }

        return true;
    }

    public void signOut(){
        SharedpreferencesUtil.setBoolean(context,"ISLOGIN",false);

    }

    public static boolean isLogin(Context context){
        return false;
        //return SharedpreferencesUtil.getBoolean(context,"ISLOGIN",false);
    }

    private void saveUser(JSONObject userJSONObject){

        //保存用户信息
        DBUtils dbUtils = new DBUtils(context);
        //先删除用户表中的所有数据
        dbUtils.clean("CK_USER");
        Log.i(ValueUtils.LOGTAG,"saveUser");
        //设置是否登录
        SharedpreferencesUtil.setBoolean(context,"ISLOGIN",true);
        String id = null;
        String userName = null;
        String password = null;
        String trueName = null;
        String userImg = null;
        HashMap<String ,String> userMap = new HashMap<String,String>();
        try{
            id = userJSONObject.getString("id");
            userName = userJSONObject.getString("username");
            password = userJSONObject.getString("password");
            trueName = userJSONObject.getString("truename");
            userImg = userJSONObject.getString("userimg");
            SharedpreferencesUtil.setString(context,ValueUtils.USERID,id);
            SharedpreferencesUtil.setString(context,ValueUtils.USERPASSWORD,password);
            SharedpreferencesUtil.setString(context,ValueUtils.USERNAME,userName);
            SharedpreferencesUtil.setString(context,ValueUtils.USERIMG,userImg);
            SharedpreferencesUtil.setString(context,ValueUtils.USERTRUENAME,trueName);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void quitLogin(){
        //退出登录
        Log.i(ValueUtils.LOGTAG,"退出登录");
        SharedpreferencesUtil.setBoolean(context, "ISLOGIN", false);
        SharedpreferencesUtil.setString(context,ValueUtils.USERID,"");
    }

    public void register(String userName,String password1,String password2,String trueName, final RegisterActivity registerActivity){
        Map<String,String> map = new HashMap<String,String>();
        map.put("username",userName);
        map.put("password1",password1);
        map.put("password2",password2);
        map.put("truename",trueName);
        VolleyRequest.requestPost(context, ValueUtils.REGISTER, "REGISTER", new VolleyListener() {
            @Override
            public void onMySuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if(code.equals("200")){
                        String msg = jsonObject.getString("msg");
                        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("注册成功")
                                .setConfirmText("确定")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();
                                        registerActivity.finish();
                                    }
                                })
                                .show();
                    }else{
                        String msg = jsonObject.getString("msg");
                        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("错误")
                                .setContentText(msg)
                                .setConfirmText("确定")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();

                                    }
                                })
                                .show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
            }
        }, map);
    }

}
