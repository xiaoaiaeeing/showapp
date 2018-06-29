package com.ident.validator.core.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ident.NAFNfc;
import com.ident.validator.core.ui.ValidatorActivity;
import com.ident.validator.core.utils.NAFVerifyHelper;
import com.ident.validator.core.utils.StatusBarUtil;


/**
 * @author cheny
 * @version 1.0
 * @descr
 * @date 2017/7/10 9:51
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private NAFNfc mNafNfc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutResId();
        if (layoutId > 0) {
            setContentView(layoutId);
            initializeViews(savedInstanceState);
            initializeData();
        }
        StatusBarUtil.setColor(this, Color.BLACK);
        mNafNfc = new NAFNfc();
        mNafNfc.init(this);
    }

    protected abstract int getLayoutResId();

    protected abstract void initializeViews(Bundle savedInstanceState);

    protected abstract void initializeData();

    @Override
    protected void onResume() {
        super.onResume();
        mNafNfc.enableForegroundDispatch();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNafNfc.disableForegroundDispatch();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNafNfc = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (NAFVerifyHelper.checkNfcData(intent)) {
            if (getClass() != ValidatorActivity.class) {
                ValidatorActivity.jump2Validator(this, intent);
            }
        }
    }
}
