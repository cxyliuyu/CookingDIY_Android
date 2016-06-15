package com.cxyliuyu.www.cookingdiy_android.Activity;

/**
 * Created by ly on 2016/6/14.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cxyliuyu.www.cookingdiy_android.Business.CommentBusiness;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.ValueUtils;

public class FoodCommentActivity extends AppCompatActivity {
    int pageNum = 1;
    public EditText messageEditText = null;
    Button sendMessageButton = null;
    TextView showMoreText = null;
    String foodId= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_comment);

        ImageView backimg = (ImageView)findViewById(R.id.toolbar_back);
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodCommentActivity.this.finish();
            }
        });
        TextView toobarTitleTextView = (TextView)findViewById(R.id.toolbar_title);
        toobarTitleTextView.setText("评论");
        foodId = getIntent().getStringExtra("foodId");
        messageEditText = (EditText)findViewById(R.id.foodcomment_messageedittext);
        sendMessageButton = (Button)findViewById(R.id.foodcomment_sendmessagebutton);
        showMoreText = (TextView)findViewById(R.id.foodlist_showmore);

        FoodCommentOnClickListener foodCommentOnClickListener = new FoodCommentOnClickListener();
        sendMessageButton.setOnClickListener(foodCommentOnClickListener);
        CommentBusiness commentBusiness = new CommentBusiness();
        commentBusiness.getComment(foodId, FoodCommentActivity.this, pageNum);
    }
    class FoodCommentOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.i(ValueUtils.LOGTAG,"按钮被点击了");
            CommentBusiness commentBusiness = new CommentBusiness();
            commentBusiness.sendComment(foodId, messageEditText.getText().toString(), FoodCommentActivity.this);

        }
    }

}