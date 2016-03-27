package com.cxyliuyu.www.cookingdiy_android.Activity.listener;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;

import com.cxyliuyu.www.cookingdiy_android.Activity.LoginActivity;
import com.cxyliuyu.www.cookingdiy_android.Business.UserBusiness;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

/**
 * Created by ly on 2016/3/8.
 */
public class MyNavigationItemSelectedListener implements OnNavigationItemSelectedListener {

    Activity activity;

    public MyNavigationItemSelectedListener(Activity activity){
        this.activity = activity;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:
                toHomeFragment();
                break;
            case R.id.nav_time:
                toTimeFragment();
                break;
            case R.id.nav_message:
                toMessageFragment();
                break;
            case R.id.nav_me:
                toMeFragment();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void toHomeFragment(){
        Log.i(ValueUtils.LOGTAG,"首页导航栏被选择了");
    }

    private void toTimeFragment(){
        Log.i(ValueUtils.LOGTAG,"记时导航栏被选择了");

    }

    private void toMessageFragment(){
        Log.i(ValueUtils.LOGTAG,"消息导航栏被选择了");

    }

    private void toMeFragment(){
        Log.i(ValueUtils.LOGTAG,"个人导航栏被点击了");
        if(UserBusiness.isLogin(activity) == false){
            //用户未登录，跳转到登录
            Intent intent = new Intent(activity,LoginActivity.class);
            activity.startActivity(intent);
        }else{
            //用户已登录，跳转到个人中心
        }
    }

}
