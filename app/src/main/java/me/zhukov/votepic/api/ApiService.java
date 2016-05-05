package me.zhukov.votepic.api;

import me.zhukov.votepic.model.GiphyRandomResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Michael Zhukov
 */
public interface ApiService {

    @GET("{type}/random")
    Observable<GiphyRandomResponse> getGiphyRandom(@Path("type") String type,
                                                   @Query("api_key") String apiKey,
                                                   @Query("tag") String tag);
}