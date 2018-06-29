package com.ident.validator.ui.manual;

import java.util.ArrayList;


import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ident.validator.R;
import com.ident.validator.common.base.BaseActivity;
import com.ident.validator.common.base.mvp.BasePresenter;
import com.ident.validator.common.imageloader.ZImageLoader;
import com.ident.validator.common.views.IphoneStyleDialog;

public class UseManualActivity extends BaseActivity implements OnPageChangeListener {

	private ViewPager mViewPager;
	private ImageView mArrowImgView;
	private IphoneStyleDialog mTipDialog;

	@Override
	protected int getLayoutResId() {
		// TODO Auto-generated method stub
		return R.layout.activity_manual;
	}

	@Override
	protected BasePresenter createPresenter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initializeViews(Bundle savedInstanceState) {
		mArrowImgView = (ImageView) findViewById(R.id.mArrow_btn);
		mViewPager = (ViewPager) findViewById(R.id.mPager);
		mViewPager.setOffscreenPageLimit(3);
		mViewPager.addOnPageChangeListener(this);
		mViewPager.setAdapter(new MyPagerAdapter());
		delayedShowDialog();
	}

	private void delayedShowDialog() {
		mTipDialog = new IphoneStyleDialog(this).setShowTitle("操作提示").setShowContent("查看更多步骤功能  请向右滑动");
		getWindow().getDecorView().postDelayed(new Runnable() {

			@Override
			public void run() {
				mTipDialog.show();
			}
		}, 150);
	}

	@Override
	protected void initializeData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int position) {
		switch (position) {
		case 0:
			mArrowImgView.setImageResource(R.mipmap.img_verify_arrow);
			break;
		case 1:
			mArrowImgView.setImageResource(R.mipmap.img_location_arrow);
			break;
		case 2:
			mArrowImgView.setImageResource(R.mipmap.img_open_arrow);
			break;
		}
	}

	@Override
	protected void onDestroy() {
		if (mTipDialog != null && mTipDialog.isShowing()) {
			mTipDialog.dismiss();
			mTipDialog = null;
		}
		super.onDestroy();
	}

	private class MyPagerAdapter extends PagerAdapter {
		private ArrayList<Integer> datas;

		public MyPagerAdapter() {
			datas = new ArrayList<Integer>();
			datas.add(R.mipmap.img_manual_one);
			datas.add(R.mipmap.img_manual_two);
			datas.add(R.mipmap.img_manual_three);
		}

		@Override
		public int getCount() {
			return datas.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView imageView = new ImageView(container.getContext());
			ZImageLoader.loadFromRes(container.getContext(),
					datas.get(position),
					container.getMeasuredWidth(), 
					container.getMeasuredHeight(), 
					imageView);
			((ViewPager) container).addView(imageView, 0);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// super.destroyItem(container, position, object);
			((ViewPager) container).removeView((ImageView) object);
		}
	}
}
