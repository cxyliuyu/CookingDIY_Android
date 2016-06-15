package com.cxyliuyu.www.cookingdiy_android.Activity.listener;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import com.cxyliuyu.www.cookingdiy_android.Activity.AddFoodStepActivity;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.AddFoodUtil;
import com.cxyliuyu.www.cookingdiy_android.utils.NetWorkUtils;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;
import org.json.JSONObject;
import java.io.File;

/**
 * Created by ly on 2016/6/12.
 */
public class AddFoodStepListener implements View.OnClickListener{

    AddFoodStepActivity activity;
    String imgUrl = null;
    String stepContext = null;

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

        final String content = activity.stepEditText.getText().toString();
        final File file = activity.getImgFile();
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String result = (String)msg.obj;
                stepContext = activity.stepEditText.getText().toString();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    imgUrl = ValueUtils.UPLOADDIR+jsonObject.getString("url");
                    Log.i(ValueUtils.LOGTAG,"上传 imgUrl = "+imgUrl);
                    AddFoodUtil.addStep(content,imgUrl,activity);
                }catch (Exception e){
                    e.printStackTrace();
                }
                activity.finish();

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
