package com.example.daytwo.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daytwo.MainActivity;
import com.example.daytwo.R;
import com.example.daytwo.XaingQingActivity;
import com.example.daytwo.adapter.Adapterrlvone;
import com.example.daytwo.api.Myservice;
import com.example.daytwo.bean.Beanone;
import com.example.daytwo.bean.RecentBean;
import com.example.daytwo.bserapp.BaseApp;
import com.example.daytwo.db.RecentBeanDao;

import java.util.ArrayList;

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
public class FragmentWode extends Fragment {


    private RecyclerView mRlv;
    private ArrayList<RecentBean> list;
    private Adapterrlvone adapterrlvone;

    public FragmentWode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_fragment_wode, container, false);
        initView(inflate);
        initAdd();
        return inflate;
    }

    private void initView(View inflate) {
        mRlv = inflate.findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapterrlvone = new Adapterrlvone(list, getContext());
        mRlv.setAdapter(adapterrlvone);
        initClick();
    }

    private void initClick() {
        adapterrlvone.setOnItemClickListener(new Adapterrlvone.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(getContext(), XaingQingActivity.class);
                startActivity(intent);
            }
        });
        adapterrlvone.setOnItemLongClickListener(new Adapterrlvone.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(final int position) {
                AlertDialog alertDialog1 = new AlertDialog.Builder(getContext())
                        .setMessage("是否插入数据库")//内容
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                RecentBeanDao recentBeanDao = BaseApp.getInstance().getDaoSession().getRecentBeanDao();
                                recentBeanDao.insert(list.get(position));
                            }
                        })
                        .setNegativeButton("取消",null)
                        .create();
                alertDialog1.show();
            }
        });
    }

    private void initAdd() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(Myservice.one)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Myservice myservice = build.create(Myservice.class);
        Observable<Beanone> data = myservice.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Beanone>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Beanone beanone) {
                        list.addAll(beanone.getRecent());
                        adapterrlvone.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
