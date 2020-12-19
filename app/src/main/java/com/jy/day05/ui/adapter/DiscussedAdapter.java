package com.jy.day05.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.day05.R;
import com.jy.day05.base.BaseAdapter;
import com.jy.day05.model.data.tongpao.TopicBean;
import com.jy.day05.utils.TxtUtils;

import java.util.List;

public class DiscussedAdapter extends BaseAdapter {
    Context context;
    public DiscussedAdapter(List mData, Context context) {
        super(mData, context);
        this.context=context;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_dicussed_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TopicBean.DataBean dataBean= (TopicBean.DataBean) data;
        final ImageView imgIcon = (ImageView) vh.getViewByid(R.id.img_icon);
        final  TextView txtTitle = (TextView) vh.getViewByid(R.id.txt_title);

        TxtUtils.setTextView(txtTitle,dataBean.getName());
        Glide.with(context).load(dataBean.getImageUrl()).into(imgIcon);
    }
}
