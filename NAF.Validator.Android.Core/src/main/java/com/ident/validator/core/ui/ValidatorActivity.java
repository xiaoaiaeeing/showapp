package com.ident.validator.core.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ident.validator.core.R;
import com.ident.validator.core.base.BaseActivity;
import com.ident.validator.core.base.BaseFragment;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

public class ValidatorActivity extends BaseActivity implements ValidatorContract.View {
    private ValidatorPresenter mPresenter;
    private ImageView mToolbarRightBtn;
    private ImageView mToolbarBackBtn;
    private static final int REQUEST_ALL_PERMISSION = 300;
    private BaseFragment mCurrentFragment;

    public static void jump2Validator(Context context) {
        Intent intent = new Intent(context, ValidatorActivity.class);
        context.startActivity(intent);
    }

    public static void jump2Validator(Context context, Intent intent) {
        Intent jmp = new Intent(intent);
        jmp.setClass(context, ValidatorActivity.class);
        context.startActivity(jmp);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_validator;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.compat(this, getResources().getColor(R.color.status_bar_color));
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        mPresenter = new ValidatorPresenter(this);
        switchFragment(DefaultValidatorFragment.newInstance());
        mToolbarRightBtn = (ImageView) findViewById(R.id.btn_right);
        mToolbarRightBtn.setOnClickListener(this);
        mToolbarBackBtn = (ImageView) findViewById(R.id.btn_back);
        mToolbarBackBtn.setOnClickListener(this);
        initPermission();

    }

    @Override
    public void switchFragment(BaseFragment fm) {
        if (mCurrentFragment != null && TextUtils.equals(mCurrentFragment.getClass().getSimpleName(), fm.getClass().getSimpleName())) {
            return;
        }
        if (fm instanceof BaseValidatorFragment) {
            ((BaseValidatorFragment) fm).setPresenter(mPresenter);
        }
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.container, fm);
        trans.commitNowAllowingStateLoss();
        mCurrentFragment = fm;
    }

    private void initPermission() {
        AndPermission.with(this).requestCode(REQUEST_ALL_PERMISSION)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE
                        , Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        AndPermission.rationaleDialog(ValidatorActivity.this, rationale)
                                .show();
                    }
                }).callback(this)
                .start();
    }

    @PermissionYes(REQUEST_ALL_PERMISSION)
    private void getMultiYes(@NonNull List<String> grantedPermissions) {
//        Toast.makeText(this, R.string.message_get_permissions_success, Toast.LENGTH_SHORT).show();
    }

    @PermissionNo(REQUEST_ALL_PERMISSION)
    private void getMultiNo(@NonNull List<String> deniedPermissions) {
//        Toast.makeText(this, R.string.message_get_permissions_failure, Toast.LENGTH_SHORT).show();

        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
            AndPermission.defaultSettingDialog(this, REQUEST_ALL_PERMISSION)
                    .setTitle(R.string.title_dialog)
                    .setMessage(R.string.message_permission_failed)
                    .setPositiveButton(R.string.btn_dialog_yes_permission)
                    .setNegativeButton(R.string.btn_dialog_no_permission, null)
                    .show();

        }
    }

    private String back_class;

    @Override
    protected void initializeData() {
        ApplicationInfo appInfo = null;
        try {
            appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            back_class = appInfo.metaData.getString("back_class");
            System.out.println("back_class:" + back_class);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showSuccess(boolean test) {
        if (mCurrentFragment instanceof BaseValidatorFragment) {
            ((BaseValidatorFragment) mCurrentFragment).showSuccess(test);
        }
    }

    @Override
    public void showFailure(boolean test) {
        if (mCurrentFragment instanceof BaseValidatorFragment) {
            ((BaseValidatorFragment) mCurrentFragment).showFailure(test);
        }
    }

    @Override
    public void restUI() {
//        if (mCurrentFragment instanceof BaseValidatorFragment) {
//            ((BaseValidatorFragment) mCurrentFragment).restUI();
//        }
        switchFragment(DefaultValidatorFragment.newInstance());
    }

    public void hideToolbarRightBtn() {
        mToolbarRightBtn.setVisibility(View.GONE);
    }


    @Override
    public void showProduct(int product_bg, int product_logo, int product_seal, int product_failure, int product_success, int product_img) {
        if (mCurrentFragment instanceof BaseValidatorFragment) {
            ((BaseValidatorFragment) mCurrentFragment).showProduct(product_bg, product_logo, product_seal, product_failure, product_success, product_img);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mPresenter.onNewIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_ALL_PERMISSION: {

                break;
            }
        }
    }


    @Override
    public void showAlert(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTipViews() {
        if (mCurrentFragment instanceof BaseValidatorFragment) {
            ((BaseValidatorFragment) mCurrentFragment).setTipViews();
        }
    }

    @Override
    public void jump2Result(String url) {
        ResultActivity.jumpIntent(this, url);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v == mToolbarRightBtn) {
            System.out.println("jumpUrl:" + jumpUrl);
            CommonWebActivity.jumpIntent(this, jumpUrl);
        } else if (v == mToolbarBackBtn) {
            onBackPressed();
        }
    }


    String jumpUrl;

    @Override
    public void showToolbarRightMenu(String url, boolean show) {
        jumpUrl = url;
        mToolbarRightBtn.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (!TextUtils.isEmpty(back_class)) {
            Intent intent = new Intent();
            intent.setClassName(this, back_class);
            startActivity(intent);
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

}
