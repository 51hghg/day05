package com.jy.day05.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.jy.day05.BigActivity;
import com.jy.day05.Main3Activity;
import com.jy.day05.R;
import com.jy.day05.base.BaseAdapter;
import com.jy.day05.model.data.tongpao.RecommendBean;
import com.jy.day05.utils.DateUtils;
import com.jy.day05.utils.ImageLoader;
import com.jy.day05.utils.TxtUtils;

import java.util.ArrayList;
import java.util.List;

public class RecommondAdapter extends BaseAdapter {
    Context context;
    private boolean isShowDes;
    private int next;

    public RecommondAdapter(List mData, Context context) {
        super(mData, context);
        this.context = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_commond_item;
    }

    @Override
    protected void bindData(Object data, com.jy.day05.base.BaseAdapter.VH vh) {
        RecommendBean.DataBean.PostDetailBean postDetailBean = (RecommendBean.DataBean.PostDetailBean) data;

        final ImageView imgHeader = (ImageView) vh.getViewByid(R.id.img_header);
        final TextView txtContent = (TextView) vh.getViewByid(R.id.txt_content);
        final TextView txtName = (TextView) vh.getViewByid(R.id.txt_name);
        final TextView txtLove = (TextView) vh.getViewByid(R.id.img_love);
        NineGridImageView nenegrid = (NineGridImageView) vh.getViewByid(R.id.nineGrid);
        TextView time = (TextView) vh.getViewByid(R.id.time);
        TextView txtAll = (TextView) vh.getViewByid(R.id.txt_all);

        final String string = txtAll.getText().toString();
        if (string.equals("全文")) {
            isShowDes = true;
        }
        txtAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShowDes) {
                    //收起
                    txtContent.setEllipsize(TextUtils.TruncateAt.END);
                    txtContent.setLines(10);
                    txtAll.setText("收起");
                } else {
                    //展开
                    txtContent.setEllipsize(null);
                    //这个方法是必须设置的，否则无法展开
                    txtContent.setLines(2);
                    txtAll.setText("全文");
                }
                isShowDes = !isShowDes;
            }
        });

        txtContent.setText(postDetailBean.getContent());
        txtName.setText(postDetailBean.getNickName());

        Long time1 = DateUtils.getDateToTime(postDetailBean.getCreateTime(), null);
        String data1 = DateUtils.getStandardDate(time1);
        TxtUtils.setTextView(time, data1);

        String msg = txtContent.getText().toString();
        int start = msg.indexOf("#");

        for (int i = start+1; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            if (ch=='#'){
                next=i;
                break;
            }
        }
        int end = msg.lastIndexOf("#");
        SpannableString spannableString = new SpannableString(msg);
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), start, next+1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        txtContent.setText(spannableString);

        final RequestOptions requestOptions = new RequestOptions();
        requestOptions.circleCrop();
        Glide.with(context).load(postDetailBean.getHeadUrl()).apply(requestOptions).into(imgHeader);

        ArrayList<String> imgs = new ArrayList<>();

        for (RecommendBean.DataBean.PostDetailBean.ImagesBean item : postDetailBean.getImages()) {
            imgs.add(item.getFilePath());
        }

        nenegrid.setAdapter(new NineGridImageViewAdapter() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, Object o) {
//                ImageLoader.loadImage((String) o,imageView);
                Glide.with(context).load(o).into(imageView);

            }

            @Override
            protected void onItemImageClick(Context context, int index, List list) {
                //点击查看大图
                super.onItemImageClick(context, index, list);
                Intent intent = new Intent(context, BigActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("postion", index);
                bundle.putStringArrayList("urls", imgs);
                intent.putExtra("data", bundle);
                context.startActivity(intent);
            }
        });

        nenegrid.setImagesData(imgs);

        imgHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                Intent intent = new Intent(context, Main3Activity.class);
//                intent.putExtra("nenegrid", (Parcelable) nenegrid);
//                intent.putInt("img", imgHeader);
//                intent.putExtra("time", (Parcelable) time);
                intent.putExtra("name", name);
                intent.putExtra("content", txtContent.getText().toString());
                intent.putExtra("all", txtAll.getText().toString());
                intent.putExtra("love", txtLove.getText().toString());
                context.startActivity(intent);
            }
        });

        txtLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtLove.setText("已关注");
            }
        });
    }
}
