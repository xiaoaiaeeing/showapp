package com.ident.validator.ui.company;

import android.os.Bundle;
import android.view.View;

import com.ident.validator.R;
import com.ident.validator.common.base.BaseActivity;
import com.ident.validator.common.base.mvp.BasePresenter;
import com.ident.validator.common.utils.ShareUtils;

public class ShareIdentActivity extends BaseActivity {

	@Override
	protected int getLayoutResId() {
		return R.layout.activity_share_ident;
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected void initializeViews(Bundle savedInstanceState) {
		findViewById(R.id.share_btn).setOnClickListener(this);
	}

	@Override
	protected void initializeData() {


	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.share_btn:
			//ShareUtils.shareText(this, getString(R.string.str_share_text));
			ShareUtils.shareImage(null,this);
//			String temp = "drawable://" + R.drawable.erweim;
//			intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(temp));
			break;

		default:
			break;
		}
	}

}
