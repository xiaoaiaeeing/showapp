package com.ident.validator.common.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ident.validator.R;

/**
 * @author sky 2016/7/29 16:18
 */
public class CustomToolbar extends RelativeLayout implements
		View.OnClickListener {

	private TextView mLeftBtn;
	private ImageView mRightBtn;
	private OnToolbarClickListener mToolbarClickListener;

	public CustomToolbar(Context context) {
		super(context);
		init();
	}

	public CustomToolbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		inflate(getContext(), R.layout.custom_toolbar, this);
		mLeftBtn = (TextView) findViewById(R.id.left_btn);
		mRightBtn = (ImageView) findViewById(R.id.right_btn);
		mLeftBtn.setOnClickListener(this);
		mRightBtn.setOnClickListener(this);
	}
	
	public void setRightBtnImage(int resId) {
		mRightBtn.setImageResource(resId);
	}

	@Override
	public void onClick(View v) {
		if (mToolbarClickListener != null) {
			if (v == mLeftBtn) {
				mToolbarClickListener.onToolbarLeftClick();
			} else {
				mToolbarClickListener.onToolbarRightClick();
			}
		}
	}
	
	public void setLeftBtnVisibility(boolean show) {
		mLeftBtn.setVisibility(show == true ? VISIBLE :GONE);
	}
	
	public void setRightBtnVisibility(boolean show) {
		mRightBtn.setVisibility(show == true ? VISIBLE :GONE);
	}

	public static interface OnToolbarClickListener {

		void onToolbarLeftClick();

		void onToolbarRightClick();
	}

	public void setToolbarClickListener(
			OnToolbarClickListener toolbarClickListener) {
		this.mToolbarClickListener = toolbarClickListener;
	}

}
