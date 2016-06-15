package com.cxyliuyu.www.cookingdiy_android.Activity.listener;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.cxyliuyu.www.cookingdiy_android.Activity.AddFoodStepActivity;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.NetWorkUtils;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

import java.io.File;
import java.lang.reflect.AccessibleObject;

/**
 * Created by ly on 2016/6/12.
 */
public class AddFoodStepListener implements View.OnClickListener{

    AddFoodStepActivity activity;

    public AddFoodStepListener(AddFoodStepActivity activity){
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addfoodstep_tocameralayout:
                toCamera();
                break;
            case R.id.addfoodstep_ok:
                addStep();
                break;

        }
    }
    private void toCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, 1);
    }
    private  void addStep(){
        //添加做菜步骤
        final File file = activity.getImgFile();
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String result = (String)msg.obj;
                Log.i(ValueUtils.LOGTAG,"上传 reslut = "+result);
            }
        };
        if(file!=null){
            Log.i(ValueUtils.LOGTAG,"文件不为空");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String result =  NetWorkUtils.uploadFile(file, ValueUtils.UPLOADFILE);
                    Message msg = new Message();
                    msg.obj = result;
                    handler.sendMessage(msg);
                }
            }).start();

        }else{
            Log.i(ValueUtils.LOGTAG,"文件为空");
        }
    }
}
