package com.example.hades.asm_nc;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class XemTinActivity extends AppCompatActivity {
        WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_tin);
        webView=findViewById(R.id.webview);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        final String link=getIntent().getExtras().getString("link");
        Toast.makeText(this, ""+link, Toast.LENGTH_SHORT).show();
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                webView.loadUrl(link);
                return super.shouldOverrideUrlLoading(view, request);

            }
        });
        webView.loadUrl(link);

    }
}
