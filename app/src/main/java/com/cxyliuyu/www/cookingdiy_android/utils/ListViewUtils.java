package com.cxyliuyu.www.cookingdiy_android.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by ly on 2016/6/4.
 */
public class ListViewUtils {
    //处理ListView的工具类
    public static void setListViewHeightBasedOnChildren(ListView listView,Activity activity){
        ListAdapter adapter = listView.getAdapter();
        if(adapter == null){
            return;
        }
        int totalHeight = 0;
        for(int i=0;i<adapter.getCount();i++){
            View listItem = adapter.getView(i,null,listView);
            listItem.measure(View.MeasureSpec.makeMeasureSpec(activity.getResources()
                    .getDisplayMetrics().widthPixels, View.MeasureSpec.EXACTLY), 0);
                    //此处很重要，不要用measure(0,0);否则item中如果有换行，显示不完全
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+(listView.getDividerHeight()*(adapter.getCount()-1));
        listView.setLayoutParams(params);
    }
}
