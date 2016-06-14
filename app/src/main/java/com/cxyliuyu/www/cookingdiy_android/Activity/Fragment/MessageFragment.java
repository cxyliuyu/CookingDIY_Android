package com.cxyliuyu.www.cookingdiy_android.Activity.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxyliuyu.www.cookingdiy_android.R;

public class MessageFragment extends Fragment {

    private Activity activity;
    private View rootView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    public void initView(LayoutInflater inflater,ViewGroup container){
        activity = this.getActivity();
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
    }


}
