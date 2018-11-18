package com.example.kapil.beach;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class DetailActivity extends AppCompatActivity {
    ProgressBar progressBar;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.progressbar);
        webView = findViewById(R.id.webview);

        webView.setVisibility(View.INVISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        //Webview ke kuch methods hotein hain jaise ki wenview jab load ho raha hain etc uske liye methods set kartein hain
        //Inn methods ke execute hone pe hum kuch code execute karna chahatein hain toh kar saktein hain
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d("WEB","Page started loading");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                Log.d("WEB","Page finished loading");
            }
        });

        //Ab webview mein URL load ki joki pichle activity se aayi thi
        webView.loadUrl(getIntent().getStringExtra("url"));
    }
}
