package com.ident.validator.core.ui;

import android.os.Bundle;
import android.view.View;

import com.ident.validator.core.base.BaseFragment;

/**
 * @author cheny
 * @version 1.0
 * @descr
 * @date 2017/7/18 16:18
 */

public abstract class BaseValidatorFragment extends BaseFragment implements ValidatorContract.View {
    protected ValidatorPresenter mPresenter;

    public void setPresenter(ValidatorPresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void setupData() {

    }

    @Override
    public void showToolbarRightMenu(String url, boolean show) {

    }
}
