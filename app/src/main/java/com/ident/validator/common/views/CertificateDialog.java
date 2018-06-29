package com.ident.validator.common.views;

import com.ident.validator.R;
import com.ident.validator.common.imageloader.ZImageLoader;
import com.ident.validator.common.utils.StringUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class CertificateDialog extends Dialog implements View.OnClickListener {
	private TextView mContentTxt;
	private ImageView mCerImage;
	private TextView mContinueBtn;
	private View mFullImg;
	private String mUrl;
	private CertificateListener mListener;

	public CertificateDialog(Context context) {
		super(context, R.style.dialogTheme);
		initializeView();
	}

	private void initializeView() {
		setContentView(R.layout.dialog_certificate);
		mContentTxt = (TextView) findViewById(R.id.dialog_content);
		mContinueBtn = (TextView) findViewById(R.id.continue_btn);
		mCerImage = (ImageView) findViewById(R.id.cer_img);
		mFullImg = findViewById(R.id.look_full_img);
		mContinueBtn.setOnClickListener(this);
		mFullImg.setOnClickListener(this);

		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		getWindow().setAttributes(lp);
	}

	public CertificateDialog setShowContent(String content) {
		mContentTxt.setText(content);
		return this;
	}
	
	public CertificateDialog setCloseListener(OnDismissListener listener) {
		setOnDismissListener(listener);
		return this;
	}

	public CertificateDialog loadImage(String url) {
		this.mUrl = url;
		if (StringUtils.isValidate(url)) {
			ZImageLoader.loadFromUrl(getContext(), url,R.drawable.default_shape,mCerImage);
		}
		return this;
	}

	@Override
	public void onClick(View v) {
		if (mListener != null) {
			if (v == mContinueBtn) {
				dismiss();
				mListener.onContinue();
			} else if (v == mFullImg) {
				mListener.onShowBigImage(mUrl);
			}
		}
	}

	public CertificateDialog setCertificateListener(CertificateListener l) {
		this.mListener = l;
		return this;
	}

	public static interface CertificateListener {
		void onContinue();

		void onShowBigImage(String url);
	}
}
