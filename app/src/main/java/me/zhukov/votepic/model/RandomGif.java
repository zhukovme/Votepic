package me.zhukov.votepic.model;

import com.google.gson.annotations.SerializedName;

import java.io.BufferedInputStream;
import java.io.InputStream;

import me.zhukov.votepic.api.Fetcher;
import rx.Observable;

/**
 * @author Michael Zhukov
 */
public class RandomGif {

    @SerializedName("type")
    private String type;

    @SerializedName("id")
    private String id;

    @SerializedName("url")
    private String url;

    @SerializedName("image_original_url")
    private String imageOriginalUrl;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("image_mp4_url")
    private String imageMp4Url;

    @SerializedName("image_frames")
    private String imageFrames;

    @SerializedName("image_width")
    private String imageWidth;

    @SerializedName("image_height")
    private String imageHeight;

    @SerializedName("fixed_height_downsampled_url")
    private String fixedHeightDownsampledUrl;

    @SerializedName("fixed_height_downsampled_width")
    private String fixedHeightDownsampledWidth;

    @SerializedName("fixed_height_downsampled_height")
    private String fixedHeightDownsampledHeight;

    @SerializedName("fixed_width_downsampled_url")
    private String fixedWidthDownsampledUrl;

    @SerializedName("fixed_width_downsampled_width")
    private String fixedWidthDownsampledWidth;

    @SerializedName("fixed_width_downsampled_height")
    private String fixedWidthDownsampledHeight;

    @SerializedName("fixed_height_small_url")
    private String fixedHeightSmallUrl;

    @SerializedName("fixed_height_small_still_url")
    private String fixedHeightSmallStillUrl;

    @SerializedName("fixed_height_small_width")
    private String fixedHeightSmallWidth;

    @SerializedName("fixed_height_small_height")
    private String fixedHeightSmallHeight;

    @SerializedName("fixed_width_small_url")
    private String fixedWidthSmallUrl;

    @SerializedName("fixed_width_small_still_url")
    private String fixedWidthSmallStillUrl;

    @SerializedName("fixed_width_small_width")
    private String fixedWidthSmallWidth;

    @SerializedName("fixed_width_small_height")
    private String fixedWidthSmallHeight;

    public RandomGif(String type, String id, String url, String imageOriginalUrl, String imageUrl,
                     String imageMp4Url, String imageFrames, String imageWidth, String imageHeight,
                     String fixedHeightDownsampledUrl, String fixedHeightDownsampledWidth,
                     String fixedHeightDownsampledHeight, String fixedWidthDownsampledUrl,
                     String fixedWidthDownsampledWidth, String fixedWidthDownsampledHeight,
                     String fixedHeightSmallUrl, String fixedHeightSmallStillUrl,
                     String fixedHeightSmallWidth, String fixedHeightSmallHeight,
                     String fixedWidthSmallUrl, String fixedWidthSmallStillUrl,
                     String fixedWidthSmallWidth, String fixedWidthSmallHeight) {

        this.type = type;
        this.id = id;
        this.url = url;
        this.imageOriginalUrl = imageOriginalUrl;
        this.imageUrl = imageUrl;
        this.imageMp4Url = imageMp4Url;
        this.imageFrames = imageFrames;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.fixedHeightDownsampledUrl = fixedHeightDownsampledUrl;
        this.fixedHeightDownsampledWidth = fixedHeightDownsampledWidth;
        this.fixedHeightDownsampledHeight = fixedHeightDownsampledHeight;
        this.fixedWidthDownsampledUrl = fixedWidthDownsampledUrl;
        this.fixedWidthDownsampledWidth = fixedWidthDownsampledWidth;
        this.fixedWidthDownsampledHeight = fixedWidthDownsampledHeight;
        this.fixedHeightSmallUrl = fixedHeightSmallUrl;
        this.fixedHeightSmallStillUrl = fixedHeightSmallStillUrl;
        this.fixedHeightSmallWidth = fixedHeightSmallWidth;
        this.fixedHeightSmallHeight = fixedHeightSmallHeight;
        this.fixedWidthSmallUrl = fixedWidthSmallUrl;
        this.fixedWidthSmallStillUrl = fixedWidthSmallStillUrl;
        this.fixedWidthSmallWidth = fixedWidthSmallWidth;
        this.fixedWidthSmallHeight = fixedWidthSmallHeight;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getImageOriginalUrl() {
        return imageOriginalUrl;
    }

    public Observable<BufferedInputStream> getImageOriginal() {
        return Fetcher.fetchGif(imageOriginalUrl);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Observable<BufferedInputStream> getImage() {
        return Fetcher.fetchGif(imageUrl);
    }

    public String getImageMp4Url() {
        return imageMp4Url;
    }

    public Observable<BufferedInputStream> getImageMp4() {
        return Fetcher.fetchGif(imageMp4Url);
    }

    public String getImageFrames() {
        return imageFrames;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public String getFixedHeightDownsampledUrl() {
        return fixedHeightDownsampledUrl;
    }

    public Observable<BufferedInputStream> getFixedHeightDownsampled() {
        return Fetcher.fetchGif(fixedHeightDownsampledUrl);
    }

    public String getFixedHeightDownsampledWidth() {
        return fixedHeightDownsampledWidth;
    }

    public String getFixedHeightDownsampledHeight() {
        return fixedHeightDownsampledHeight;
    }

    public String getFixedWidthDownsampledUrl() {
        return fixedWidthDownsampledUrl;
    }

    public Observable<BufferedInputStream> getFixedWidthDownsampled() {
        return Fetcher.fetchGif(fixedWidthDownsampledUrl);
    }

    public String getFixedWidthDownsampledWidth() {
        return fixedWidthDownsampledWidth;
    }

    public String getFixedWidthDownsampledHeight() {
        return fixedWidthDownsampledHeight;
    }

    public String getFixedHeightSmallUrl() {
        return fixedHeightSmallUrl;
    }

    public Observable<BufferedInputStream> getFixedHeightSmall() {
        return Fetcher.fetchGif(fixedHeightSmallUrl);
    }

    public String getFixedHeightSmallStillUrl() {
        return fixedHeightSmallStillUrl;
    }

    public Observable<BufferedInputStream> getFixedHeightSmallStill() {
        return Fetcher.fetchGif(fixedHeightSmallStillUrl);
    }

    public String getFixedHeightSmallWidth() {
        return fixedHeightSmallWidth;
    }

    public String getFixedHeightSmallHeight() {
        return fixedHeightSmallHeight;
    }

    public String getFixedWidthSmallUrl() {
        return fixedWidthSmallUrl;
    }

    public Observable<BufferedInputStream> getFixedWidthSmall() {
        return Fetcher.fetchGif(fixedWidthSmallUrl);
    }

    public String getFixedWidthSmallStillUrl() {
        return fixedWidthSmallStillUrl;
    }

    public Observable<BufferedInputStream> getFixedWidthSmallStill() {
        return Fetcher.fetchGif(fixedWidthSmallStillUrl);
    }

    public String getFixedWidthSmallWidth() {
        return fixedWidthSmallWidth;
    }

    public String getFixedWidthSmallHeight() {
        return fixedWidthSmallHeight;
    }
}
