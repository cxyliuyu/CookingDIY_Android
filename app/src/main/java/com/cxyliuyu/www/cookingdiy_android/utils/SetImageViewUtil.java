package com.cxyliuyu.www.cookingdiy_android.utils;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by ly on 2016/5/2.
 */
public class SetImageViewUtil {
    public static void setImageToImageView(final ImageView imageView ,final String imgURL){
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Log.e(ValueUtils.LOGTAG, "设置图片成功");
                super.handleMessage(msg);
                Bitmap bitmap = (Bitmap)msg.obj;
                imageView.setImageBitmap(bitmap);
            }
        };
        new Thread(new Runnable() {

            @Override
            public void run() {
                Bitmap bitmap = NetWorkUtils.getBitmap(imgURL);
                Message msg = new Message();
                msg.obj = bitmap;
                handler.sendMessage(msg);
            }
        }).start();
    }
}
