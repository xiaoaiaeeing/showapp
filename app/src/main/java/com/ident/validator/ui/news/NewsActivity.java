package com.ident.validator.ui.news;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ident.validator.R;
import com.ident.validator.common.base.BaseActivity;
import com.ident.validator.common.base.mvp.BasePresenter;
import com.ident.validator.data.bean.News;
import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

import java.util.ArrayList;

public class NewsActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;

    @Override
    protected int getLayoutResId() {
        // TODO Auto-generated method stub
        return R.layout.activity_news;
    }

    @Override
    protected BasePresenter createPresenter() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new NewsAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                News news = mAdapter.getItem(position);
                NewsDetailsActivity.jumpIntent(NewsActivity.this, news.url);
            }
        });
    }

    @Override
    protected void initializeData() {
        ArrayList<News> datas = new ArrayList<>();
        News news = new News();
        news.iconUrl = "http://uploads.chinatimes.cc/article/201703/20170321093353pSgQs5ClHZ.jpg";
        news.summary = "在糖酒会上，记者刚刚从五粮液股份公司获悉，52度500mL新品五粮液包装防伪进行了全面升级，消费者可通过带NFC功能的手机可直接查询产品真伪。升级产品近期将逐步投放市场，与现有产品逐步实现自然过渡。";
        news.title = "五粮液拿出打假利器，手机即可直接查询真伪";
        news.url = "http://m.chinatimes.cc/article/65746.html?from=groupmessage&isappinstalled=0";
        datas.add(news);
        news = new News();
        news.iconUrl = "http://mmbiz.qpic.cn/mmbiz_jpg/pOnlKicJibGCm8pdnuibktqYSMeaeDpnibPbpFYZWqTL1pwGetVbAp51QCWYbH4jASbWqTpMUrX1ib0OK4zy0dq5Lfg/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1";
        news.summary = "近日，五粮液股份公司对52度500mL新品五粮液包装防伪进行了全面升级，消费者可通过带NFC功能的手机可直接查询产品真伪。升级产品近期将逐步投放市场，与现有产品逐步实现自然过渡。";
        news.title = "五粮液防伪全面升级 NFC手机轻松查真伪";
        news.url = "http://mp.weixin.qq.com/s/50FfnXvB15L-i--H7sp1FQ";
        datas.add(news);
        mAdapter.setNewData(datas);
    }

    @Override
    public void onClick(View v) {

    }

    class DividerItemDecoration extends Y_DividerItemDecoration {
        public DividerItemDecoration(Context context) {
            super(context);
        }

        @Override
        public Y_Divider getDivider(int itemPosition) {
            Y_Divider divider;
            divider = new Y_DividerBuilder()
                    .setRightSideLine(true, Color.TRANSPARENT, 10f, 0f, 0f)
                    .setBottomSideLine(true, Color.parseColor("#e0e0e0"), 1f, 0f, 0f)
                    .setLeftSideLine(true, Color.TRANSPARENT, 10f, 0f, 0f)
                    .create();
            return divider;
        }
    }
}
