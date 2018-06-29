package com.ident.validator.common.base;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.ident.validator.R;
import com.ident.validator.common.base.mvp.BasePresenter;
import com.ident.validator.common.views.CustomWebView;

public abstract class BaseWebViewActivity<P extends BasePresenter> extends BaseActivity<P> {

	private CustomWebView mWebView;

	@Override
	protected int getLayoutResId() {
		// TODO Auto-generated method stub
		return R.layout.activity_base_webview;
	}

	@Override
	protected void initializeViews(Bundle savedInstanceState) {
		mWebView = (CustomWebView) findViewById(R.id.mWebView);
		setupWebView(mWebView.getWebView());
		loadUrl(mWebView);
	}

	public abstract void setupWebView(WebView webview);

	public abstract void loadUrl(CustomWebView webview);

	@Override
	protected void initializeData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onToolbarLeftClick() {
		super.onToolbarLeftClick();
	}

	@Override
	public void onBackPressed() {
		if (mWebView.getWebView().canGoBack()) {
			mWebView.getWebView().goBack();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
