package com.ident.validator.core.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ident.validator.core.R;
import com.ident.validator.core.base.BaseFragment;

/**
 * Created by pengllrn on 2018/7/30.
 */

public class FailedFragment extends BaseFragment {
    public static FailedFragment newInstance(){
        Bundle args = new Bundle();
        FailedFragment fragment = new FailedFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getRootViewLayoutId() {
        return R.layout.fragment_failure;
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {

    }

    @Override
    protected void setupData() {

    }

    @Override
    public void onClick(View v) {

    }

    public void setUid(String s){
        TextView tv_uid = (TextView) getHostActivity().findViewById(R.id.batch);
        tv_uid.setText(s);
    }
}
