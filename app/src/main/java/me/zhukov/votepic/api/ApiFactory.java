package me.zhukov.votepic.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Michael Zhukov
 */
public class ApiFactory {

    private static final String BASE_URL = "http://api.giphy.com/v1/";

    public static GifService getGifService() {
        return getRetrofit().create(GifService.class);
    }

    public static ApiService getApiService() {
        return getRetrofit().create(ApiService.class);
    }

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
