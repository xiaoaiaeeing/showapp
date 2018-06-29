package com.ident.validator.core.views;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.WindowManager;

import com.ident.validator.core.R;

/**
 * @author cheny
 * @version 1.0
 * @descr
 * @date 2017/7/13 10:58
 */

public class ProgressDialog extends Dialog {

    private ProgressBarView mProgressView;

    public ProgressDialog(@NonNull Context context) {
        super(context, R.style.dialogTheme);
        setContentView(R.layout.dialog_progress);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        initializeViews();
//
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        getWindow().setAttributes(lp);
    }

    private void initializeViews() {
        mProgressView = (ProgressBarView) findViewById(R.id.progressbarview);
        mProgressView.setPercent(true);
    }

    public void setProgress(int progress) {
        mProgressView.setCurrentProgress(progress);
    }

}
