package com.ident.validator.common.views;

import com.ident.validator.R;
import com.ident.validator.common.imageloader.ZImageLoader;
import com.ident.validator.common.utils.StringUtils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;

public class BigImageDialog extends Dialog implements View.OnClickListener {
	private TouchImageView mCerImage;

	public BigImageDialog(Context context) {
		super(context, R.style.dialogTheme2);
		initializeView();
	}

	private void initializeView() {
		setContentView(R.layout.dialog_big_image);
		mCerImage = (TouchImageView) findViewById(R.id.touchImageView);
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//		getWindow().setAttributes(lp);
		mCerImage.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		dismiss();
	}

	public BigImageDialog loadImage(String url) {
		if (StringUtils.isValidate(url)) {
			ZImageLoader.loadFromUrl(getContext(), url, mCerImage);
		}
		return this;
	}
}
