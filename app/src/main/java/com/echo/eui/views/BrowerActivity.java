package com.echo.eui.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.echo.eui.R;

public class BrowerActivity extends Activity {
    private WebView webView;
    private ImageView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brower);

        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.baidu.com");
        webView.setWebViewClient(new MyWebViewClient());

        close = (ImageView) findViewById(R.id.web_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //web视图客户端
    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverviewUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
