package com.cxyliuyu.www.cookingdiy_android.Activity.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.baoyz.widget.PullRefreshLayout;
import com.cxyliuyu.www.cookingdiy_android.Business.CommentBusiness;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

public class MessageFragment extends Fragment {

    private Activity activity;
    private View rootView = null;
    ListView commentListView = null;
    private PullRefreshLayout pullRefreshLayout = null;//下拉刷新组件

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initView(inflater,container);

        return rootView;

    }

    public void initView(LayoutInflater inflater,ViewGroup container){
        activity = this.getActivity();
        rootView = inflater.inflate(R.layout.fragment_message, container, false);
        commentListView = (ListView)rootView.findViewById(R.id.messagefragment_messagelistview);
        pullRefreshLayout = (PullRefreshLayout)rootView.findViewById(R.id.messagefragment_pullrefreshlayout);
        CommentBusiness commentBusiness = new CommentBusiness();
        commentBusiness.getConversation(activity,commentListView);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh
                Log.i(ValueUtils.LOGTAG, "下拉刷新");
                stopRefreshing();
            }
        });
    }
    private void stopRefreshing(){
        pullRefreshLayout.setRefreshing(false);
    }


}
