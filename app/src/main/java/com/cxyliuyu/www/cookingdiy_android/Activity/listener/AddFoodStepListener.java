package com.cxyliuyu.www.cookingdiy_android.Activity.listener;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;

import com.cxyliuyu.www.cookingdiy_android.R;

import java.lang.reflect.AccessibleObject;

/**
 * Created by ly on 2016/6/12.
 */
public class AddFoodStepListener implements View.OnClickListener{

    Activity activity;

    public AddFoodStepListener(Activity activity){
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addfoodstep_tocameralayout:
                toCamera();
                break;

        }
    }
    private void toCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, 1);
    }
}
