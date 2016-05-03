package com.cxyliuyu.www.cookingdiy_android.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cxyliuyu.www.cookingdiy_android.R;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by ly on 2016/5/3.
 */
public class FoodStepListViewAdapter extends BaseAdapter {

    JSONArray foodStepArray = null;
    Activity activity = null;

    public FoodStepListViewAdapter(JSONArray foodStepArray,Activity activity){
        this.foodStepArray = foodStepArray;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        int count = foodStepArray.length();
        return count;
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

        try{
            JSONObject jsonObject = foodStepArray.getJSONObject(position);
            String stepImg = jsonObject.getString("stepimg");
            String stepTxt = jsonObject.getString("steptxt");
            itemView = inflater.inflate(R.layout.foodstep_listitem, null);
            ImageView stepImageView = (ImageView)itemView.findViewById(R.id.foodstep_listitem_img);
            TextView stepTextView = (TextView)itemView.findViewById(R.id.foodstep_listitem_txt);
            SetImageViewUtil.setImageToImageView(stepImageView, stepImg);
            if(stepTxt == null){
                stepTextView.setText("");
            }else {
                stepTextView.setText(stepTxt);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return itemView;
    }
}
