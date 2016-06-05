package com.cxyliuyu.www.cookingdiy_android.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.cxyliuyu.www.cookingdiy_android.Activity.FoodDetailActivity;
import com.cxyliuyu.www.cookingdiy_android.MyApplication;
import com.cxyliuyu.www.cookingdiy_android.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by ly on 2016/5/2.
 */
public class FoodListviewAdapter extends BaseAdapter{
    ArrayList<HashMap<String,String>> list = null;
    Activity activity = null;
    public FoodListviewAdapter(ArrayList<HashMap<String,String>> list,Activity activity){
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = null;
        try{
            LayoutInflater inflater = activity.getLayoutInflater();
            HashMap<String,String> hashMap = list.get(position);
            final String foodId = hashMap.get("foodId");
            String foodName = hashMap.get("foodName");
            String foodImg = hashMap.get("foodImg");
            String content = hashMap.get("content");
            String userId = hashMap.get("userId");
            itemView = inflater.inflate(R.layout.food_listitem, null);
            ImageView listItemFoodImage = (ImageView)itemView.findViewById(R.id.food_listitem_foodimg);
            TextView listItemFoodName = (TextView)itemView.findViewById(R.id.food_listitem_foodname);
            TextView listItemFoodContent = (TextView)itemView.findViewById(R.id.food_listitem_foodcontent);
            //SetImageViewUtil.setImageToImageView(listItemFoodImage,foodImg);
            listItemFoodContent.setText(content);
            listItemFoodName.setText(foodName);
            //设置图片
            ImageLoader loader = new ImageLoader(MyApplication.getVolleyRequestQueue(),new BitmapCache());
            ImageLoader.ImageListener listener = ImageLoader
                    .getImageListener(listItemFoodImage,R.drawable.food_default,R.drawable.food_default);
            loader.get(foodImg,listener);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.i(ValueUtils.LOGTAG, "食物id是"+foodId);
                    Intent intent = new Intent(activity, FoodDetailActivity.class);
                    intent.putExtra("foodId",foodId);
                    activity.startActivity(intent);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        return itemView;
    }
}
