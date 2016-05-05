package me.zhukov.votepic.api;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import me.zhukov.votepic.R;
import me.zhukov.votepic.model.GiphyRandomResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Michael Zhukov
 */
public class Fetcher {

    private static ApiService apiService = ApiFactory.getApiService();
    private static GifService gifService = ApiFactory.getGifService();

    public static Observable<GiphyRandomResponse> fetchGiphyRandom(Context context) {
        return apiService
                .getGiphyRandom("gifs", context.getString(R.string.giphy_public_key), null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BufferedInputStream> fetchGif(String url) {
        return gifService
                .getGif(url)
                .flatMap(response -> Observable.just(new BufferedInputStream(response.byteStream())))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
