package com.ident.validator.core.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;


import com.ident.validator.core.R;

import java.util.Map;

public class CustomWebView extends FrameLayout {

    private ProgressBar mProgressBar;
    private WebView mWebView;

    public CustomWebView(Context context) {
        super(context);
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.custom_webview_layout, this);
        mWebView = (WebView) findViewById(R.id.webView);
        mProgressBar = (ProgressBar) findViewById(R.id.webView_progress_bar);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAppCacheEnabled(true); // 设置允许缓存
        settings.setDatabaseEnabled(true); // 设置允许使用localstore
        settings.setAllowFileAccess(true);
        settings.setDomStorageEnabled(true);
        // mWebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        // String appCachePath =
        // mWebView.getContext().getApplicationContext().getCacheDir().getAbsolutePath();
        // mWebView.getSettings().setAppCachePath(appCachePath);
        // String databasePath =
        // mWebView.getContext().getApplicationContext().getDir("databases",
        // Context.MODE_PRIVATE).getPath();
        // mWebView.getSettings().setDatabasePath(databasePath);

        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        // mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//		settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        mWebView.setWebViewClient(new DefaultWebViewClient());
        mWebView.setWebChromeClient(new DefaultWebChromeClient());
    }

    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    public void loadUrl(String url, Map<String, String> headers) {
        mWebView.loadUrl(url, headers);
    }

    public WebView getWebView() {
        return mWebView;
    }

    public class DefaultWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mProgressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setProgress(0);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view,
                                                WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    public class DefaultWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgressBar.setVisibility(GONE);
            } else {
                if (mProgressBar.getVisibility() == GONE)
                    mProgressBar.setVisibility(VISIBLE);
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        mWebView.destroy();
        super.onDetachedFromWindow();
    }
}
