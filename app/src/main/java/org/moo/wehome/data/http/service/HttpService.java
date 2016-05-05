package org.moo.wehome.data.http.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by moo on 16/4/16.
 */
public class HttpService {
//    private static final String DOMAIN = "http://rap.taobao.org/mockjsdata/2798/";
    private static final String DOMAIN = "http://192.168.199.225:8000/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    public static <T> T getService(Class<T> clazz) {
        return retrofit.create(clazz);
    }

}
