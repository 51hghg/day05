package com.jy.wanandroid;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColFragment extends Fragment {


    private RecyclerView mRecycler;
    private ArrayList<FoodDbBean> list;
    private ColAdapter colAdapter;
    private DbUtil dbUtil;
    private int index;

    public ColFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_col, container, false);
        initView(root);
        initListener();
        return root;
    }

    private void initListener() {
        colAdapter.setiOnCLick(new ColAdapter.IOnCLick() {
            @Override
            public void click(int pos) {
                index = pos;
                FoodDbBean foodDbBean = list.get(pos);
                Intent intent = new Intent(getActivity(), UpdateActivity.class);
                intent.putExtra("title", foodDbBean.getTitle());
                intent.putExtra("des", foodDbBean.getDes());
                intent.putExtra("pic", foodDbBean.getPic());
                startActivityForResult(intent, 1);
            }
        });

        colAdapter.setiOnLongCLick(new ColAdapter.IOnLongCLick() {
            @Override
            public void click(int pos) {
                dbUtil.delete(list.get(pos));
                list.remove(pos);
                colAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            initData();
        } else {
            if (list != null && list.size() > 0) {
                list.clear();
            }
        }
    }

    private void initData() {
        dbUtil = DbUtil.getDbUtil();
        List<FoodDbBean> foodDbBeans = dbUtil.query();
        list.addAll(foodDbBeans);
        colAdapter.notifyDataSetChanged();
    }

    private void initView(@NonNull final View itemView) {
        mRecycler = (RecyclerView) itemView.findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        colAdapter = new ColAdapter(getActivity(), list);
        mRecycler.setAdapter(colAdapter);
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }

        if (requestCode == 1 && resultCode == 2) {
            String title = data.getStringExtra("title");
            String des = data.getStringExtra("des");

            FoodDbBean foodDbBean = list.get(index);
            foodDbBean.setTitle(title);
            foodDbBean.setDes(des);
            dbUtil.update(foodDbBean);

            colAdapter.notifyDataSetChanged();
        }
    }
}
