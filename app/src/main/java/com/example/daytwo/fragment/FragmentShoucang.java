package com.example.daytwo.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.daytwo.R;
import com.example.daytwo.adapter.Adapterrlvtwo;
import com.example.daytwo.api.Myservice;
import com.example.daytwo.bean.Beanone;
import com.example.daytwo.bean.RecentBean;
import com.example.daytwo.bserapp.BaseApp;
import com.example.daytwo.db.RecentBeanDao;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentShoucang extends Fragment {


    private RecyclerView mRlv;
    private ArrayList<RecentBean> list;
    private Adapterrlvtwo adapterrlvtwo;

    public FragmentShoucang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_fragment_shoucang, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mRlv = inflate.findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        getdata();
    }


    public void getdata() {
        list.clear();
        RecentBeanDao recentBeanDao = BaseApp.getInstance().getDaoSession().getRecentBeanDao();
        List<RecentBean> recentBeans = recentBeanDao.loadAll();
        adapterrlvtwo = new Adapterrlvtwo(recentBeans, getContext());
        mRlv.setAdapter(adapterrlvtwo);
        adapterrlvtwo.notifyDataSetChanged();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

    }
}
