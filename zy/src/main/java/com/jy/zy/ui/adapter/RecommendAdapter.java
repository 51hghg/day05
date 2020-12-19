package com.jy.zy.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.jy.zy.R;
import com.jy.zy.base.BaseAdapter;
import com.jy.zy.model.data.tongpao.RecommendBean;
import com.jy.zy.utils.DateUtils;
import com.jy.zy.utils.TxtUtils;

import java.util.ArrayList;
import java.util.List;

public class RecommendAdapter extends BaseAdapter {
    Context context;
    private boolean isShowDes;
    private int next;

    public RecommendAdapter(List mData, Context context) {
        super(mData, context);
        this.context = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_recommend;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        RecommendBean.DataBean.PostDetailBean postDetailBean = (RecommendBean.DataBean.PostDetailBean) data;
        ImageView imgHeader = (ImageView) vh.getViewByid(R.id.img_header);
        TextView txtName = (TextView) vh.getViewByid(R.id.txt_name);
        TextView txtContent = (TextView) vh.getViewByid(R.id.txt_content);
        TextView timg = (TextView) vh.getViewByid(R.id.time);
        TextView txtAll = (TextView) vh.getViewByid(R.id.txt_all);
        NineGridImageView nineGrid = (NineGridImageView) vh.getViewByid(R.id.nineGrid);

        txtContent.setText(postDetailBean.getContent());

        String msg = txtContent.getText().toString();
        int start = msg.indexOf("#");
        for (int i = start + 1; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            if (ch == '#') {
                next = i;
                break;
            }
        }
        int end = msg.lastIndexOf("#");
        SpannableString spannableString = new SpannableString(msg);
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), start, next + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        txtContent.setText(spannableString);

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

        Long time = DateUtils.getDateToTime(postDetailBean.getCreateTime(), null);
        String date = DateUtils.getStandardDate(time);
        TxtUtils.setTextView(timg, date);

        RequestOptions requestOptions = new RequestOptions();
        RoundedCorners roundedCorners = new RoundedCorners(8);
        requestOptions.transform(roundedCorners);
//        requestOptions.circleCrop();
        Glide.with(context).load(postDetailBean.getHeadUrl()).apply(requestOptions).into(imgHeader);

        TxtUtils.setTextView(txtName, postDetailBean.getNickName());

        ArrayList<String> imgs = new ArrayList<>();
        for (RecommendBean.DataBean.PostDetailBean.ImagesBean item : postDetailBean.getImages()) {
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
                Intent intent = new Intent(context, BigImageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("postion", index);
                bundle.putStringArrayList("urls", imgs);
                intent.putExtra("data", bundle);
                context.startActivity(intent);
            }
        });

        nineGrid.setImagesData(imgs);
    }
}
