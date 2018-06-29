package com.ident.validator.ui.merchant;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ident.validator.R;
import com.ident.validator.common.imageloader.ZImageLoader;
import com.ident.validator.data.bean.Merchant;
import com.ident.validator.data.bean.News;

import java.util.List;

/**
 * @author cheny
 * @version 1.0
 * @descr
 * @date 2017/7/11 13:05
 */

public class MerchantAdapter extends BaseQuickAdapter<Merchant, BaseViewHolder> {
    public MerchantAdapter(@Nullable List<Merchant> data) {
        super(R.layout.merchant_list_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Merchant item) {
        helper.setText(R.id.merchant_name, item.name)
                .setText(R.id.merchant_summary, item.summary)
                .setImageResource(R.id.merchant_img, item.img);
    }
}
