package com.bintracking.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.gamepoint.R;

public class DetailActivity extends AppCompatActivity {

    ProgressBar progressBar;
    WebView webView;
    //TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        progressBar = findViewById(R.id.progressBar);
        webView = findViewById(R.id.detailview);
       // textView = findViewById(R.id.textUrl);
        //getSupportActionBar().setTitle("Game Tournament");
        getSupportActionBar().hide();
        //apply swipe layout

        webView.setVisibility(View.INVISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
           /* @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                textView.setText(url);
            }
*/
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                //textView.setText(webView.getTitle());

            }
        });
        webView.loadUrl(getIntent().getStringExtra("url"));
    }
}
