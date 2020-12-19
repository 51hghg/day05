package com.jy.day05.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.day05.R;
import com.jy.day05.base.BaseAdapter;
import com.jy.day05.model.data.tongpao.UserBean;
import com.jy.day05.utils.TxtUtils;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    Context context;
    public UserAdapter(List mData, Context context) {
        super(mData, context);
        this.context=context;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_user_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        UserBean.DataBean dataBean= (UserBean.DataBean) data;
        final ImageView imgHome = (ImageView) vh.getViewByid(R.id.img_home);
        final ImageView imgHeader = (ImageView) vh.getViewByid(R.id.img_header);
        final TextView txtName= (TextView) vh.getViewByid(R.id.txt_name);
        final TextView txtCity= (TextView) vh.getViewByid(R.id.txt_city);
        final TextView txtLove= (TextView) vh.getViewByid(R.id.img_love);
        TxtUtils.setTextView(txtCity,dataBean.getCity());
        TxtUtils.setTextView(txtName,dataBean.getNickName());
        Glide.with(context).load(dataBean.getHeadUrl()).into(imgHeader);
        Glide.with(context).load(dataBean.getFileBeanList().get(0).getFilePath()).into(imgHome);
        txtLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtLove.setText("已关注");
            }
        });
    }
}
