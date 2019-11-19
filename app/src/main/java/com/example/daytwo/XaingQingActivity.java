package com.example.daytwo;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.daytwo.api.Myservice;
import com.example.daytwo.bean.Beanone;
import com.example.daytwo.bean.Beantwo;

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

public class XaingQingActivity extends AppCompatActivity {

    private WebView mWv;
    private ArrayList<Beantwo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xaing_qing);
        initAdd();
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mWv = (WebView) findViewById(R.id.wv);
        list = new ArrayList<>();
    }

    private void initAdd() {
        Retrofit build = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Myservice.two)
                .build();
        Myservice myservice = build.create(Myservice.class);
        Observable<Beantwo> dataone = myservice.getDataone();
        dataone.subscribeOn(Schedulers.io());
        dataone.observeOn(AndroidSchedulers.mainThread());
        dataone.subscribe(new Observer<Beantwo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Beantwo beantwo) {
                mWv.loadUrl(beantwo.getUrl());
                mWv.setWebViewClient(new WebViewClient());
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(XaingQingActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onComplete() {

            }
        });
    }
}
