package com.cxyliuyu.www.cookingdiy_android.Activity.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxyliuyu.www.cookingdiy_android.Activity.listener.MeFragmentNotLoginOnClickListener;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.SharedpreferencesUtil;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

import de.hdodenhof.circleimageview.CircleImageView;
import info.hoang8f.widget.FButton;

public class MeFragment extends Fragment {

    private Activity activity;
    private View rootView;
    private LinearLayout userAll;
    private CircleImageView userImg;
    private TextView userName;
    private TextView userInfo;
    private LinearLayout mysave;
    private LinearLayout upload;
    private LinearLayout myfood;
    private FButton quit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        activity = this.getActivity();
        rootView = inflater.inflate(R.layout.fragment_me, container, false);
        userAll = (LinearLayout)rootView.findViewById(R.id.mefragment_userall);
        userImg = (CircleImageView)rootView.findViewById(R.id.mefragment_userimg);
        userName = (TextView)rootView.findViewById(R.id.mefragment_username);
        userInfo = (TextView)rootView.findViewById(R.id.mefragment_userinfo);
        mysave = (LinearLayout)rootView.findViewById(R.id.mefragment_mysave);
        upload = (LinearLayout)rootView.findViewById(R.id.mefragment_upload);
        myfood = (LinearLayout)rootView.findViewById(R.id.mefragment_myfood);
        quit = (FButton)rootView.findViewById(R.id.meFragment_quit);
        init();

        return rootView;

    }

    public void init(){
        //检查是否已经登录
        Boolean islogin = SharedpreferencesUtil.getBoolean(activity,"ISLOGIN",false);

        if(islogin == true){
            //用户已经登录
            Log.e(ValueUtils.LOGTAG,"用户已经登录");
            quit.setVisibility(View.VISIBLE);

        }else{
            //用户还没登录
            Log.e(ValueUtils.LOGTAG,"用户还未登录");
            quit.setVisibility(View.GONE);
            userAll.setOnClickListener(new MeFragmentNotLoginOnClickListener(activity));
        }
    }
}
