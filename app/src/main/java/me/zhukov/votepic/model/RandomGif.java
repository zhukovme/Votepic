package me.zhukov.votepic.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Michael Zhukov
 */
public class RandomGif {

    @SerializedName("id")
    private String id;

    @SerializedName("image_url")
    private String url;

    @SerializedName("image_width")
    private String width;

    @SerializedName("image_height")
    private String height;

    public RandomGif(String id, String url, String width, String height) {
        this.id = id;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }
}
