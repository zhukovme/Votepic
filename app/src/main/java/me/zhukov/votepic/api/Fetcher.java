package me.zhukov.votepic.api;

import android.content.Context;

import me.zhukov.votepic.R;
import me.zhukov.votepic.data.GiphyRandomResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Michael Zhukov
 */
public class Fetcher {

    private ApiInterface apiInterface = ApiModule.getApiInterface();

    private Context context;

    public Fetcher(Context context) {
        this.context = context;
    }

    public Observable<GiphyRandomResponse> fetchRandomGif() {
        return apiInterface
                .getGiphyRandom("gifs", context.getString(R.string.giphy_public_key), null)
                .subscribeOn(Schedulers.io());
    }
}
