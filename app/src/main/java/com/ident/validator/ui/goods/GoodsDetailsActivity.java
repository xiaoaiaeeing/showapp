package com.ident.validator.ui.goods;

import java.util.ArrayList;

import com.ident.validator.R;
import com.ident.validator.common.base.BaseActivity;
import com.ident.validator.common.base.mvp.BasePresenter;
import com.ident.validator.common.utils.DisplayUtils;
import com.ident.validator.common.widgets.rollviewpager.RollPagerView;
import com.ident.validator.common.widgets.rollviewpager.hintview.IconHintView;


import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;

public class GoodsDetailsActivity extends BaseActivity {
	private RollPagerView mRollPagerView;
	private GoodsBannerAdapter mGoodBanner;
	private TextView mGoodsNumber;
	private TextView mGoodsSalePrice;

	@Override
	protected int getLayoutResId() {
		// TODO Auto-generated method stub
		return R.layout.activity_goods_details;
	}

	@Override
	protected BasePresenter createPresenter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initializeViews(Bundle savedInstanceState) {
		findViewById(R.id.look_more_btn).setOnClickListener(this);
		mGoodsSalePrice = (TextView) findViewById(R.id.sale_price);
		mGoodsNumber = (TextView) findViewById(R.id.goods_number);
		mRollPagerView = (RollPagerView) findViewById(R.id.mRollPagerView);
		mRollPagerView.setHintView(new IconHintView(this,
				R.mipmap.ic_goods_point_focus,
				R.mipmap.ic_goods_point_normal, DisplayUtils.dip2px(
						getApplicationContext(), 20)));
		mGoodBanner = new GoodsBannerAdapter();
		mRollPagerView.setAdapter(mGoodBanner);

		String priceStr = "￥679 官网售价";
		SpannableString msp = new SpannableString(priceStr);
		msp.setSpan(new RelativeSizeSpan(1.5f), 0, 4,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		msp.setSpan(new AbsoluteSizeSpan(13, true), priceStr.length() - 4,
				priceStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		msp.setSpan(new ForegroundColorSpan(Color.RED), 0, 4,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		mGoodsSalePrice.setText(msp);

		String goodsNumberStr = "证书编号 876543667 (售出)";
		SpannableString goodsNumberSp = new SpannableString(goodsNumberStr);
		goodsNumberSp.setSpan(new ForegroundColorSpan(Color.RED),
				goodsNumberStr.length() - 4, goodsNumberStr.length(),
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		mGoodsNumber.setText(goodsNumberSp);
	}

	@Override
	protected void initializeData() {
		ArrayList<String> datas = new ArrayList<String>();
		datas.add("http://pic.35pic.com/normal/07/69/93/10877986_143606421000_2.jpg");
		datas.add("http://img2.imgtn.bdimg.com/it/u=1779349013,2879726041&fm=21&gp=0.jpg");
		datas.add("http://img05.jdzj.com/oledit/UploadFile/news2014c/image/20150920/20150920082748294829613859.jpg");
		datas.add("http://pic40.nipic.com/20140411/2721429_152118312358_2.jpg");
		datas.add("http://file.muyee.com/images/upload/Image/20140424134440.jpg");
		mGoodBanner.setData(datas);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.look_more_btn:
			startActivity(GoodsMoreInfoActivity.class);
			break;

		default:
			break;
		}
	}

}
