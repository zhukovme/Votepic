package me.zhukov.votepic.api;

import android.content.Context;

import java.io.IOException;

import me.zhukov.votepic.R;
import me.zhukov.votepic.data.GifImage;
import me.zhukov.votepic.data.RandomGif;
import pl.droidsonroids.gif.GifDrawable;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Michael Zhukov
 */
public class Fetcher {

    private static ApiService apiService = ApiFactory.getApiService();

    private static Observable<RandomGif> fetchGiphyRandom(Context context) {
        return apiService
                .getGiphyRandom("gifs", context.getString(R.string.giphy_public_key), null)
                .flatMap(response -> {
                    if (response.getMeta().getStatus() != 200) {
                        throw new Error(response.getMeta().getMessage());
                    }
                    return Observable.just(response.getRandomGif());
                });
    }

    public static Observable<GifImage> fetchImage(Context context) {
        return fetchGiphyRandom(context)
                .flatMap(randomGif -> apiService
                        .getImage(randomGif.getUrl())
                        .map(response -> {
                            int width = Integer.parseInt(randomGif.getWidth());
                            int height = Integer.parseInt(randomGif.getHeight());
                            GifDrawable gifDrawable = null;
                            try {
                                byte[] bytesGif = response.bytes();
                                gifDrawable = new GifDrawable(bytesGif);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return new GifImage(randomGif.getId(), gifDrawable, width, height);
                        }))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
