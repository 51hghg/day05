package com.jy.zy3.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.jy.zy3.R;
import com.jy.zy3.base.BaseAdapter;
import com.jy.zy3.model.data.tongpao.RecommendBean;
import com.jy.zy3.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class RecommendAdapter extends BaseAdapter {
    Context context;

    public RecommendAdapter(List mData, Context context) {
        super(mData, context);
        this.context = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.item_recommend;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        RecommendBean.DataBean.PostDetailBean bean = (RecommendBean.DataBean.PostDetailBean) data;
        ImageView imgHeader = (ImageView) vh.getViewByid(R.id.img_header);
        TextView txtName = (TextView) vh.getViewByid(R.id.txt_name);
        TextView time = (TextView) vh.getViewByid(R.id.time);
        TextView txtContent = (TextView) vh.getViewByid(R.id.txt_content);
        TextView txtAll = (TextView) vh.getViewByid(R.id.txt_all);
        NineGridImageView nineGrid = (NineGridImageView) vh.getViewByid(R.id.nineGrid);

        Glide.with(context).load(bean.getHeadUrl()).into(imgHeader);
        txtName.setText(bean.getNickName());
        txtContent.setText(bean.getContent());
        Long time1 = DateUtils.getDateToTime(bean.getCreateTime(), null);
        String date = DateUtils.getStandardDate(time1);
        time.setText(date);

        ArrayList<String> imgs = new ArrayList<>();
        for (RecommendBean.DataBean.PostDetailBean.ImagesBean item : bean.getImages()) {
            imgs.add(item.getFilePath());
        }

        nineGrid.setAdapter(new NineGridImageViewAdapter() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, Object o) {
                Glide.with(context).load(o).into(imageView);
            }

            @Override
            protected void onItemImageClick(Context context, int index, List list) {
                super.onItemImageClick(context, index, list);
            }
        });

        nineGrid.setImagesData(imgs);
    }
}
