package com.cxyliuyu.www.cookingdiy_android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by ly on 2016/3/7.
 */
public class NetWorkUtils {
    //封装网络操作的工具类

    public static JSONObject sendHttpPost(String url,HashMap<String, String> params){
        URL realUrl = null;
        InputStream in = null;
        HttpURLConnection conn = null;
        StringBuffer sb = new StringBuffer();
        if(params != null){
            //将哈希表参数转化为字符串
            for(Map.Entry<String,String>e :params.entrySet()){
                sb.append(e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
            sb=sb.deleteCharAt(sb.length() - 1);
        }
        String stringParams = sb.toString();


        //发送请求
        try{
            realUrl = new URL(url);
            conn = (HttpURLConnection)realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            PrintWriter pw = new PrintWriter(conn.getOutputStream());
            pw.print(stringParams);
            pw.flush();
            pw.close();
            in = conn.getInputStream();//获得返回的输入流
        }catch(Exception e){
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        String result = buffer.toString();//返回低字符串
        Log.i(URLUtils.LOGTAG,"result = "+ result);

        return null;

    }

    public static boolean isNetWorkConnected(Context context) {
        // 判断网络连接是否可用
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo != null) {
                // 网络连接可用
                return networkInfo.isAvailable();
            } else {
                Log.e(URLUtils.LOGTAG, "网络连接不可用");
            }
        }
        return false;
    }


}
