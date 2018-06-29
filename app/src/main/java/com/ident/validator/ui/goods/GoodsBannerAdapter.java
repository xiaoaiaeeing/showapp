package com.ident.validator.ui.goods;

import java.util.ArrayList;

import com.ident.validator.common.imageloader.ZImageLoader;
import com.ident.validator.common.utils.T;
import com.ident.validator.common.widgets.rollviewpager.adapter.DynamicPagerAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class GoodsBannerAdapter extends DynamicPagerAdapter {
	private ArrayList<String> datas;

	public GoodsBannerAdapter() {
		datas = new ArrayList<String>();
	}

	public void setData(ArrayList<String> datas) {
		this.datas = datas;
		notifyDataSetChanged();
	}

	@Override
	public View getView(final ViewGroup container, int position) {
		ImageView view = new ImageView(container.getContext());
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				T.showShort(container.getContext(), "onClick");
			}
		});
		// view.setScaleType(ImageView.ScaleType.CENTER_CROP);
		view.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		ZImageLoader.loadFromUrl(container.getContext(),
				datas.get(position),
				container.getMeasuredWidth(),
				container.getMeasuredHeight(), 
				view);
		return view;
	}

	@Override
	public int getCount() {
		return datas == null ? 0 : datas.size();
	}

}
