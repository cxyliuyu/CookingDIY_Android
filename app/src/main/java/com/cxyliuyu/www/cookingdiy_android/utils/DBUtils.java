package com.cxyliuyu.www.cookingdiy_android.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ly on 2016/3/12.
 */
public class DBUtils {
    //用于封装数据库操作的类
    private MySqliteOpenHelper helper ;
    private SQLiteDatabase db ;

    public DBUtils(Context context){
        helper = new MySqliteOpenHelper(context,"cookdiy.db",null,1);
        db = helper.getWritableDatabase();
    }

    public void add(String tableName,HashMap<String, String> map){
        //添加数据

        Log.d(ValueUtils.LOGTAG,"DBUtils----->add");

        ContentValues contentValues = new ContentValues();
        for(Map.Entry<String,String>e :map.entrySet()){
            contentValues.put(e.getKey(),e.getValue());
        }
        db.insert(tableName,null,contentValues);
    }
    public void delete(String tableName,int id){
        db.execSQL("delete from " + tableName + " where id = " + id);
    }

    public void clean(String tableName){

        if(tableExist(tableName)){
            db.execSQL("delete * from CK_USER");
        }
    }
//    public List<HashMap<String,String>> query(){
//
//    }

    public Cursor queryTheCursor(String tableName){
        Cursor c = db.rawQuery("select * from "+tableName,null);
        return c;
    }

    public boolean tableExist(String tableName){
        //判断一个数据表是否存在
        Boolean result = false;
        if(tableName == null){
            return false;
        }
        Cursor cursor = null;
        try{
            String sql = "select count(*) as c from sqlite_master where type ='table' and name ='"+tableName.trim()+"' ";
            cursor = db.rawQuery(sql,null);
            if(cursor.moveToNext()){
                int count = cursor.getInt(0);
                if(count > 0){
                    result = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

class MySqliteOpenHelper extends SQLiteOpenHelper {

    public MySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS CK_USER" + "(id interger,username varchar,password varchar,truename varchar,userimg varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}