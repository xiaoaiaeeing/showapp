package com.ident.validator.common.views;

import com.ident.validator.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class VerifyStatusDialog extends Dialog implements View.OnClickListener {
	private TextView mRightBtn, mLeftBtn;
	private TextView mContentTxt;
	private OnDialogListener mListener;
	private View mDivider;

	public VerifyStatusDialog(Context context) {
		super(context, R.style.dialogTheme);
		initializeView();
	}

	private void initializeView() {
		setContentView(R.layout.dialog_verify_status);
		mContentTxt = (TextView) findViewById(R.id.dialog_content);
		mRightBtn = (TextView) findViewById(R.id.dialog_right_btn);
		mLeftBtn = (TextView) findViewById(R.id.dialog_left_btn);
		mDivider = findViewById(R.id.mDivider);
		//
		mRightBtn.setOnClickListener(this);
		mLeftBtn.setOnClickListener(this);

		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		getWindow().setAttributes(lp);
	}

	public void setShowOnlyBtn(boolean only) {
		mLeftBtn.setVisibility(only ? View.GONE : View.VISIBLE);
		mDivider.setVisibility(only ? View.GONE : View.VISIBLE);
		mRightBtn.setBackgroundResource(only ? R.drawable.dialog_bottom_btn_shape
						: R.drawable.dialog_right_btn_shape);
	}

	public VerifyStatusDialog setShowContent(String content) {
		mContentTxt.setText(content);
		return this;
	}
	
	public VerifyStatusDialog setCloseListener(OnDismissListener listener) {
		setOnDismissListener(listener);
		return this;
	}

	@Override
	public void onClick(View v) {
		dismiss();
		if (mListener != null) {
			if (v == mRightBtn) {
				mListener.onRightClick(this);
			} else {
				mListener.onLeftClick(this);
			}
		}
	}

	public static interface OnDialogListener {
		void onLeftClick(VerifyStatusDialog dialog);

		void onRightClick(VerifyStatusDialog dialog);
	}

	public VerifyStatusDialog setDialogListener(OnDialogListener listener) {
		this.mListener = listener;
		return this;
	}
}
