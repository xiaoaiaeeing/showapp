package com.ident.validator.ui.news;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ident.validator.R;
import com.ident.validator.common.imageloader.ZImageLoader;
import com.ident.validator.data.bean.News;

import java.util.List;

/**
 * @author cheny
 * @version 1.0
 * @descr
 * @date 2017/7/11 13:05
 */

public class NewsAdapter extends BaseQuickAdapter<News, BaseViewHolder> {
    public NewsAdapter(@Nullable List<News> data) {
        super(R.layout.news_list_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, News item) {
        helper.setText(R.id.news_title, item.title)
                .setText(R.id.news_summary, item.summary);
        ImageView newsImage = helper.getView(R.id.news_img);
        ZImageLoader.loadFromUrl(newsImage.getContext(), item.iconUrl, newsImage);
    }
}
