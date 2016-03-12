package com.cxyliuyu.www.cookingdiy_android.Business;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;


import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.MD5Utils;
import com.cxyliuyu.www.cookingdiy_android.utils.NetWorkUtils;
import com.cxyliuyu.www.cookingdiy_android.utils.SharedpreferencesUtil;
import com.cxyliuyu.www.cookingdiy_android.utils.URLUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

        String value = username+password+URLUtils.key;
        String md5Value = MD5Utils.string2MD5(value);
        Log.i("COOKINGDIY", value);
        HashMap<String ,String> params = new HashMap<String,String>();
        params.put("username",username);
        params.put("password",password);
        params.put("value", md5Value);
        Log.i(URLUtils.LOGTAG,md5Value);

        //发送post请求
        JSONObject jsonResult = NetWorkUtils.sendHttpPost(URLUtils.loginURL, params);
        if(jsonResult != null){
            //解析登录返回的信息
            String code = null;
            String msg = null;
            try{
                code = jsonResult.getString("code");
                msg = jsonResult.getString("msg");
                if(code.equals("200")){
                    //保存用户信息

                    return true;
                }else{
                    if(code.equals("201")){
                        Toast.makeText(context,context.getString(R.string.login_user_error),Toast.LENGTH_LONG).show();
                    }else if(code.equals("202")){
                        Toast.makeText(context,context.getString(R.string.login_key_error),Toast.LENGTH_LONG).show();
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

    public static boolean isLogin(Context context){
        return SharedpreferencesUtil.getBoolean(context,"ISLOGIN",false);
    }

    private void saveUser(JSONObject userJSONObject){
        //保存用户信息

        //设置是否登录
        SharedpreferencesUtil.setBoolean(context,"ISLOGIN",true);

    }

}
