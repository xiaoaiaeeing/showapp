package com.ident.validator.ui.home;

import java.util.ArrayList;

import com.ident.validator.common.imageloader.ZImageLoader;
import com.ident.validator.common.widgets.rollviewpager.RollPagerView;
import com.ident.validator.common.widgets.rollviewpager.adapter.LoopPagerAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HomeBannerAdapter extends LoopPagerAdapter {
	private ArrayList<Integer> datas;

	public HomeBannerAdapter(RollPagerView viewPager) {
		super(viewPager);
		datas = new ArrayList<Integer>();
	}

	public void setData(ArrayList<Integer> datas) {
		this.datas = datas;
		notifyDataSetChanged();
	}

	@Override
	public View getView(final ViewGroup container, int position) {
		ImageView view = new ImageView(container.getContext());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                T.showShort(container.getContext(), "onClick");
            }
        });
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    	ZImageLoader.loadFromRes(container.getContext(),
				datas.get(position),
				container.getMeasuredWidth(),
				container.getMeasuredHeight(), 
				view);
        return view;
	}

	@Override
	public int getRealCount() {
		return datas == null ? 0 : datas.size();
	}

}
