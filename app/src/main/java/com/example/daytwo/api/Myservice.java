package com.example.daytwo.api;

import com.example.daytwo.bean.Beanone;
import com.example.daytwo.bean.Beantwo;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Myservice {
    String one ="https://news-at.zhihu.com/";
    String two="http://news-at.zhihu.com/";
    @GET("api/4/news/hot")
    Observable<Beanone> getData();

    @GET("api/2/news/9717396")
    Observable<Beantwo> getDataone();
}
