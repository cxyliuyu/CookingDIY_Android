package com.cxyliuyu.www.cookingdiy_android.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cxyliuyu.www.cookingdiy_android.Activity.listener.AddFoodStepListener;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class AddFoodStepActivity extends AppCompatActivity {

    LinearLayout toCameraLayout = null;
    ImageView imageView = null;
    FButton addFoodStepButton = null;
    File imgFile = null;
    Bitmap bitmap = null;
    public EditText stepEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_step);
        ImageView backimg = (ImageView)findViewById(R.id.toolbar_back);
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFoodStepActivity.this.finish();
            }
        });
        toCameraLayout = (LinearLayout)findViewById(R.id.addfoodstep_tocameralayout);
        AddFoodStepListener addFoodStepListener = new AddFoodStepListener(AddFoodStepActivity.this);
        toCameraLayout.setOnClickListener(addFoodStepListener);
        imageView = (ImageView)findViewById(R.id.addfoodstep_foodimg);
        addFoodStepButton = (FButton)findViewById(R.id.addfoodstep_ok);
        stepEditText = (EditText)findViewById(R.id.addfoodstep_stepcontent);
        addFoodStepButton.setOnClickListener(addFoodStepListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                Log.i(ValueUtils.LOGTAG, "SD卡不可用");
                return;
            }else{
                String name = new DateFormat().format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".jpg";
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
                FileOutputStream b = null;
                File file = new File("/sdcard/Zuofanme/");
                if(!file.exists()){
                    file.mkdirs();// 创建文件夹
                }
                String fileName = "/sdcard/Zuofanme/"+name;
                try {
                    b = new FileOutputStream(fileName);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        b.flush();
                        b.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(bitmap != null){
                    toCameraLayout.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageBitmap(bitmap);
                    this.bitmap = bitmap;
                    imgFile = new File(fileName);
                }

            }
//            String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
//            Bundle bundle = data.getExtras();
//            Bitmap bitmap = (Bitmap) bundle.get("data");
//            if(bitmap != null){
//                toCameraLayout.setVisibility(View.GONE);
//                imageView.setVisibility(View.VISIBLE);
//                imageView.setImageBitmap(bitmap);
//                this.bitmap = bitmap;
//            }
        }
    }

    public File getImgFile(){
        return imgFile;
    }
}
