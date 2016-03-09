package com.cxyliuyu.www.cookingdiy_android.Activity.listener;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;

import com.cxyliuyu.www.cookingdiy_android.Activity.LoginActivity;
import com.cxyliuyu.www.cookingdiy_android.Business.LoginBusiness;
import com.cxyliuyu.www.cookingdiy_android.R;

/**
 * Created by ly on 2016/3/8.
 */
public class MyNavigationItemSelectedListener implements OnNavigationItemSelectedListener {

    Activity context;

    public MyNavigationItemSelectedListener(Activity context){
        this.context = context;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_me) {
            // Handle the camera action
            Log.i("cookingdiy","个人中心被点击了");
            //判断用户是否登录
            if(LoginBusiness.isLogin() == false){
                //用户未登录，进入登录页面
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }else{
                //用户已登录，进入个人中心
            }
        }
        DrawerLayout drawer = (DrawerLayout) context.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
