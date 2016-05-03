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

/**
 * Created by ly on 2016/5/3.
 */
public class FoodListListviewAdapter extends BaseAdapter{

    JSONArray foodListArray = null;
    Activity activity = null;

    public FoodListListviewAdapter(JSONArray foodListArray,Activity activity){
        this.foodListArray = foodListArray;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        int count = foodListArray.length();
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
            JSONObject jsonObject = foodListArray.getJSONObject(position);
            String foodListName = jsonObject.getString("foodlistname");
            String foodListCount = jsonObject.getString("foodlistcount");
            itemView = inflater.inflate(R.layout.foodlist_listitem, null);
            TextView foodListNameTextView = (TextView)itemView.findViewById(R.id.foodlist_listitem_name);
            TextView foodListCountTextView = (TextView)itemView.findViewById(R.id.foodlist_listitem_count);
            foodListNameTextView.setText(foodListName);
            if(foodListCount == null){
                foodListCountTextView.setText("");
            }else {
                foodListCountTextView.setText(foodListCount);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return itemView;
    }
}
