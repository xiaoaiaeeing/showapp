package com.ident.validator.ui.goods;

import com.ident.validator.common.base.BaseWebViewActivity;
import com.ident.validator.common.base.mvp.BasePresenter;
import com.ident.validator.common.views.CustomWebView;


import android.webkit.WebView;

public class GoodsMoreInfoActivity extends BaseWebViewActivity {

	@Override
	public void setupWebView(WebView webview) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadUrl(CustomWebView webview) {
		// TODO Auto-generated method stub
		webview.loadUrl("http://www.wuliangye.com.cn/");
	}

	@Override
	protected BasePresenter createPresenter() {
		// TODO Auto-generated method stub
		return null;
	}

}
