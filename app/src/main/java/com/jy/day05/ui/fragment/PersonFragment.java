package com.jy.day05.ui.fragment;

import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.jy.day05.R;
import com.jy.day05.base.BaseFragment;
import com.jy.day05.interfaces.tongpao.IRecommend;
import com.jy.day05.model.data.tongpao.BannerBean;
import com.jy.day05.model.data.tongpao.HotBean;
import com.jy.day05.model.data.tongpao.PersonBean;
import com.jy.day05.model.data.tongpao.RecommendBean;
import com.jy.day05.model.data.tongpao.TopicBean;
import com.jy.day05.model.data.tongpao.UserBean;
import com.jy.day05.persenter.RecommendPersenter;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends BaseFragment<RecommendPersenter> implements IRecommend.View {

    @BindView(R.id.person)
    TextView person;
    @BindView(R.id.xing)
    TextView xing;
    @BindView(R.id.txt_sex)
    TextView txtSex;
    @BindView(R.id.shengri)
    TextView shengri;
    @BindView(R.id.txt_birth)
    TextView txtBirth;
    @BindView(R.id.xingzuo)
    TextView xingzuo;
    @BindView(R.id.txt_xingzuo)
    TextView txtXingzuo;
    @BindView(R.id.diqu)
    TextView diqu;
    @BindView(R.id.txt_logic)
    TextView txtLogic;
    @BindView(R.id.jineng)
    TextView jineng;
    @BindView(R.id.tv_xin)
    RelativeLayout tvXin;

    @Override
    public int getLatout() {
        return R.layout.fragment_person4;
    }

    @Override
    public void initView() {

    }

    @Override
    public RecommendPersenter createPresenter() {
        return new RecommendPersenter(this);
    }

    @Override
    public void initData() {
        presenter.getPerson();
    }

    @Override
    public void getRecommendReturn(RecommendBean result) {

    }

    @Override
    public void getBannerReturn(BannerBean bannerBean) {

    }

    @Override
    public void getTopisReturn(TopicBean topicBean) {

    }

    @Override
    public void getUserReturn(UserBean userBean) {

    }

    @Override
    public void persenter(PersonBean personBean) {
        final PersonBean.DataBean data = personBean.getData();
        txtBirth.setText(data.getBirthday());
        txtLogic.setText(data.getCity());
        if (data.getSex() == 1 + "") {
            txtSex.setText("男");
        }
        txtXingzuo.setText("射手座");
    }

    @Override
    public void getHot(HotBean hotBean) {

    }
}
