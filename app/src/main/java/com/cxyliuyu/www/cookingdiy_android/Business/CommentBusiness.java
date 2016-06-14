package com.cxyliuyu.www.cookingdiy_android.Business;

import android.util.Log;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.cxyliuyu.www.cookingdiy_android.Activity.FoodCommentActivity;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;
import com.cxyliuyu.www.cookingdiy_android.utils.VolleyListener;
import com.cxyliuyu.www.cookingdiy_android.utils.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ly on 2016/6/7.
 */
public class CommentBusiness {

    public void getComment(String foodId,FoodCommentActivity foodCommentActivity,int pageNum){
        ListView listView = (ListView)foodCommentActivity.findViewById(R.id.foodcomment_listview);
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
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject commentJsonObject = jsonArray.getJSONObject(i);
                            String content = commentJsonObject.getString("content");
                            String time = commentJsonObject.getString("time");
                            String trueName = commentJsonObject.getString("truename");
                            String userImg = commentJsonObject.getString("userimg");
                            Log.i(ValueUtils.LOGTAG,trueName+userImg+time+content);
                        }
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
}
