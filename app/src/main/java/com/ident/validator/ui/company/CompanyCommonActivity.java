package com.ident.validator.ui.company;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.ident.validator.common.base.BaseWebViewActivity;
import com.ident.validator.common.base.mvp.BasePresenter;
import com.ident.validator.common.views.CustomWebView;
public class CompanyCommonActivity extends BaseWebViewActivity {
	//公司简介
	public static int SHOW_COMPANY_INTRODUCTION = 0;
	//技术支持
	public static int SHOW_COMPANY_TECHNICAL = 1;
	
	public static void jumpIntent(Context context,int ShowType) {
		Intent intent = new Intent(context,CompanyCommonActivity.class);
		intent.putExtra("showType", ShowType);
		context.startActivity(intent);
	}
	
	@Override
	public void setupWebView(WebView webview) {
		// TODO Auto-generated method stub
	
	}


	@Override
	public void loadUrl(CustomWebView webview) {
		int showType = getIntent().getIntExtra("showType", SHOW_COMPANY_INTRODUCTION);
		if(showType == SHOW_COMPANY_INTRODUCTION){
			webview.loadUrl("file:///android_asset/html/company.html");
		}else {
			webview.loadUrl("file:///android_asset/html/technical_support.html");
		}
	}
	

	@Override
	protected BasePresenter createPresenter() {
		// TODO Auto-generated method stub
		return null;
	}


}
