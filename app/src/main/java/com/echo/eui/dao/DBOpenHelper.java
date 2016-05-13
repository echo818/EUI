package com.echo.eui.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库版本控制类(SQLiteOpenHelper-数据库版本控制超类)
 * Created by Echo-z on 2016/5/12/0012.
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DBName = "echo";  //数据库名称
    private static final SQLiteDatabase.CursorFactory DBFactory = null; //游标工厂
    private static final int DBVersion = 2; //数据库版本

    /**
     * 构造方法
     * @param context 上下文
     */
    public DBOpenHelper(Context context) {
        //调用超类的构造方法
        super(context, DBName, DBFactory, DBVersion);
    }

    /**
     * 构造方法
     * @param context 上下文
     * @param version 数据库版本号
     */
    public DBOpenHelper(Context context, int version) {
        super(context, DBName, DBFactory, version);
    }

    /**
     * 第一次创建数据库
     * @param db 数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE person(id INTEGER PRIMARY KEY AUTOINCREMENT,name,pass)";
        db.execSQL(sql);
    }

    /**
     * 数据库版本改变的时候
     * @param db         数据库版本对象
     * @param oldVersion 旧版本号
     * @param newVersion 新版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS person");
        onCreate(db);
    }
}
