package me.zhukov.votepic.data;

import com.google.gson.annotations.SerializedName;

/**
 * @author Michael Zhukov
 */
public class GiphyResponse {

    @SerializedName("data")
    private Gif gif;

    @SerializedName("meta")
    private Meta meta;

    public GiphyResponse(Gif gif, Meta meta) {
        this.gif = gif;
        this.meta = meta;
    }

    public Gif getGif() {
        return gif;
    }

    public Meta getMeta() {
        return meta;
    }
}
