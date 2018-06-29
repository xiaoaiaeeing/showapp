package com.ident.validator.common.views;

import com.ident.validator.R;
import com.ident.validator.common.utils.DisplayUtils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class VerifySuccessDialog extends Dialog implements View.OnClickListener {
	private ImageView mSuccessImg;

	public VerifySuccessDialog(Context context) {
		super(context, R.style.Translucent_NoTitle);
		initializeView();
	}

	private void initializeView() {
		setContentView(R.layout.dialog_success);

		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		getWindow().setAttributes(lp);
		getWindow().setWindowAnimations(R.style.test);

		findViewById(R.id.success_close).setOnClickListener(this);
		mSuccessImg = (ImageView)findViewById(R.id.success_img);
		
	}

	public void updateLocation(int x, int y) {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.y = y - DisplayUtils.dip2px(getContext(), 20);
		getWindow().setAttributes(lp);
	}
	
	private int targetWidth;
	private int targetHeight;
	
	public void scale(int width, int height) {
		targetWidth = width;
		targetHeight = height;
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		int tempWidth = lp.width;
		int tempHeight = lp.height;

		int sWidth = tempWidth / targetWidth;
		int sHeight = tempHeight / targetHeight;
		lp.width = tempWidth * sWidth;
		lp.height = tempHeight * sHeight;
		getWindow().setAttributes(lp);
	}
	
	@Override
	public void show() {
		super.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.success_close:
			dismiss();
			break;

		default:
			break;
		}
	}

}
