package com.cxyliuyu.www.cookingdiy_android.Activity.listener;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.cxyliuyu.www.cookingdiy_android.Activity.FoodCommentActivity;
import com.cxyliuyu.www.cookingdiy_android.Activity.FoodDetailActivity;
import com.cxyliuyu.www.cookingdiy_android.Activity.LoginActivity;
import com.cxyliuyu.www.cookingdiy_android.Business.FoodBusiness;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.SharedpreferencesUtil;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ly on 2016/5/30.
 */
public class FoodDetailOnClickListener implements View.OnClickListener{

    final FoodDetailActivity activity;
    final ImageView saveImageView;
    final ImageView commentImageView;

    public FoodDetailOnClickListener(FoodDetailActivity activity,ImageView saveImageView,ImageView commentImageView){
        this.activity = activity;
        this.saveImageView = saveImageView;
        this.commentImageView = commentImageView;
    }

    @Override
    public void onClick(View v) {
        if(activity == null||saveImageView == null||commentImageView == null){
            //空指针了，直接返回避免程序崩溃
            return;
        }
        switch (v.getId()){
            case R.id.fooddetail_saveLinearLayout:
                saveFood();
                break;
            case R.id.fooddetail_commentLinearLayout:
                toComment();
                break;
            default:

        }
    }

    private void saveFood(){
        //收藏按钮被点击的事件
        //收藏按钮被点击的业务
        Boolean islogin = SharedpreferencesUtil.getBoolean(activity, ValueUtils.ISLOGIN, false);
        if(islogin == false){
            //如果用户没有登录，提醒用户登录
            //Log.i(ValueUtils.LOGTAG,"用户还未登录");
            new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("请登录?")
                    .setContentText("您尚未登录!")
                    .setCancelText("取消")
                    .setConfirmText("登录")
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                            Log.i(ValueUtils.LOGTAG, "alert取消按钮被点击");
                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            Log.i(ValueUtils.LOGTAG,"alert确定按钮被点击");
                            Intent intent = new Intent(activity, LoginActivity.class);
                            activity.startActivity(intent);
                        }
                    })
                    .show();

        }else if(islogin == true){
            if(activity.isSaved != null&&activity.foodId != null){
                FoodBusiness foodBusiness = new FoodBusiness(activity);
                if(activity.isSaved == true){
                    //取消收藏
                    foodBusiness.deleteSava(activity.foodId,activity,saveImageView);
                }else if(activity.isSaved == false){
                    //添加收藏
                    foodBusiness.addSave(activity.foodId,activity,saveImageView);
                }
            }
        }
    }

    private void toComment(){
        Boolean islogin = SharedpreferencesUtil.getBoolean(activity, ValueUtils.ISLOGIN, false);
        if(islogin == false){
            //如果用户没有登录，提醒用户登录
            //Log.i(ValueUtils.LOGTAG,"用户还未登录");
            new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("请登录?")
                    .setContentText("您尚未登录!")
                    .setCancelText("取消")
                    .setConfirmText("登录")
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                            Log.i(ValueUtils.LOGTAG, "alert取消按钮被点击");
                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            Log.i(ValueUtils.LOGTAG,"alert确定按钮被点击");
                            Intent intent = new Intent(activity, LoginActivity.class);
                            activity.startActivity(intent);
                        }
                    })
                    .show();

        }else{
            Intent intent = new Intent(activity,FoodCommentActivity.class);
            intent.putExtra("foodId",activity.foodId);
            activity.startActivity(intent);
        }
    }
}
