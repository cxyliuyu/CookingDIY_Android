package com.cxyliuyu.www.cookingdiy_android.Activity.listener;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;

import com.cxyliuyu.www.cookingdiy_android.Activity.Fragment.HomeFragment;
import com.cxyliuyu.www.cookingdiy_android.Activity.Fragment.MeFragment;
import com.cxyliuyu.www.cookingdiy_android.Activity.Fragment.MessageFragment;
import com.cxyliuyu.www.cookingdiy_android.Activity.Fragment.TimeFragment;
import com.cxyliuyu.www.cookingdiy_android.Activity.LoginActivity;
import com.cxyliuyu.www.cookingdiy_android.Business.UserBusiness;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

/**
 * Created by ly on 2016/3/8.
 */
public class MyNavigationItemSelectedListener implements OnNavigationItemSelectedListener {

    Activity activity = null;
    FragmentManager  fragmentManager = null;
    FragmentTransaction fragmentTransaction = null;
    HomeFragment homeFragment = null;
    TimeFragment timeFragment = null;
    MessageFragment messageFragment = null;
    MeFragment meFragment = null;

    public MyNavigationItemSelectedListener(Activity activity,HomeFragment homeFragment){
        this.activity = activity;
        this.homeFragment = homeFragment;
        initObjects();
    }
    private void initObjects(){
        fragmentManager = activity.getFragmentManager();
        timeFragment = new TimeFragment();
        messageFragment = new MessageFragment();
        meFragment = new MeFragment();
    }
    private void initHomeFragment(){
        homeFragment = new HomeFragment();
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

    //跳转到相应页面的四个方法
    private void toHomeFragment(){
        Log.i(ValueUtils.LOGTAG,"首页导航栏被选择了");
        fragmentTransaction = fragmentManager.beginTransaction();
        if(homeFragment != null){
            fragmentTransaction.replace(R.id.main_framelayout,homeFragment);
        }else{
            initHomeFragment();
            initObjects();
            fragmentTransaction.replace(R.id.main_framelayout, homeFragment);
        }
        fragmentTransaction.commit();
    }

    private void toTimeFragment(){
        Log.i(ValueUtils.LOGTAG,"记时导航栏被选择了");
        fragmentTransaction = fragmentManager.beginTransaction();
        if(timeFragment != null){
            fragmentTransaction.replace(R.id.main_framelayout,timeFragment);
        }else{
            initHomeFragment();
            initObjects();
        }
        fragmentTransaction.commit();
    }

    private void toMessageFragment(){
        Log.i(ValueUtils.LOGTAG,"消息导航栏被选择了");
        fragmentTransaction = fragmentManager.beginTransaction();
        if(messageFragment != null){
            fragmentTransaction.replace(R.id.main_framelayout,messageFragment);
        }else{
            initHomeFragment();
            initObjects();
        }
        fragmentTransaction.commit();
    }

    private void toMeFragment(){
        Log.i(ValueUtils.LOGTAG,"个人导航栏被点击了");
        fragmentTransaction = fragmentManager.beginTransaction();
        if(UserBusiness.isLogin(activity) == false){
            //用户未登录，跳转到登录
            Intent intent = new Intent(activity,LoginActivity.class);
            activity.startActivity(intent);
        }else{
            //用户已登录，跳转到个人中心
            if(meFragment != null){
                fragmentTransaction.replace(R.id.main_framelayout,meFragment);
            }else{
                initHomeFragment();
                initObjects();
            }
            fragmentTransaction.commit();
        }
    }

}
