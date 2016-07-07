package com.cxyliuyu.www.cookingdiy_android.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ly on 2016/6/15.
 */
public class AddFoodUtil {
    //添加菜品的工具的封装
    public static void addStep(String steptxt,String stepimg,Context context)throws JSONException{
        JSONObject jsonObject = getJSONObject(context);
        JSONArray foodStepArray = null;
        try{
            foodStepArray = jsonObject.getJSONArray("foodsteps");
            int order = foodStepArray.length()+1;
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("steptxt",steptxt);
            jsonObject1.put("stepimg",stepimg);
            jsonObject1.put("steporder",""+order);
            foodStepArray.put(jsonObject1);
            jsonObject.put("foodsteps",foodStepArray);
        }catch (JSONException e){
            foodStepArray = new JSONArray();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("steptxt",steptxt);
            jsonObject1.put("stepimg",stepimg);
            jsonObject1.put("steporder","1");
            foodStepArray.put(jsonObject1);
            jsonObject.put("foodsteps",foodStepArray);
        }
        jsonObject.put("foodimg", stepimg);//将最后一个添加的图片设置为菜谱的图片
        String foodJSONString = jsonObject.toString();
        Log.i(ValueUtils.LOGTAG,"foodJSONString = "+foodJSONString);
        SharedpreferencesUtil.setString(context,"foodJSONString",foodJSONString);

    }

    public static void addList(String listName,String listCount,Context context)throws JSONException{
        JSONObject jsonObject = getJSONObject(context);
        JSONArray foodlistArray = null;
        try {
            foodlistArray = jsonObject.getJSONArray("foodlist");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("foodlistname",listName);
            jsonObject1.put("foodlistcount",listCount);
            foodlistArray.put(jsonObject1);
            jsonObject.put("foodlist",foodlistArray);
        }catch (JSONException e){
            foodlistArray = new JSONArray();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("foodlistname",listName);
            jsonObject1.put("foodlistcount",listCount);
            foodlistArray.put(jsonObject1);
            jsonObject.put("foodlist",foodlistArray);
        }
        String foodJSONString = jsonObject.toString();
        Log.i(ValueUtils.LOGTAG,"foodJSONString = "+foodJSONString);
        SharedpreferencesUtil.setString(context,"foodJSONString",foodJSONString);
    }

    public static void addFoodName(String foodName,Context context)throws JSONException{
        JSONObject jsonObject = getJSONObject(context);
        jsonObject.put("foodName",foodName);
        String foodJSONString = jsonObject.toString();
        Log.i(ValueUtils.LOGTAG,"foodJSONString = "+foodJSONString);
        SharedpreferencesUtil.setString(context,"foodJSONString",foodJSONString);
    }

    public static void addContent(String content,Context context)throws JSONException{
        JSONObject jsonObject = getJSONObject(context);
        jsonObject.put("content",content);
        String foodJSONString = jsonObject.toString();
        Log.i(ValueUtils.LOGTAG,"foodJSONString = "+foodJSONString);
        SharedpreferencesUtil.setString(context, "foodJSONString", foodJSONString);
    }

    public static JSONObject getJSONObject(Context context)throws JSONException{
        JSONObject foodJSONObject = null;
        String foodJSONString = SharedpreferencesUtil.getString(context,"foodJSONString");
        Log.i(ValueUtils.LOGTAG,"foodString = "+foodJSONString);
        if(foodJSONString == null||foodJSONString.equals("")){
            Log.i(ValueUtils.LOGTAG,"测试");

            foodJSONObject = new JSONObject();
        }else{
            foodJSONObject = new JSONObject(foodJSONString);
        }
        return foodJSONObject;
    }
}
