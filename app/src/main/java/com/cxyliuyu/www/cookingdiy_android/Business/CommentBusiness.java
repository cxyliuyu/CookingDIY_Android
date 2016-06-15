package com.cxyliuyu.www.cookingdiy_android.Business;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.cxyliuyu.www.cookingdiy_android.Activity.FoodCommentActivity;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.FoodCommentAdapter;
import com.cxyliuyu.www.cookingdiy_android.utils.ListViewUtils;
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
 * Created by ly on 2016/6/7.
 */
public class CommentBusiness {

    public void getComment(String foodId, final FoodCommentActivity foodCommentActivity,int pageNum){
        final ListView listView = (ListView)foodCommentActivity.findViewById(R.id.foodcomment_listview);
        Map<String,String> map = new HashMap<String,String>();
        map.put("foodId",foodId);
        map.put("pageNum",""+pageNum);
        map.put("pageSize","20");
        VolleyRequest.requestPost(foodCommentActivity, ValueUtils.GETCOMMENTBYFOODIDANDPAGE, "COMMENT", new VolleyListener() {
            @Override
            public void onMySuccess(String result) {
                //Log.i(ValueUtils.LOGTAG, result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if(code.equals("200")){
                        JSONArray jsonArray = jsonObject.getJSONArray("list");
                        FoodCommentAdapter adapter = new FoodCommentAdapter(foodCommentActivity,jsonArray,false);
                        listView.setAdapter(adapter);
                        ListViewUtils.setListViewHeightBasedOnChildren(listView,foodCommentActivity);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                Log.i(ValueUtils.LOGTAG,"请求评论列表错误");
            }
        },map);
    }

    public void getConversation(final Activity activity,final ListView listView){
        Boolean islogin = SharedpreferencesUtil.getBoolean(activity, ValueUtils.ISLOGIN, false);
        if(islogin){
            Map<String,String>map = new HashMap<String,String>();
            map.put("userId", SharedpreferencesUtil.getString(activity,ValueUtils.USERID));
            VolleyRequest.requestPost(activity, ValueUtils.GETCONVERSATION, "COMMENT", new VolleyListener() {
                @Override
                public void onMySuccess(String result) {
                    //Log.i(ValueUtils.LOGTAG, result);
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        if (code.equals("200")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("list");
                            FoodCommentAdapter adapter = new FoodCommentAdapter(activity, jsonArray, true);
                            listView.setAdapter(adapter);
                            ListViewUtils.setListViewHeightBasedOnChildren(listView, activity);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onMyError(VolleyError error) {
                    Log.i(ValueUtils.LOGTAG, "请求评论列表错误");
                }
            }, map);
        }
    }

    public void sendComment(final String foodId ,String content,final FoodCommentActivity activity){
        //发送评论
        Map<String,String> map = new HashMap<String,String>();
        map.put("foodId",foodId);
        map.put("content",content);
        map.put("userId",SharedpreferencesUtil.getString(activity,ValueUtils.USERID));
        VolleyRequest.requestPost(activity, ValueUtils.ADDCOMMENT, "COMMENT", new VolleyListener() {
            @Override
            public void onMySuccess(String result) {
                //Log.i(ValueUtils.LOGTAG, result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = jsonObject.getString("code");
                    if (code.equals("200")) {
                        //添加评论成功，刷新评论列表
                        new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("评论成功")
                                .setConfirmText("确定")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();
                                        getComment(foodId, activity, 1);
                                        activity.messageEditText.setText("");
                                    }
                                })
                                .show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                Log.i(ValueUtils.LOGTAG, "请求评论列表错误");
            }
        }, map);
    }
}
