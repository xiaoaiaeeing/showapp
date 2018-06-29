package com.ident.validator.ui.merchant;

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
import com.ident.validator.data.bean.Merchant;
import com.ident.validator.ui.news.NewsDetailsActivity;
import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

import java.util.ArrayList;

public class MerchantActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private MerchantAdapter mAdapter;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_merchant_partner;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MerchantAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Merchant merchant = (Merchant) mAdapter.getItem(position);
                NewsDetailsActivity.jumpIntent(MerchantActivity.this, merchant.url);
            }
        });
    }

    @Override
    protected void initializeData() {
        ArrayList<Merchant> datas = new ArrayList<>();
        Merchant merchant = new Merchant();
        merchant.setUrl("http://www.wuliangye.com.cn");
        merchant.setName("五粮液");
        merchant.setSummary(
                "四川省宜宾五粮液集团有限公司，前身是明初时期沿用下来的8家酿酒作坊在20世纪50年代初联合组建的“中国专卖公司四川省宜宾酒厂”，截至2015年底，公司总资产达到825.28亿元，五粮液品牌价值已达875.69亿元，连续22年保持白酒制造行业第一");
        merchant.setImg(R.mipmap.img_wuliangye);
        datas.add(merchant);
        merchant = new Merchant();
        merchant.setUrl("http://www.moutaichina.com");
        merchant.setName("茅台老酒");
        merchant.setSummary(
                "作为酱香型白酒的领军者，贵州茅台以“酿造高品位的生活”为使命，“打造世界蒸馏酒第一品牌”，被誉为“国酒”。而贵州茅台酒从出厂至几十年藏贮，其色泽呈现无色、微黄色、淡黄色至琥珀金色的渐变，甚至在琥珀金中透出轻微绿的色泽。从酒体的色泽变化中，可窥探出其岁月几点和“液体黄金”之美。");
        merchant.setImg(R.mipmap.img_moutai);
        datas.add(merchant);
        merchant = new Merchant();
        merchant.setUrl("http://www.chinayanghe.com/");
        merchant.setName("洋河");
        merchant.setSummary(
                "洋河，中国江苏洋河酒厂股份有限公司商标。洋河酒历史悠久，起源于两汉而兴于唐宋。洋河酒属于浓香型大曲酒，以小麦、大麦、豌豆为原料精制而成；洋河酒曾多次荣获“国际名酒”和入选中国八大名酒行列。");
        merchant.setImg(R.mipmap.img_yanghe);
        datas.add(merchant);
        merchant = new Merchant();
        merchant.setUrl("http://www.lzljmall.com");
        merchant.setName("泸州老窖");
        merchant.setSummary(
                "泸州老窖(jiào)是中国最古老的四大名酒之一，“浓香鼻祖，酒中泰斗”。其1573国宝窖池群1996年成为行业首家全国重点文物保护单位，传统酿制技艺2006年又入选首批国家级非物质文化遗产名录，世称“双国宝单位”，旗下产品国窖1573被誉为“活文物酿造”、“中国白酒鉴赏标准级酒品”。");
        merchant.setImg(R.mipmap.img_luzhou);
        datas.add(merchant);

        merchant = new Merchant();
        merchant.setUrl("http://www.ssjx1915.cn");
        merchant.setName("盛世酱香（北京）国际贸易有限公司");
        merchant.setSummary("盛世酱香（北京）国际贸易有限公司，主营以茅台酒全系为主导。公司秉承'顾客第一，勇攀高峰'的经营理念，坚持'诚实守信'的原则为广大客户提供优质的服务");
        merchant.setImg(R.mipmap.img_ss);
        datas.add(merchant);
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
            if (itemPosition == 0) {
                divider = new Y_DividerBuilder()
                        .setRightSideLine(true, Color.TRANSPARENT, 10f, 0f, 0f)
                        .setBottomSideLine(true, Color.TRANSPARENT, 15f, 0f, 0f)
                        .setLeftSideLine(true, Color.TRANSPARENT, 10f, 0f, 0f)
                        .setTopSideLine(true, Color.TRANSPARENT, 15f, 0f, 0f)
                        .create();
            } else {
                divider = new Y_DividerBuilder()
                        .setRightSideLine(true, Color.TRANSPARENT, 10f, 0f, 0f)
                        .setBottomSideLine(true, Color.TRANSPARENT, 15f, 0f, 0f)
                        .setLeftSideLine(true, Color.TRANSPARENT, 10f, 0f, 0f)
                        .create();
            }
            return divider;
        }
    }
}
