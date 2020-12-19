package com.jy.wanandroid;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private RecyclerView mRecycler;
    private HomeAdapter homeAdapter;
    private int page = 1;
    private ArrayList<FoodBean.DataBean> list;
    private static final String TAG = "HomeFragment";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initView(root);
//        initData();
        initDataRx();
        initListener();
        return root;
    }

    private void initDataRx() {
        new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getDataRx("1", "20", 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FoodBean foodBean) {
                        List<FoodBean.DataBean> data = foodBean.getData();
                        list.addAll(data);
                        homeAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "错误原因:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initListener() {
        homeAdapter.setiOnCLick(new HomeAdapter.IOnCLick() {
            @Override
            public void click(int pos) {
                DbUtil dbUtil = DbUtil.getDbUtil();
                FoodBean.DataBean dataBean = list.get(pos);
                FoodDbBean foodDbBean = new FoodDbBean();
                foodDbBean.setPic(dataBean.getPic());
                foodDbBean.setDes(dataBean.getFood_str());
                foodDbBean.setTitle(dataBean.getTitle());
                long insert = dbUtil.insert(foodDbBean);
                if (insert > 0) {
                    Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "收藏失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.baseUrl)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<FoodBean> call = apiService.get("1", "20", page);

        call.enqueue(new Callback<FoodBean>() {
            @Override
            public void onResponse(Call<FoodBean> call, Response<FoodBean> response) {
                FoodBean foodBean = response.body();
                List<FoodBean.DataBean> data = foodBean.getData();
                list.addAll(data);
                homeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FoodBean> call, Throwable t) {

            }
        });
    }

    private void initView(@NonNull final View itemView) {
        list = new ArrayList<>();
        mRecycler = (RecyclerView) itemView.findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapter = new HomeAdapter(getActivity(), list);
        mRecycler.setAdapter(homeAdapter);
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

}
