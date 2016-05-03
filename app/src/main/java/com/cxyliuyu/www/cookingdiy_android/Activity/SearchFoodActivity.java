package com.cxyliuyu.www.cookingdiy_android.Activity;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.cxyliuyu.www.cookingdiy_android.Activity.AsyncTask.FoodAsyncTask;
import com.cxyliuyu.www.cookingdiy_android.Business.FoodBusiness;
import com.cxyliuyu.www.cookingdiy_android.R;
import com.cxyliuyu.www.cookingdiy_android.utils.FoodListviewAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class SearchFoodActivity extends AppCompatActivity {

    EditText searchEditText = null;
    Button searchButton = null;
    ImageView backImg = null;
    ListView resultListView = null;
    SweetAlertDialog pDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        searchEditText = (EditText)findViewById(R.id.searchactivity_searchEdit);
        searchButton = (Button)findViewById(R.id.searchactivity_searchbutton);
        backImg = (ImageView)findViewById(R.id.toolbar_back);
        resultListView = (ListView)findViewById(R.id.searchactivity_resultlistview);
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        SearchOnClickListener searchOnClickListener = new SearchOnClickListener();
        backImg.setOnClickListener(searchOnClickListener);
        searchButton.setOnClickListener(searchOnClickListener);



    }

    public void refreshView(JSONObject jsonObject){
        //pDialog.cancel();
        try {
            String code = jsonObject.getString("code");
            if(code.equals("200")){
                //返回菜谱列表成功
                JSONArray foodArray = jsonObject.getJSONArray("list");
                ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
                for(int i=0;i<foodArray.length();i++){
                    JSONObject foodJsonObject = foodArray.getJSONObject(i);
                    String foodId = foodJsonObject.getString("id");
                    String foodName = foodJsonObject.getString("foodname");
                    String foodImg = foodJsonObject.getString("foodimg");
                    String userId = foodJsonObject.getString("userid");
                    String content = foodJsonObject.getString("content");
                    HashMap<String,String>map = new HashMap<String,String>();
                    map.put("foodId",foodId);
                    map.put("foodName",foodName);
                    map.put("foodImg",foodImg);
                    map.put("content",content);
                    map.put("userId",userId);
                    list.add(map);
                }
                FoodListviewAdapter foodListviewAdapter = new FoodListviewAdapter(list,SearchFoodActivity.this);
                resultListView.setAdapter(foodListviewAdapter);
            }else{

            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    class SearchOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.toolbar_back:
                    SearchFoodActivity.this.finish();
                    break;
                case R.id.searchactivity_searchbutton:
                    String key = searchEditText.getText().toString();
                    FoodAsyncTask foodAsyncTask = new FoodAsyncTask("SEARCHFOODS",SearchFoodActivity.this);
                    foodAsyncTask.searchFoodActivity = SearchFoodActivity.this;
                    HashMap<String,String> hashMap = new HashMap<String,String>();
                    hashMap.put("key",key);
                    foodAsyncTask.execute(hashMap);

                    searchEditText.clearFocus();//取消焦点
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(SearchFoodActivity.this
                                            .getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);//关闭输入法

                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("加载中");
                    pDialog.setCancelable(false);
                    //pDialog.show();
                    break;

            }
        }
    }
}
