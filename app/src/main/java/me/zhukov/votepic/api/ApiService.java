package me.zhukov.votepic.api;

import me.zhukov.votepic.data.GiphyRandomResponse;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author Michael Zhukov
 */
public interface ApiService {

    @GET
    Observable<ResponseBody> getImage(@Url String url);

    @GET("v1/{type}/random")
    Observable<GiphyRandomResponse> getGiphyRandom(@Path("type") String type,
                                                   @Query("api_key") String apiKey,
                                                   @Query("tag") String tag);
}
