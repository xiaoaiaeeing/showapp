package com.ident.validator.ui.report;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.ident.validator.R;
import com.ident.validator.common.base.BaseActivity;
import com.ident.validator.common.base.mvp.BasePresenter;
import com.ident.validator.common.views.IphoneStyleDialog;


public class ReportActivity extends BaseActivity implements OnCheckedChangeListener {

	private View mInptLayout;
	private CheckBox mCheckBox;
	private IphoneStyleDialog mTipDialog;

	@Override
	protected int getLayoutResId() {
		// TODO Auto-generated method stub
		return R.layout.activity_report;
	}

	@Override
	protected BasePresenter createPresenter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initializeViews(Bundle savedInstanceState) {
		mCheckBox = (CheckBox) findViewById(R.id.mCheckbox);
		mCheckBox.setOnCheckedChangeListener(this);
		mInptLayout = findViewById(R.id.input_layout);
		findViewById(R.id.just_report_btn).setOnClickListener(this);
	}

	@Override
	protected void initializeData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.just_report_btn:
			if (mTipDialog == null) {
				mTipDialog = new IphoneStyleDialog(this)
						.setShowTitle("举报成功")
						.setShowContent("匿名举报成功");
			}
			mTipDialog.show();
			break;

		default:
			break;
		}

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		mInptLayout.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
	}

	@Override
	protected void onDestroy() {
		if (mTipDialog != null && mTipDialog.isShowing()) {
			mTipDialog.dismiss();
			mTipDialog = null;
		}
		super.onDestroy();
	}

}
