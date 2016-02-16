package com.akrmall.akrmall;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.KeyEvent;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    WebView web = null;
    ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = (WebView)findViewById(R.id.webView);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);

        if(web != null)
        {
            web.setWebViewClient(new WebViewClient()
            {
                @Override
                public void onPageFinished(WebView view,String url)
                {
                    dialog.dismiss();
                }
            });

            loadUrl("http://www.akrmall.com");
        }
    }

    public void loadUrl(String url)
    {
        if(web != null)
        {
            web.loadUrl(url);
            dialog = ProgressDialog.show(this,null,"亚天韩国城正在加载");
            web.reload();
        }
    }

    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
            web.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("确认退出吗？")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("是", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();
    }
}
