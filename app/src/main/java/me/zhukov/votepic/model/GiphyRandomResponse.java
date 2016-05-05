package me.zhukov.votepic.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Michael Zhukov
 */
public class GiphyRandomResponse {

    @SerializedName("data")
    private RandomGif randomGif;

    @SerializedName("meta")
    private Meta meta;

    public GiphyRandomResponse(RandomGif randomGif, Meta meta) {
        this.randomGif = randomGif;
        this.meta = meta;
    }

    public RandomGif getRandomGif() {
        return randomGif;
    }

    public Meta getMeta() {
        return meta;
    }
}
