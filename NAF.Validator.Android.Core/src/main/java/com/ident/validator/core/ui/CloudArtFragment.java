package com.ident.validator.core.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

/**
 * @author cheny
 * @version 1.0
 * @descr 云艺术
 * @date 2017/7/18 16:17
 */

public class CloudArtFragment extends ValidatorFragment {

    public static CloudArtFragment newInstance() {

        Bundle args = new Bundle();

        CloudArtFragment fragment = new CloudArtFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        super.setupView(savedInstanceState);
        mSealView.setVisibility(View.GONE);
        mTitleTxtView.setText("人人都是收藏家！");
        mSubTitleTxtView.setText("收藏艺术就是收藏历史！");
        mTitleTxtView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        mSubTitleTxtView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        mRootView.setImageDrawable(new ColorDrawable(Color.WHITE));
        mSuccessProductImgView.setVisibility(View.GONE);
    }

    @Override
    public void restUI() {
        super.restUI();
        mRootView.setImageDrawable(new ColorDrawable(Color.WHITE));
    }
}
