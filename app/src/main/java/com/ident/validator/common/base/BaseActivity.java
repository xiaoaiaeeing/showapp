package com.ident.validator.common.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.ident.validator.App;
import com.ident.validator.R;
import com.ident.validator.common.base.mvp.BasePresenter;
import com.ident.validator.common.utils.AppManager;
import com.ident.validator.common.utils.T;
import com.ident.validator.common.views.CustomToolbar;
import com.ident.validator.common.views.ZLoadingDialog;
import com.jaeger.library.StatusBarUtil;


public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity
        implements View.OnClickListener, CustomToolbar.OnToolbarClickListener {
    protected App mApp;
    private boolean isStartActivity;
    protected P mPresenter;
    protected LayoutInflater mInflater;
    private CustomToolbar mToolbar;
    private ZLoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        mApp = (App) getApplicationContext();
        setContentView(getLayoutResId());
        mInflater = getLayoutInflater();
        mPresenter = createPresenter();
        initializeViews(savedInstanceState);
        initializeToolbar();
        setStatusBar();
        initializeData();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResColor(R.color.colorPrimary), 0);
    }

    protected void initializeToolbar() {
        mToolbar = (CustomToolbar) findViewById(R.id.actionBar);
        if (mToolbar != null) {
            mToolbar.setToolbarClickListener(this);
        }
    }

    protected abstract int getLayoutResId();

    protected abstract P createPresenter();

    protected abstract void initializeViews(Bundle savedInstanceState);

    protected abstract void initializeData();

    public abstract void onClick(View v);

    @Override
    public void onToolbarLeftClick() {
        onBackPressed();
    }

    @Override
    public void onToolbarRightClick() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        release();
    }

    private void release() {
        AppManager.getAppManager().finishActivity(this);
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        closeLoadingDialog();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        isStartActivity = true;
        // overridePendingTransition(R.anim.push_right_in,
        // R.anim.push_left_out);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        isStartActivity = true;
        // overridePendingTransition(R.anim.push_right_in,
        // R.anim.push_left_out);
    }

    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void startActivity(String action) {
        startActivity(action, null);
    }

    protected void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
        if (!isStartActivity) {
            // overridePendingTransition(R.anim.push_left_in,
            // R.anim.push_right_out);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new ZLoadingDialog(this);
        }
        mLoadingDialog.show();
    }

    public void closeLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    public void showToast(String msg) {
        T.showShort(getApplicationContext(), msg);
    }

    public CustomToolbar getCustomToolbar() {
        return mToolbar;
    }

    public App getApp() {
        return mApp;
    }

    protected int getResColor(int color) {
        return this.getResources().getColor(color);
    }

}