package com.ident.validator.common.views;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.ident.validator.R;

public class MerchantDialog extends Dialog implements View.OnClickListener {
	private TextView mBottomBtn;
	private TextView mContentTxt;
	private TextView mDialogTitle;
	private View bottomSecond;
	private TextView mRIghtBtn;
	private TextView mLeftBtn;
	private MerchantDialogListener mListener;

	public MerchantDialog(Context context) {
		super(context, R.style.dialogTheme);
		initializeView();
	}

	private void initializeView() {
		setContentView(R.layout.merchant_dialog);
		mDialogTitle = (TextView) findViewById(R.id.dialog_title);
		mContentTxt = (TextView) findViewById(R.id.dialog_content);
		mBottomBtn = (TextView) findViewById(R.id.dialog_bottom_btn);
		bottomSecond = findViewById(R.id.bottom_second_ly);
		mLeftBtn = (TextView) findViewById(R.id.dialog_left_btn);
		mRIghtBtn = (TextView) findViewById(R.id.dialog_right_btn);

		mBottomBtn.setOnClickListener(this);
		mLeftBtn.setOnClickListener(this);
		mRIghtBtn.setOnClickListener(this);

		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		getWindow().setAttributes(lp);
	}

	public MerchantDialog setShowTitle(String title) {
		mDialogTitle.setText(title);
		return this;
	}

	public MerchantDialog setShowContent(String content) {
		mContentTxt.setText(content);
		return this;
	}

	public MerchantDialog setLeftBtnTxt(String text) {
		mLeftBtn.setText(text);
		return this;
	}

	public MerchantDialog setRightBtnTxt(String text) {
		mRIghtBtn.setText(text);
		return this;
	}

	public MerchantDialog showBottomSecondLy(boolean visibility) {
		bottomSecond.setVisibility(visibility ? View.VISIBLE : View.GONE);
		mBottomBtn.setVisibility(visibility ? View.GONE : View.VISIBLE);
		return this;
	}
	
	public MerchantDialog setCloseListener(OnDismissListener listener) {
		setOnDismissListener(listener);
		return this;
	}

	@Override
	public void onClick(View v) {
		dismiss();
		switch (v.getId()) {
		case R.id.dialog_bottom_btn:
			break;
		case R.id.dialog_left_btn:
			if(mListener != null)
				mListener.onLeftClick(this);
			break;
		case R.id.dialog_right_btn:
			if(mListener != null)
				mListener.onRightClick(this);
			break;
		}
	}

	public static interface MerchantDialogListener {
		void onLeftClick(MerchantDialog dialog);

		void onRightClick(MerchantDialog dialog);
	}

	public MerchantDialog setDialogListener(MerchantDialogListener listener) {
		this.mListener = listener;
		return this;
	}
}
