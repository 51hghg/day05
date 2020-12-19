package com.jy.wanandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ColAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<FoodDbBean> list;

    public ColAdapter(Context context, ArrayList<FoodDbBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_text, parent, false);
        return new TextViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        TextViewHolder textViewHolder = (TextViewHolder) holder;
        textViewHolder.tv_title.setText(list.get(position).getTitle());
        textViewHolder.tv_type.setText(list.get(position).getDes());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.circleCrop();
        Glide.with(context).load(list.get(position).getPic()).apply(requestOptions).into(textViewHolder.iv_text);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iOnCLick!=null){
                    iOnCLick.click(position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (iOnLongCLick!=null){
                    iOnLongCLick.click(position);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class TextViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_text;
        public TextView tv_title;
        public TextView tv_type;
        public TextViewHolder(View root) {
            super(root);
            iv_text = root.findViewById(R.id.iv_text);
            tv_title = root.findViewById(R.id.tv_title);
            tv_type = root.findViewById(R.id.tv_type);
        }
    }

    public interface IOnCLick{
        void click(int pos);
    }

    IOnCLick iOnCLick;

    public void setiOnCLick(IOnCLick iOnCLick) {
        this.iOnCLick = iOnCLick;
    }

    public interface IOnLongCLick{
        void click(int pos);
    }

    IOnLongCLick iOnLongCLick;

    public void setiOnLongCLick(IOnLongCLick iOnLongCLick) {
        this.iOnLongCLick = iOnLongCLick;
    }
}
