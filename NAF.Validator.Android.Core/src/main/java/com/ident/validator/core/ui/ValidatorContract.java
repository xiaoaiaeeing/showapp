package com.ident.validator.core.ui;

import android.content.Intent;

import com.ident.validator.core.base.BaseFragment;

/**
 * @author cheny
 * @version 1.0
 * @descr
 * @date 2017/7/10 11:04
 */

public interface ValidatorContract {
    interface View {
        void showSuccess(boolean test);

        void showFailure(boolean test);

        void restUI();
        void showAlert(String msg);

        void setTipViews();

        void jump2Result(String url);

        void showProduct(int product_bg, int product_logo, int product_seal, int product_failure, int product_success, int product_img);

        void showToolbarRightMenu(String url,boolean show);
        void switchFragment(BaseFragment fm);
    }

    interface Presenter {
        void onStart();

        void onResume();

        void onPause();

        void onStop();

        void onNewIntent(Intent intent);

        void onNewIntent(Intent intent, String num1);

        void onDestroy();

        void init();

        void showToolbarRightMenu();

        String getToolbarRightJumpUrl();
    }
}
