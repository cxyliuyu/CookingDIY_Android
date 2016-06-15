package com.cxyliuyu.www.cookingdiy_android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


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
        String result = buffer.toString();//返回字符串
        Log.i(ValueUtils.LOGTAG,"网络请求的返回结果result = "+ result);
        JSONObject josonResult = null;
        try{
            josonResult = new JSONObject(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return josonResult;
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
                Log.e(ValueUtils.LOGTAG, "网络连接不可用");
            }
        }
        return false;
    }

    //利用URL获取网络上的图片
    public static Bitmap getBitmap(String url) {
        URL imageURL = null;
        Bitmap bitmap = null;
        Log.e("inuni","URL = "+url);
        try {
            imageURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) imageURL
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static String uploadFile(File file ,String RequestURL){
        //上传文件
        int TIME_OUT = 10*10000000;
        String CHARSET = "utf-8"; //设置编码
        String SUCCESS="1";
        String FAILURE="0";
        String BOUNDARY = UUID.randomUUID().toString(); //边界标识 随机生成
        String PREFIX = "--" , LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data"; //内容类型
        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true); //允许输入流
            conn.setDoOutput(true); //允许输出流
            conn.setUseCaches(false); //不允许使用缓存
            conn.setRequestMethod("POST"); //请求方式
            conn.setRequestProperty("Charset", CHARSET);
            //设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
            if(file!=null) {
                /** * 当文件不为空，把文件包装并且上传 */
                OutputStream outputSteam=conn.getOutputStream();
                DataOutputStream dos = new DataOutputStream(outputSteam);
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY); sb.append(LINE_END);
                /**
                 * 这里重点注意：
                 * name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后缀名的 比如:abc.png
                 */
                sb.append("Content-Disposition: form-data; name=\"img\"; filename=\""+file.getName()+"\""+LINE_END);
                sb.append("Content-Type: application/octet-stream; charset="+CHARSET+LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while((len=is.read(bytes))!=-1)
                {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX+BOUNDARY+PREFIX+LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();
                /**
                 * 获取响应码 200=成功
                 * 当响应成功，获取响应的流
                 */
                int res = conn.getResponseCode();
                Log.e(ValueUtils.LOGTAG, "response code:"+res);
                if(res==200)
                {
                    InputStream in = conn.getInputStream();
                    in = conn.getInputStream();
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
                    String result = buffer.toString();//返回字符串
                    //return SUCCESS;
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FAILURE;
    }

}
