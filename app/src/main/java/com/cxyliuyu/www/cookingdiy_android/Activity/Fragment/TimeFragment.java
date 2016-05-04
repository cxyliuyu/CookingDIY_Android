package com.cxyliuyu.www.cookingdiy_android.Activity.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.util.Timer;
import java.util.TimerTask;

import at.markushi.ui.CircleButton;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ly on 2016/3/26.
 */
public class TimeFragment extends Fragment{

    private View rootView = null;
    DiscreteSeekBar discreteSeekBar = null;
    CircleButton stopButton = null;
    CircleButton startButton = null;
    CircleButton pauseButton = null;
    LinearLayout stopLayout = null;
    LinearLayout startLayout = null;
    TextView showTimeTextView = null;
    LinearLayout seekTimeLayout = null;
    Timer timer = null;

    int minute = 0;
    int second = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_time,container,false);
        discreteSeekBar = (DiscreteSeekBar)rootView.findViewById(R.id.timefragment_seekbar);
        startButton = (CircleButton)rootView.findViewById(R.id.timefragment_startbutton);
        stopButton = (CircleButton)rootView.findViewById(R.id.timefragment_stopbutton);
        pauseButton = (CircleButton)rootView.findViewById(R.id.timefragment_pausebutton);
        stopLayout = (LinearLayout)rootView.findViewById(R.id.timefragment_stoplayout);
        startLayout = (LinearLayout)rootView.findViewById(R.id.timefragment_startlayout);
        showTimeTextView = (TextView)rootView.findViewById(R.id.timefragment_showtimetext);
        seekTimeLayout = (LinearLayout)rootView.findViewById(R.id.timefragment_seektimelayout);
        //给各个元素设置监听器
        TimeFragmentOnClickListener timeFragmentOnClickListener = new TimeFragmentOnClickListener();
        startButton.setOnClickListener(timeFragmentOnClickListener);
        stopButton.setOnClickListener(timeFragmentOnClickListener);
        pauseButton.setOnClickListener(timeFragmentOnClickListener);

        discreteSeekBar.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                //Log.i(ValueUtils.LOGTAG, "时间选择器被修改了，值 = " + value);
                //监听选择器值得变化
                minute = value;
                second = minute * 60;
                return value;
            }
        });
        return rootView;
    }
    class TimeFragmentOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.timefragment_startbutton:
                    startTime();
                    break;
                case R.id.timefragment_pausebutton:
                    pauseTime();
                    break;
                case R.id.timefragment_stopbutton:
                    stopTime();
                    break;
            }
        }
    }
    final Handler handler= new Handler(){

        @Override
        public void handleMessage(Message msg) {
            //接收到timer发送的信号后，更新UI
            String timeString = "";
            int curentMinute = second / 60;
            if(curentMinute < 10){
                timeString = timeString +"0"+curentMinute+":";
            }else{
                timeString = timeString +curentMinute +":";
            }
            int currentSecond =  second % 60;
            if(currentSecond < 10){
                timeString = timeString + "0"+currentSecond;
            }else{
                timeString = timeString + currentSecond;
            }
            Log.i(ValueUtils.LOGTAG,timeString);
            showTimeTextView.setText(timeString);
            if(second == 0){
                //倒计时结束
                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("时间到啦!")
                        .setContentText("注意掌握火候哦!")
                        .show();
                stopTime();
            }
            second--;
            Log.i(ValueUtils.LOGTAG,"second = "+second);
        }
    };
    private void startTime(){
        //开始计时
        startLayout.setVisibility(View.GONE);
        stopLayout.setVisibility(View.VISIBLE);
        pauseButton.setEnabled(true);
        seekTimeLayout.setVisibility(View.GONE);
        showTimeTextView.setVisibility(View.VISIBLE);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //Log.i(ValueUtils.LOGTAG, "哈哈哈");
                Message message = new Message();
                handler.sendMessage(message);
            }
        },0,1000);

    }
    private void stopTime(){
        //停止计时
        pauseButton.setEnabled(false);
        stopLayout.setVisibility(View.GONE);
        startLayout.setVisibility(View.VISIBLE);
        seekTimeLayout.setVisibility(View.VISIBLE);
        showTimeTextView.setVisibility(View.GONE);
        showTimeTextView.setText("00:00");
        second = minute * 60;
        timer.cancel();
    }
    private void pauseTime(){
        //暂停计时
        stopLayout.setVisibility(View.GONE);
        startLayout.setVisibility(View.VISIBLE);
        pauseButton.setEnabled(false);
        timer.cancel();
    }
}
