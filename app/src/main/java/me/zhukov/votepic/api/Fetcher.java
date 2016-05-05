package me.zhukov.votepic.api;

import android.content.Context;
import android.graphics.Movie;

import me.zhukov.votepic.R;
import me.zhukov.votepic.model.MovieGif;
import me.zhukov.votepic.model.RandomGif;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Michael Zhukov
 */
public class Fetcher {

    private static ApiService apiService = ApiFactory.getApiService();
    private static GifService gifService = ApiFactory.getGifService();

    public static Observable<RandomGif> fetchGiphyRandom(Context context) {
        return apiService
                .getGiphyRandom("gifs", context.getString(R.string.giphy_public_key), null)
                .flatMap(giphyRandomResponse -> Observable.just(giphyRandomResponse.getRandomGif()))
                .subscribeOn(Schedulers.io());
    }

    public static Observable<MovieGif> fetchGifOriginal(Context context) {
        return fetchGiphyRandom(context)
                .flatMap(randomGif -> gifService
                        .getGif(randomGif.getImageUrl())
                        .map(response -> {
                            int width = Integer.parseInt(randomGif.getImageWidth());
                            int height = Integer.parseInt(randomGif.getImageHeight());
                            return new MovieGif(Movie.decodeStream(response.byteStream()),
                                    randomGif.getId(), width, height);
                        })
                        .subscribeOn(Schedulers.io())
                )
                .observeOn(AndroidSchedulers.mainThread());
    }
}
