package com.cxyliuyu.www.cookingdiy_android.Activity.listener;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

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

        if (id == R.id.nav_camara) {
            // Handle the camera action

            //测试登录
            LoginBusiness loginBusiness = new LoginBusiness(context);
            loginBusiness.login();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) context.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
