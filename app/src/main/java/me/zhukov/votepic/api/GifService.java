package me.zhukov.votepic.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author Michael Zhukov
 */
public interface GifService {

    @GET
    Observable<ResponseBody> getGif(@Url String url);
}
