package com.cxyliuyu.www.cookingdiy_android.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cxyliuyu.www.cookingdiy_android.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ly on 2016/6/14.
 */
public class FoodCommentAdapter extends BaseAdapter{

    Activity activity = null;
    JSONArray commentArray = null;
    public FoodCommentAdapter(Activity activity,JSONArray commentArray){
        this.activity = activity;
        this.commentArray = commentArray;
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
        TextView trueNameView = (TextView)itemView.findViewById(R.id.messagelistitem_userimg);
        TextView contentView = (TextView)itemView.findViewById(R.id.messagelistitem_content);
        TextView timeView = (TextView)itemView.findViewById(R.id.messagelistitem_time);
        try{
            JSONObject commentJsonObject = commentArray.getJSONObject(position);
            String content = commentJsonObject.getString("content");
            String time = commentJsonObject.getString("time");
            String trueName = commentJsonObject.getString("truename");
            String userImg = commentJsonObject.getString("userimg");
            contentView.setText(content);
            trueNameView.setText(trueName);
            timeView.setText(getTime(time));

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
    public String getTime(String time){
        //根据时间戳获取时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        Date date;
        String strTime ="";
        try{
            date = simpleDateFormat.parse(time);
            strTime = date.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return strTime;
    }
}
