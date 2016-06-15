package com.cxyliuyu.www.cookingdiy_android.Activity.listener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.android.volley.VolleyError;
import com.cxyliuyu.www.cookingdiy_android.Activity.AddFoodListActivity;
import com.cxyliuyu.www.cookingdiy_android.Activity.AddFoodStepActivity;
import com.cxyliuyu.www.cookingdiy_android.Activity.UploadFoodActivity;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.AddFoodUtil;
import com.cxyliuyu.www.cookingdiy_android.utils.FoodListviewAdapter;
import com.cxyliuyu.www.cookingdiy_android.utils.ListViewUtils;
import com.cxyliuyu.www.cookingdiy_android.utils.SharedpreferencesUtil;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;
import com.cxyliuyu.www.cookingdiy_android.utils.VolleyListener;
import com.cxyliuyu.www.cookingdiy_android.utils.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ly on 2016/6/12.
 */
public class UploadFoodActivityOnClickListener implements View.OnClickListener{
    UploadFoodActivity activity;
    JSONArray foodlistArray;
    JSONArray foodstepArray;
    String foodId = null;
    SweetAlertDialog pDialog = null;
    //判断上传操作工程的变量
    int process;
    int allProcess;

    public UploadFoodActivityOnClickListener(UploadFoodActivity activity){
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.uploadactivity_addfoodlistlayout:
                toAddFoodList();
                break;
            case R.id.uploadactivity_addfoodsteplayout:
                toAddFoodStep();
                break;
            case R.id.uploadactivity_ok:
                saveFood();
                break;
            case R.id.uploadactivity_cancel:
                cancelFood();
                break;
        }
    }
    public void toAddFoodList(){
        Intent intent = new Intent(activity, AddFoodListActivity.class);
        activity.startActivity(intent);
    }
    public void toAddFoodStep(){
        Intent intent = new Intent(activity, AddFoodStepActivity.class);
        activity.startActivity(intent);
    }
    public void cancelFood(){
        SharedpreferencesUtil.setString(activity, "foodJSONString", "");
    }
    public void saveFood(){
        JSONObject jsonObject;
        try{
            jsonObject = AddFoodUtil.getJSONObject(activity);
            String userId = SharedpreferencesUtil.getString(activity, ValueUtils.USERID);
            String foodName = activity.foodNameEditText.getText().toString();
            String content = activity.foodContentEditText.getText().toString();
            String foodImg = jsonObject.getString("foodimg");
            foodlistArray = jsonObject.getJSONArray("foodlist");
            foodstepArray = jsonObject.getJSONArray("foodsteps");
            uploadFood(userId, foodName, content, foodImg);


        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void uploadFood(String userId,String foodName,String content,String foodImg){
        Map<String,String> map = new HashMap<String,String>();
        map.put("userId", SharedpreferencesUtil.getString(activity,ValueUtils.USERID));
        map.put("foodName", foodName);
        map.put("content",content);
        map.put("foodImg",foodImg);
        VolleyRequest.requestPost(activity, ValueUtils.ADDFOOD, "UPLOADFOOD", new VolleyListener() {
            @Override
            public void onMySuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("200")) {
                        foodId = jsonObject.getString("id");
                        pDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        pDialog.setTitleText("Loading");
                        pDialog.setCancelable(false);
                        pDialog.show();
                        process = 0;
                        allProcess = foodlistArray.length()+foodstepArray.length();
                        uploadFoodList(foodlistArray);
                        uploadFoodStep(foodstepArray);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                Log.i(ValueUtils.LOGTAG, "请求出错");
                error.printStackTrace();
            }
        }, map);
    }

    private void uploadFoodStep(JSONArray foodstepArray){
        for(int i=0;i<foodstepArray.length();i++){
            try{
                JSONObject foodStepObject = foodstepArray.getJSONObject(i);
                String stepImg = foodStepObject.getString("stepimg");
                String stepTxt = foodStepObject.getString("steptxt");
                String stepOrder = foodStepObject.getString("steporder");
                Map<String,String> map = new HashMap<String,String>();
                map.put("stepImg", stepImg);
                map.put("stepTxt", stepTxt);
                map.put("stepOrder", stepOrder);
                map.put("foodId",foodId);
                VolleyRequest.requestPost(activity, ValueUtils.ADDFOODSTEP, "GETSAVEBYPAGE", new VolleyListener() {
                    @Override
                    public void onMySuccess(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            String code = jsonObject.getString("code");
                            if (code.equals("200")) {
                                process++;
                                if(process == allProcess){
                                    finishUpload();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        Log.i(ValueUtils.LOGTAG, "请求出错");
                        error.printStackTrace();
                    }
                }, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void uploadFoodList(JSONArray foodlistArray){
        for(int i=0;i<foodlistArray.length();i++){
            try{
                JSONObject foodListObject = foodlistArray.getJSONObject(i);
                String foodListName = foodListObject.getString("foodlistname");
                String foodListCount = foodListObject.getString("foodlistcount");
                Map<String,String> map = new HashMap<String,String>();
                map.put("foodListName", foodListName);
                map.put("foodListCount", foodListCount);
                map.put("foodId",foodId);
                VolleyRequest.requestPost(activity, ValueUtils.ADDFOODLIST, "UPLOADFOOD", new VolleyListener() {
                    @Override
                    public void onMySuccess(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            String code = jsonObject.getString("code");
                            if (code.equals("200")) {
                                process++;
                                if(process == allProcess){
                                    finishUpload();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        Log.i(ValueUtils.LOGTAG, "请求出错");
                        error.printStackTrace();
                    }
                }, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void finishUpload(){
        pDialog.cancel();
        new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("上传成功!")
                .setConfirmText("确定")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        activity.finish();
                        cancelFood();
                    }
                })
                .show();
    }

}
