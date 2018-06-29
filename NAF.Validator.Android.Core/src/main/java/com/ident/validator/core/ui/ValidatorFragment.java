package com.ident.validator.core.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ident.validator.core.R;
import com.ident.validator.core.base.BaseFragment;
import com.ident.validator.core.views.MyWaveView;
import com.ident.validator.core.views.WaveLoadingView;

/**
 * @author cheny
 * @version 1.0
 * @descr 通用验证界面
 * @date 2017/7/18 15:29
 */

public class ValidatorFragment extends BaseValidatorFragment {
    protected ImageView mRootView;
    protected ImageView mLogoView;
    protected ImageView mSealView;
    protected WaveLoadingView mWaveLoadingView;
    protected LinearLayout mSuccessLayout;
    protected LinearLayout mFailureLayout;
    protected ImageView mSuccessImgView;
    protected ImageView mSuccessProductImgView;
    protected ImageView mFailureImgView;
    protected TextView mTipOne;
    protected TextView mTipTwo;
    protected MyWaveView mWaveView;
    protected View mCurrentShowView;
    protected ImageView mFailureCloseBtn;
    protected ImageView mSuccessCloseBtn;
    protected ImageView mTestSealView;
    protected TextView mTitleTxtView, mSubTitleTxtView;

    public static ValidatorFragment newInstance() {
        ValidatorFragment fm = new ValidatorFragment();
        return fm;
    }

    @Override
    protected int getRootViewLayoutId() {
        return R.layout.fragment_validator;
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        mRootView = (ImageView) findViewById(R.id.root_bg);
        mLogoView = (ImageView) findViewById(R.id.logo_iv);
        mSealView = (ImageView) findViewById(R.id.seal_iv);
        mTestSealView = (ImageView) findViewById(R.id.test_seal);
        mTitleTxtView = (TextView) findViewById(R.id.title_txt);
        mSubTitleTxtView = (TextView) findViewById(R.id.sub_title_txt);
        mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        mWaveView = (MyWaveView) findViewById(R.id.wave_view);

        mSuccessLayout = (LinearLayout) findViewById(R.id.success_layout);
        mFailureLayout = (LinearLayout) findViewById(R.id.failure_layout);

        mSuccessProductImgView = (ImageView) findViewById(R.id.success_product_iv);
        mSuccessImgView = (ImageView) findViewById(R.id.success_img);
        mFailureImgView = (ImageView) findViewById(R.id.yzback);

        mTipOne = (TextView) findViewById(R.id.tip_one);
        mTipTwo = (TextView) findViewById(R.id.tip_two);

        mFailureCloseBtn = (ImageView) findViewById(R.id.failure_close);
        mFailureCloseBtn.setOnClickListener(this);
        mSuccessCloseBtn = (ImageView) findViewById(R.id.success_close);
        mSuccessCloseBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSuccessCloseBtn || v == mFailureCloseBtn) {
            scaleMinAnimation();
        }
    }


    private void scaleMinAnimation() {
        if (mCurrentShowView != null) {
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(mCurrentShowView, "scaleX", 1.0f, 0.0f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(mCurrentShowView, "scaleY", 1.0f, 0.0f);
            // ObjectAnimator alpha =
            // ObjectAnimator.ofFloat(mSuccessLayout,"alpha", 1.0f, 0.7f);
            AnimatorSet animationSet = new AnimatorSet();
            animationSet.setDuration(250);
            animationSet.setInterpolator(new DecelerateInterpolator());
            animationSet.playTogether(scaleX, scaleY);
            animationSet.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    restUI();
                }

            });
            animationSet.start();
        }
    }

    private void scaleMaxAnimation(View view, final boolean test) {
        mCurrentShowView = view;
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.0f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.0f, 1.0f);
        // ObjectAnimator alpha = ObjectAnimator.ofFloat(mSuccessLayout,"alpha",
        // 0.5f, 1.0f);
        AnimatorSet animationSet = new AnimatorSet();
        animationSet.setDuration(250);
        animationSet.setInterpolator(new DecelerateInterpolator());
        animationSet.playTogether(scaleX, scaleY);
        animationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (product_img != 0 && product_success != 0 && product_failure != 0) {
                    if (mCurrentShowView == mSuccessLayout) {
                        mSuccessProductImgView.setImageResource(product_img);
                        mSuccessImgView.setImageResource(product_success);
                    } else {
                        mFailureImgView.setImageResource(product_failure);
                    }
                }
                if (mCurrentShowView != mFailureLayout) {
                    mPresenter.showToolbarRightMenu();
                }
                mTestSealView.setVisibility(test ? View.VISIBLE : View.GONE);
                mCurrentShowView.setVisibility(View.VISIBLE);
                mWaveView.setReflesh(false);
            }
        });
        animationSet.start();
    }

    @Override
    public void showSuccess(boolean test) {
        scaleMaxAnimation(mSuccessLayout, test);
    }

    @Override
    public void showFailure(boolean test) {
        scaleMaxAnimation(mFailureLayout, test);
    }

    @Override
    public void restUI() {
//        if (mCurrentShowView != null) {
//            mCurrentShowView.setVisibility(View.GONE);
//        }
//        mTipTwo.setText(R.string.message_start_verify);
//        mTipOne.setVisibility(View.INVISIBLE);
//        mRootView.setImageResource(R.mipmap.p_010001000100000002_bg);
//        mLogoView.setImageResource(R.mipmap.p_010001000100000002_logo);
//        mSealView.setImageResource(R.mipmap.p_010001000100000002_seal);
//        mWaveView.setReflesh(true);
//        product_failure = 0;
//        product_success = 0;
//        product_img = 0;
        ((ValidatorActivity) getHostActivity()).restUI();
        ((ValidatorActivity) getHostActivity()).hideToolbarRightBtn();
    }

    @Override
    public void showAlert(String msg) {

    }

    @Override
    public void setTipViews() {
        mTipTwo.setText(R.string.message_dont_move);
        mTipOne.setVisibility(View.VISIBLE);
    }

    @Override
    public void jump2Result(String url) {

    }

    int product_failure;
    int product_success;
    int product_img;

    @Override
    public void showProduct(int product_bg, int product_logo, int product_seal, int product_failure, int product_success, int product_img) {
        mLogoView.setImageResource(product_logo);
        mSealView.setImageResource(product_seal);
        mRootView.setImageResource(product_bg);
        this.product_failure = product_failure;
        this.product_success = product_success;
        this.product_img = product_img;
    }

    @Override
    public void switchFragment(BaseFragment fm) {

    }
}
