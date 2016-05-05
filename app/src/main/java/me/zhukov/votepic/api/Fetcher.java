package me.zhukov.votepic.api;

import android.content.Context;

import java.io.BufferedInputStream;

import me.zhukov.votepic.R;
import me.zhukov.votepic.model.GiphyRandomResponse;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author Michael Zhukov
 */
public class Fetcher {

    private ApiService apiService = ApiFactory.getApiService();
    private GifService gifService = ApiFactory.getGifService();

    private static Fetcher instance;

    private Fetcher() {
    }

    public static Fetcher getInstance() {
        if (instance == null) {
            instance = new Fetcher();
        }
        return instance;
    }

    public Observable<GiphyRandomResponse> fetchGiphyRandom(Context context) {
        return apiService
                .getGiphyRandom("gifs", context.getString(R.string.giphy_public_key), null)
                .subscribeOn(Schedulers.io());
    }

    public Observable<BufferedInputStream> fetchGif(String url) {
        return gifService
                .getGif(url)
                .flatMap(responseBody -> {
                    BufferedInputStream bis = new BufferedInputStream(responseBody.byteStream());
                    return Observable.just(bis);
                })
                .subscribeOn(Schedulers.io());
    }
}
