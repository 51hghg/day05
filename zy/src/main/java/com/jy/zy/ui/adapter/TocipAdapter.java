package com.jy.zy.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.zy.R;
import com.jy.zy.base.BaseAdapter;
import com.jy.zy.model.data.tongpao.RecommendBean;
import com.jy.zy.model.data.tongpao.TopicBean;

import java.util.List;

import butterknife.BindView;

public class TocipAdapter extends BaseAdapter {
    Context context;

    public TocipAdapter(List mData, Context context) {
        super(mData, context);
        this.context = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.item_recommend;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TopicBean.DataBean dataBean = (TopicBean.DataBean) data;
        TextView txtTitle = (TextView) vh.getViewByid(R.id.txt_title);
        ImageView imgIcon = (ImageView) vh.getViewByid(R.id.img_icon);
        txtTitle.setText(dataBean.getName());
        Glide.with(context).load(dataBean.getImageUrl()).into(imgIcon);
    }
}
