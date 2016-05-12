package com.echo.eui.views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.echo.eui.R;

public class ShareActivity extends AppCompatActivity implements View.OnClickListener {
    private Button share, editor;
    private TextView tv;
    private SharedPreferences mShare;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        share = (Button) findViewById(R.id.share_preferences);
        editor = (Button) findViewById(R.id.editor_preferences);

        tv = (TextView) findViewById(R.id.share_tv);

        share.setOnClickListener(this);
        editor.setOnClickListener(this);

        mShare = this.getSharedPreferences("echo", 0);
        mEditor = mShare.edit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_preferences:
                saveData();
                break;
            case R.id.editor_preferences:
                getData();
                break;
        }
    }

    private void getData() {
        String name = mShare.getString("name", "名称为空");
        tv.setText(name);
    }

    private void saveData() {
        mEditor.putString("name", "张三是个好人");
        mEditor.putInt("mode",1);
        mEditor.putBoolean("void",false);
        mEditor.putFloat("float",12.000f);
        mEditor.commit();
        System.out.println("save data successful");
    }
}
