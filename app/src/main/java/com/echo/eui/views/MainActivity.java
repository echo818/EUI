package com.echo.eui.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.echo.eui.R;
import com.echo.eui.service.CountService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button start,stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start_service);
        stop = (Button) findViewById(R.id.stop_service);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, CountService.class);
        switch (v.getId()) {
            case R.id.start_service:
                startService(intent);
                break;
            case R.id.stop_service:
                stopService(intent);
                break;
        }
    }
}
