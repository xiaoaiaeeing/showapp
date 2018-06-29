package com.ident.validator.common.base.mvp;

public interface IPresenter<V extends BaseView> {
    void attachView(V view);

    void start();

    void pause();

    void detachView();
}