package com.cxyliuyu.www.cookingdiy_android.Activity.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cxyliuyu.www.cookingdiy_android.Activity.listener.MeFragmentOnClickListener;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.NetWorkUtils;
import com.cxyliuyu.www.cookingdiy_android.utils.SharedpreferencesUtil;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

import de.hdodenhof.circleimageview.CircleImageView;
import info.hoang8f.widget.FButton;

public class MeFragment extends Fragment {

    private Activity activity = null;
    private View rootView = null;
    private LinearLayout userAll = null;
    private CircleImageView userImg = null;
    private TextView userName = null;
    private TextView userInfo = null;
    private LinearLayout mysave = null;
    private LinearLayout upload = null;
    private LinearLayout myfood = null;
    private FButton quit = null;
    private MeFragmentReceiver meFragmentReceiver = null;
    private MeFragmentOnClickListener meFragmentOnClickListener= null;

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
        meFragmentOnClickListener = new MeFragmentOnClickListener(activity);

        init();

        return rootView;

    }

    public void init(){
        //检查是否已经登录
        Boolean islogin = SharedpreferencesUtil.getBoolean(activity,ValueUtils.ISLOGIN,false);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ValueUtils.refreshmefragment);
        meFragmentReceiver = new MeFragmentReceiver();
        activity.registerReceiver(meFragmentReceiver,intentFilter);
        refreshUI();//初始化页面
        //给页面控件设置监听事件
        userAll.setOnClickListener(meFragmentOnClickListener);
        mysave.setOnClickListener(meFragmentOnClickListener);
        upload.setOnClickListener(meFragmentOnClickListener);
        myfood.setOnClickListener(meFragmentOnClickListener);
        quit.setOnClickListener(meFragmentOnClickListener);
        myfood.setOnClickListener(meFragmentOnClickListener);
    }

    public void refreshUI(){
        //刷新页面
        Boolean isLogin = SharedpreferencesUtil.getBoolean(activity,ValueUtils.ISLOGIN,false);
        if(isLogin == true){
            //用户已经登录
            quit.setVisibility(View.VISIBLE);
            String userTrueName = SharedpreferencesUtil.getString(activity,ValueUtils.USERTRUENAME);
            final String userImgUrl = SharedpreferencesUtil.getString(activity,ValueUtils.USERIMG);
            userName.setText(userTrueName);
            final Handler myHandler = new Handler() {
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Bitmap userImgBitmap = (Bitmap)msg.obj;
                    userImg.setImageBitmap(userImgBitmap);
                }
            };
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap userImgBitmap = NetWorkUtils.getBitmap(userImgUrl);
                    Message message = new Message();
                    message.obj = userImgBitmap;
                    myHandler.sendMessage(message);
                }
            }).start();
        }else{
            //用户没有登录
            quit.setVisibility(View.GONE);
            userName.setText("请点击登录");
            userImg.setImageDrawable(activity.getResources().getDrawable(R.drawable.userdefaultimg));
        }
    }

    class MeFragmentReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(ValueUtils.LOGTAG,"接收到了广播");
            String action = intent.getAction();
            if(action.equals(ValueUtils.refreshmefragment)){
                //接收到刷新页面的广播
                refreshUI();
            }
        }
    }

}
