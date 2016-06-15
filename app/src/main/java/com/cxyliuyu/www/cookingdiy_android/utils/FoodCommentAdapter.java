package com.cxyliuyu.www.cookingdiy_android.utils;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.cxyliuyu.www.cookingdiy_android.Activity.FoodDetailActivity;
import com.cxyliuyu.www.cookingdiy_android.MyApplication;
import com.cxyliuyu.www.cookingdiy_android.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ly on 2016/6/14.
 * 评论列表的适配器
 */
public class FoodCommentAdapter extends BaseAdapter{

    Activity activity = null;
    JSONArray commentArray = null;
    Boolean myIsClickable = null; //是否能被点击
    String foodId = null;
    public FoodCommentAdapter(Activity activity,JSONArray commentArray,Boolean myIsClickable){
        this.activity = activity;
        this.commentArray = commentArray;
        this.myIsClickable = myIsClickable;
    }

    @Override
    public int getCount() {
        return commentArray.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = null;
        LayoutInflater inflater = activity.getLayoutInflater();
        itemView = inflater.inflate(R.layout.message_listitem, null);
        CircleImageView userImgView = (CircleImageView)itemView.findViewById(R.id.messagelistitem_userimg);
        TextView trueNameView = (TextView)itemView.findViewById(R.id.messagelistitem_username);
        TextView contentView = (TextView)itemView.findViewById(R.id.messagelistitem_content);
        TextView timeView = (TextView)itemView.findViewById(R.id.messagelistitem_time);
        try{
            JSONObject commentJsonObject = commentArray.getJSONObject(position);
            String content = commentJsonObject.getString("content");
            String time = commentJsonObject.getString("time");
            String trueName = commentJsonObject.getString("truename");
            String userImg = commentJsonObject.getString("userimg");
            foodId = commentJsonObject.getString("foodid");
            contentView.setText(content);
            trueNameView.setText(trueName);
            timeView.setText(getTime(time));
            //加载图片
            ImageLoader loader = new ImageLoader(MyApplication.getVolleyRequestQueue(),new BitmapCache());
            ImageLoader.ImageListener listener = ImageLoader
                    .getImageListener(userImgView, R.drawable.food_default, R.drawable.food_default);
            loader.get(userImg, listener);

        }catch (Exception e){
            e.printStackTrace();
        }
        if(myIsClickable == true){
            //item 能被点击
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(foodId != null){
                        Intent intent = new Intent(activity, FoodDetailActivity.class);
                        intent.putExtra("foodId",foodId);
                        activity.startActivity(intent);
                    }
                }
            });
        }else{
            //item不能被点击
            //itemView.setClickable(false);
        }
        return itemView;
    }
    public String getTime(String time){
        //根据时间戳获取时间
        long longTime = Long.parseLong(time+"000");
        Log.i(ValueUtils.LOGTAG,longTime+"");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        Date date = new Date(longTime);
        String strTime = simpleDateFormat.format(date);
        Log.i(ValueUtils.LOGTAG,strTime+"");
        return strTime;
    }
}
