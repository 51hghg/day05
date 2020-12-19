package com.jy.zy.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.zy.R;
import com.jy.zy.base.BaseAdapter;
import com.jy.zy.model.data.tongpao.UserBean;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    Context context;

    public UserAdapter(List mData, Context context) {
        super(mData, context);
        this.context = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_user;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        UserBean.DataBean dataBean = (UserBean.DataBean) data;
        ImageView imgHeader = (ImageView) vh.getViewByid(R.id.img_header);
        ImageView imgHome = (ImageView) vh.getViewByid(R.id.img_home);
        TextView txtName = (TextView) vh.getViewByid(R.id.txt_name);
        TextView txtCity = (TextView) vh.getViewByid(R.id.txt_city);
        txtName.setText(dataBean.getNickName());
        txtCity.setText(dataBean.getCity());
        Glide.with(context).load(dataBean.getHeadUrl()).into(imgHeader);
        Glide.with(context).load(dataBean.getFileBeanList().get(0).getFilePath()).into(imgHome);
    }
}
