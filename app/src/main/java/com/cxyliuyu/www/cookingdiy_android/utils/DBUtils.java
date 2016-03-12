package com.cxyliuyu.www.cookingdiy_android.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ly on 2016/3/12.
 */
public class DBUtils {
    //用于封装数据库操作的类
    public void add(String tableName,Object object){

    }
}

class MySqliteOpenHelper extends SQLiteOpenHelper {

    public MySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS CK_USER" + "id interger,username varchar,password varchar,truename varchar,userimg varchar");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}