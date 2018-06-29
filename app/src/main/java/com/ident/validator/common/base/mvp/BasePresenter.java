package com.ident.validator.common.base.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<V extends BaseView> implements IPresenter<V> {
    protected V mView;
    protected CompositeSubscription mCompositeSubscription;

    public BasePresenter() {
    }

    public BasePresenter(V view) {
        mView = view;
    }

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
        onUnsubscribe();
    }

    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription.clear();
            mCompositeSubscription = null;
        }
    }

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }
}