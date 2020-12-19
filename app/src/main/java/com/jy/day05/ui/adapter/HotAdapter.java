package com.jy.day05.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.day05.R;
import com.jy.day05.base.BaseAdapter;
import com.jy.day05.base.BaseFragment;
import com.jy.day05.model.data.tongpao.HotBean;

import java.util.List;

public class HotAdapter extends BaseAdapter {
    Context context;

    public HotAdapter(List mData, Context context) {
        super(mData, context);
        this.context = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.item_hot;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HotBean.DataBean dataBean = (HotBean.DataBean) data;
        ImageView img = (ImageView) vh.getViewByid(R.id.img);
        TextView tvTitle = (TextView) vh.getViewByid(R.id.tv_title);
        Glide.with(context).load(dataBean.getCover()).into(img);
        tvTitle.setText(dataBean.getTitle());
    }
}
