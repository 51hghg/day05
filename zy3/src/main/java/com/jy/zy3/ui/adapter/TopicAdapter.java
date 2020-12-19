package com.jy.zy3.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.jy.zy3.R;
import com.jy.zy3.base.BaseAdapter;
import com.jy.zy3.model.data.tongpao.TopicBean;

import java.util.List;

public class TopicAdapter extends BaseAdapter {
    Context context;

    public TopicAdapter(List mData, Context context) {
        super(mData, context);
        this.context = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.topic_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TopicBean.DataBean dataBean = (TopicBean.DataBean) data;
        ImageView imgIcon = (ImageView) vh.getViewByid(R.id.img_icon);
        TextView txtTitle = (TextView) vh.getViewByid(R.id.txt_title);
        Glide.with(context).load(dataBean.getImageUrl()).into(imgIcon);
        txtTitle.setText(dataBean.getName());
    }
}
