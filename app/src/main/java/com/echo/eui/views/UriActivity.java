package com.echo.eui.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.echo.eui.R;
import com.google.gson.Gson;

public class UriActivity extends AppCompatActivity implements View.OnClickListener {
    private Button web,icon,chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uri);

        web = (Button) findViewById(R.id.uri_web);
        web.setOnClickListener(this);

        icon = (Button) findViewById(R.id.uri_icon);
        icon.setOnClickListener(this);

        chat = (Button) findViewById(R.id.uri_chat);
        chat.setOnClickListener(this);

        parseJson();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uri_web:
                Intent intent = new Intent(this,BrowerActivity.class);
                startActivity(intent);
//                Uri uri = Uri.parse("http://www.baidu.com");
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
                break;
            case R.id.uri_icon:
                installShortCut("安全卫士");
                break;
            case R.id.uri_chat:
                installShortCut("微信");
                break;
        }
    }

    /**
     * 创建快捷图标
     */
    public void installShortCut(String name) {
        //发送广播的意图，要创建快捷图标了
        Intent intent = new Intent();
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        //快捷方式  要包含3个重要的信息 1，名称 2.图标 3.干什么事情
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, R.mipmap.ic_launcher);
        //桌面点击图标对应的意图。
        Intent shortcutIntent = new Intent();
        shortcutIntent.setAction("android.intent.action.MAIN");
        shortcutIntent.addCategory("android.intent.category.LAUNCHER");
        shortcutIntent.setClassName(getPackageName(), ".MainActivity");
        shortcutIntent.putExtra("duplicate", false);
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        sendBroadcast(intent);
    }

    String json = "{\"ARRAYDATA\":{\"USERNO\":\"liaohui\",\"USERNAME\":\"1\",\"USERALLAS\":\"liaohui\",\"TOKEN\":\"UoUrBuDm\"},\"RETMSG\":\"登录成功\",\"RETCODE\":\"00000\",\"RETTRANSDATE\":\"2016/05/13 17:28:34\"}";
    String str = "{'ARRAYDATA':{'USERNO':'liaohui','USERNAME':'1','USERALLAS':'liaohui','TOKEN':'UoUrBuDm'},'RETMSG':'登录成功','RETCODE':'00000','RETTRANSDATE':'2016/05/13 17:28:34'}";
    public void parseJson(){
        Gson gson = new Gson();
        Person person = gson.fromJson(str, Person.class);
        System.out.println(person.ARRAYDATA.getUSERNO());
//        try {
//            JSONObject jsonObject = new JSONObject(str);
//            JSONObject userObject = new JSONObject(jsonObject.getString("ARRAYDATA"));
//            ARRAYDATA arraydata = new ARRAYDATA(userObject.getString("USERNO"),userObject.getString("USERNAME"),userObject.getString("USERALLAS"),userObject.getString("TOKEN"));
//            Person person = new Person(arraydata,jsonObject.getString("RETMSG"),jsonObject.getString("RETCODE"),jsonObject.getString("RETTRANSDATE"));
//            System.out.println(person.ARRAYDATA.getUSERNAME());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    private class Person{
        private ARRAYDATA ARRAYDATA;
        private String RETMSG;
        private String RETCODE;
        private String RETTRANSDATE;

        public Person(ARRAYDATA ARRAYDATA, String RETMSG, String RETCODE, String RETTRANSDATE) {
            this.ARRAYDATA = ARRAYDATA;
            this.RETMSG = RETMSG;
            this.RETCODE = RETCODE;
            this.RETTRANSDATE = RETTRANSDATE;
        }

        public UriActivity.ARRAYDATA getARRAYDATA() {
            return ARRAYDATA;
        }

        public String getRETMSG() {
            return RETMSG;
        }

        public String getRETCODE() {
            return RETCODE;
        }

        public String getRETTRANSDATE() {
            return RETTRANSDATE;
        }
    }

    private class ARRAYDATA{
        private String USERNO;
        private String USERNAME;
        private String USERALLAS;
        private String TOKEN;

        public String getUSERNO() {
            return USERNO;
        }

        public String getUSERNAME() {
            return USERNAME;
        }

        public String getUSERALLAS() {
            return USERALLAS;
        }

        public String getTOKEN() {
            return TOKEN;
        }
    }
}
