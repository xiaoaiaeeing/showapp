package com.ident.validator.common.views;

import com.ident.validator.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class ReportDialog extends Dialog implements View.OnClickListener {
	private TextView mRightBtn, mLeftBtn;
	private TextView mContentTxt;
	private OnDialogListener mListener;

	public ReportDialog(Context context) {
		super(context, R.style.dialogTheme);
		initializeView();
	}

	private void initializeView() {
		setContentView(R.layout.dialog_report);
		mContentTxt = (TextView) findViewById(R.id.dialog_content);
		mRightBtn = (TextView) findViewById(R.id.dialog_right_btn);
		mLeftBtn = (TextView) findViewById(R.id.dialog_left_btn);
		//
		mRightBtn.setOnClickListener(this);
		mLeftBtn.setOnClickListener(this);

		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		getWindow().setAttributes(lp);
	}

	public ReportDialog setShowContent(String content) {
		mContentTxt.setText(content);
		return this;
	}
	
	public ReportDialog setCloseListener(OnDismissListener listener) {
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

	public interface OnDialogListener {
		void onLeftClick(ReportDialog dialog);

		void onRightClick(ReportDialog dialog);
	}

	public ReportDialog setDialogListener(OnDialogListener listener) {
		this.mListener = listener;
		return this;
	}
}
