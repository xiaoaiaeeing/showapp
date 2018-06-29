package com.ident.validator.ui.company;

import android.os.Bundle;
import android.view.View;

import com.ident.validator.R;
import com.ident.validator.common.base.BaseActivity;
import com.ident.validator.common.base.mvp.BasePresenter;


public class CompanyActivity extends BaseActivity {

	@Override
	protected int getLayoutResId() {
		// TODO Auto-generated method stub
		return R.layout.activity_company;
	}

	@Override
	protected BasePresenter createPresenter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initializeViews(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		findViewById(R.id.company_intro_item).setOnClickListener(this);
		findViewById(R.id.company_tenchnical_item).setOnClickListener(this);
	}

	@Override
	protected void initializeData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.company_intro_item:
			CompanyCommonActivity.jumpIntent(this, CompanyCommonActivity.SHOW_COMPANY_INTRODUCTION);
			break;
		case R.id.company_tenchnical_item:
			CompanyCommonActivity.jumpIntent(this, CompanyCommonActivity.SHOW_COMPANY_TECHNICAL);
			break;
		default:
			break;
		}
	}
}
