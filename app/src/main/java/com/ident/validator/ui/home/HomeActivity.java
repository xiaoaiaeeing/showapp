package com.ident.validator.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ident.NAFNfc;
import com.ident.validator.R;
import com.ident.validator.common.base.BaseActivity;
import com.ident.validator.common.base.mvp.BasePresenter;
import com.ident.validator.common.imageloader.ZImageLoader;
import com.ident.validator.common.utils.AppManager;
import com.ident.validator.common.utils.DisplayUtils;
import com.ident.validator.common.widgets.rollviewpager.RollPagerView;
import com.ident.validator.common.widgets.rollviewpager.hintview.IconHintView;
import com.ident.validator.core.ui.ValidatorActivity;
import com.ident.validator.core.utils.NAFVerifyHelper;
import com.ident.validator.ui.company.CompanyActivity;
import com.ident.validator.ui.company.ShareIdentActivity;
import com.ident.validator.ui.manual.UseManualActivity;
import com.ident.validator.ui.merchant.MerchantActivity;
import com.ident.validator.ui.news.NewsActivity;
import com.ident.validator.ui.news.NewsDetailsActivity;

import java.util.ArrayList;


public class HomeActivity extends BaseActivity {

    private RollPagerView mRollPagerView;
    private HomeBannerAdapter mHomeBanner;
    private ImageView mNewsImage;
    private TextView mNewsSummary;
    private TextView mNewsTitle;
    private NAFNfc mNafNfc;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initializeToolbar() {
        super.initializeToolbar();
        getCustomToolbar().setLeftBtnVisibility(false);
        getCustomToolbar().setRightBtnImage(R.mipmap.erweim);
        getCustomToolbar().setRightBtnVisibility(true);
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        findViewById(R.id.ident_about_btn).setOnClickListener(this);
        findViewById(R.id.ident_partner_btn).setOnClickListener(this);
        findViewById(R.id.ident_verify_btn).setOnClickListener(this);
        findViewById(R.id.ident_news_btn).setOnClickListener(this);
        findViewById(R.id.ident_manual_btn).setOnClickListener(this);
        findViewById(R.id.more_news_btn).setOnClickListener(this);
        findViewById(R.id.more_expertise_btn).setOnClickListener(this);
        findViewById(R.id.news_item).setOnClickListener(this);
        findViewById(R.id.expertise_item).setOnClickListener(this);
        findViewById(R.id.merchant_item).setOnClickListener(this);
        findViewById(R.id.more_merchant_btn).setOnClickListener(this);
        mRollPagerView = (RollPagerView) findViewById(R.id.mRollPagerView);
        mRollPagerView.setHintView(new IconHintView(this, R.mipmap.ic_home_point_focus,
                R.mipmap.ic_home_point_normal, DisplayUtils.dip2px(getApplicationContext(), 28)));
        mHomeBanner = new HomeBannerAdapter(mRollPagerView);
        mRollPagerView.setAdapter(mHomeBanner);

        mNewsImage = (ImageView) findViewById(R.id.news_img);
        mNewsSummary = (TextView) findViewById(R.id.news_summary);
        mNewsTitle = (TextView) findViewById(R.id.news_title);
        mNafNfc = new NAFNfc();
        mNafNfc.init(this);
    }

    @Override
    protected void initializeData() {
        ArrayList<Integer> datas = new ArrayList<Integer>();
        datas.add(R.mipmap.img_banner_01);
        datas.add(R.mipmap.img_banner_02);
        // datas.add(R.drawable.img_banner_03);
        datas.add(R.mipmap.img_banner_04);
        datas.add(R.mipmap.img_banner_05);
        datas.add(R.mipmap.img_banner_06);
        // datas.add(R.drawable.img_banner_07);
        mHomeBanner.setData(datas);
        ZImageLoader.loadFromRes(this, R.mipmap.new_test, mNewsImage);
        mNewsTitle.setText("五粮液拿出打假利器，手机即可直接查询真伪");
        mNewsSummary.setText(
                "在糖酒会上，记者刚刚从五粮液股份公司获悉，52度500mL新品五粮液包装防伪进行了全面升级，消费者可通过带NFC功能的手机可直接查询产品真伪。升级产品近期将逐步投放市场，与现有产品逐步实现自然过渡。");

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

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
        mNafNfc = null;
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (NAFVerifyHelper.checkNfcData(intent)) {
            ValidatorActivity.jump2Validator(this, intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ident_about_btn:
                startActivity(CompanyActivity.class);
                break;
            case R.id.ident_partner_btn:
            case R.id.more_merchant_btn:
                startActivity(MerchantActivity.class);
                break;
            case R.id.ident_verify_btn:
                ValidatorActivity.jump2Validator(this);
                break;
            case R.id.ident_news_btn:
            case R.id.more_news_btn:
                startActivity(NewsActivity.class);
                break;
            case R.id.ident_manual_btn:
            case R.id.more_expertise_btn:
            case R.id.expertise_item:
                startActivity(UseManualActivity.class);
                break;
            case R.id.news_item:
                NewsDetailsActivity.jumpIntent(this,
                        "http://m.chinatimes.cc/article/65746.html?from=groupmessage&isappinstalled=0");
                break;
            case R.id.merchant_item:
                NewsDetailsActivity.jumpIntent(this, "http://www.ssjx1915.cn");
                break;
        }
    }

    @Override
    public void onToolbarRightClick() {
        super.onToolbarRightClick();
        startActivity(ShareIdentActivity.class);
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    AppManager.getAppManager().finishAllActivity();
                }
                break;
        }
        return false;
    }

}
