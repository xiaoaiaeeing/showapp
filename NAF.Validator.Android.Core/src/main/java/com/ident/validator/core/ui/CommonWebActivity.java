package com.ident.validator.core.ui;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ident.validator.core.R;
import com.ident.validator.core.base.BaseActivity;
import com.ident.validator.core.views.CustomWebView;

/**
 * @author cheny
 * @version 1.0
 * @descr
 * @date 2017/7/10 13:24
 */

public class CommonWebActivity extends BaseActivity {
    private CustomWebView mCustomWebView;
    private ImageView mBackBtn;

    public static void jumpIntent(Context context, String url) {
        Intent intent = new Intent(context, CommonWebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_common_web;
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        mCustomWebView = (CustomWebView) findViewById(R.id.customWebView);
        mBackBtn = (ImageView) findViewById(R.id.btn_back);
        mBackBtn.setOnClickListener(this);
    }

    @Override
    protected void initializeData() {
        String url = getIntent().getStringExtra("url");
        mCustomWebView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        if (v == mBackBtn) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        if (mCustomWebView.getWebView().canGoBack()) {
            mCustomWebView.getWebView().goBack();
        } else {
            super.onBackPressed();
        }
    }
}
