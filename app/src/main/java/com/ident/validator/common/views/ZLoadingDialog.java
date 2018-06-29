package com.ident.validator.common.views;

import com.ident.validator.R;

import android.app.Dialog;
import android.content.Context;

/**
 * @author sky
 * @version 1.0
 * @descr
 * @date 2016/10/20 17:28
 */

public class ZLoadingDialog extends Dialog {
    public ZLoadingDialog(Context context) {
        super(context, R.style.dialogTheme);
        initializeView();
    }

    private void initializeView() {
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.loading_dialog);
    }
}
