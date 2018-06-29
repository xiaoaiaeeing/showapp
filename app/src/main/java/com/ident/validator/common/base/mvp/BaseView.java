package com.ident.validator.common.base.mvp;

public interface BaseView {
    void showToast(String msg);

    void showLoadingDialog();

    void closeLoadingDialog();

//    boolean isActive();
}
