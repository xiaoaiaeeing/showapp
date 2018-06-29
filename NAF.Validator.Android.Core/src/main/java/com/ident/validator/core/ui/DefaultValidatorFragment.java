package com.ident.validator.core.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ident.validator.core.R;
import com.ident.validator.core.base.BaseFragment;

/**
 * @author cheny
 * @version 1.0
 * @descr
 * @date 2017/7/20 9:32
 */

public class DefaultValidatorFragment extends BaseFragment {

    public static DefaultValidatorFragment newInstance() {

        Bundle args = new Bundle();
        
        DefaultValidatorFragment fragment = new DefaultValidatorFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getRootViewLayoutId() {
        return R.layout.fragment_default_validator;
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void setupData() {

    }

    @Override
    public void onClick(View v) {

    }


}
