package com.ident.validator.ui.news;

import com.ident.validator.common.base.BaseWebViewActivity;
import com.ident.validator.common.base.mvp.BasePresenter;
import com.ident.validator.common.views.CustomWebView;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.WebView;

public class NewsDetailsActivity extends BaseWebViewActivity {

	public static void jumpIntent(Context context, String url) {
		Intent intent = new Intent(context, NewsDetailsActivity.class);
		intent.putExtra("url", url);
		context.startActivity(intent);
	}

	@Override
	public void setupWebView(WebView webview) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadUrl(CustomWebView webview) {
		String url = getIntent().getStringExtra("url");
		if (TextUtils.isEmpty(url)) {
			webview.loadUrl("http://www.wuliangye.com.cn/");
		} else {
			webview.loadUrl(url);
		}
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}
}
