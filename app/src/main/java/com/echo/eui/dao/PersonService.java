package com.echo.eui.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.echo.eui.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作实体类
 * Created by Echo-z on 2016/5/13/0013.
 */
public class PersonService {
    private DBOpenHelper helper; //数据库版本控制对象

    /**
     * 构造方法
     * @param context 上下文
     */
    public PersonService(Context context) {
        //实例化数据库版本控制对象
        helper = new DBOpenHelper(context);
    }

    /**
     * 构造方法
     * @param context 上下文
     * @param version 数据库版本号
     */
    public PersonService(Context context,int version) {
        helper = new DBOpenHelper(context,version);
    }

    /**
     * 保存数据
     * @param p 数据对象
     */
    public void save(Person p){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "INSERT INTO person (name,pass) VALUES (?,?)";
        db.execSQL(sql,new Object[]{p.getName(),p.getPass()});
    }

    /**
     * 更新数据
     * @param p 数据对象
     */
    public void update(Person p){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "UPDATE person SET name=?,pass=? WHERE id=?";
        db.execSQL(sql,new Object[]{p.getName(),p.getPass(),p.getId()});
    }

    /**
     * 删除一条数据
     * @param id 数据id
     */
    public void delete(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "DELETE FROM person WHERE id="+id;
        db.execSQL(sql);
    }

    /**
     * 删除多条数据
     * @param ids 数据id数组
     */
    public void deleteByIds(Integer... ids){
        if (ids.length>0){
            StringBuilder builder = new StringBuilder();
            for (int i=0;i<ids.length;i++){
                builder.append('?').append(',');
            }
            builder.deleteCharAt(builder.length()-1);
            SQLiteDatabase db = helper.getWritableDatabase();
            String sql = "DELETE FROM person WHERE id IN(" + builder + ")";
            db.execSQL(sql,ids);
        }
    }

    /**
     * 查询一条数据
     * @param id 数据id
     * @return Person对象数据
     */
    public Person find(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "SELECT * FROM person WHERE id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        Person person = null;
        if (cursor.moveToNext()){
            person = new Person(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("pass")));
        }
        return person;
    }

    /**
     * 查询多条数据
     * @param ids 数据id数组
     * @return Person数据集合
     */
    public List<Person> findByIds(Integer... ids){
        List<Person> list = new ArrayList<>();
        if (ids.length>0){
            StringBuilder builder = new StringBuilder();
            for (int id:ids){
                builder.append(id).append(',');
            }
            builder.deleteCharAt(builder.length()-1);
            SQLiteDatabase db = helper.getWritableDatabase();
            String sql = "SELECT * FROM person WHERE id IN(" + builder + ")";
            Cursor cursor = db.rawQuery(sql,null);
            while (cursor.moveToNext()){
                Person person = new Person(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("pass")));
                list.add(person);
            }
        }
        return list;
    }

    /**
     * 查询所有数据
     * @return Person所有数据集合
     */
    public List<Person> select(){
        List<Person> list = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "SELECT * FROM person";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            Person person = new Person(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("pass")));
            list.add(person);
        }
        return list;
    }

    /**
     * 分页查询
     * @param page 页码
     * @param num 每页数
     * @return Person数据集合
     */
    public List<Person> pageFind(int page,int num){
        List<Person> list = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "SELECT * FROM person LIMIT ?,?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(page), String.valueOf(num)});
        while (cursor.moveToNext()){
            Person person = new Person(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("pass")));
            list.add(person);
        }
        return list;
    }
}
