package me.zhukov.votepic.data;

import pl.droidsonroids.gif.GifDrawable;

/**
 * @author Michael Zhukov
 */
public class GifImage {

    private String id;
    private GifDrawable gifDrawable;
    private int width;
    private int height;

    public GifImage() {
    }

    public GifImage(String id, GifDrawable gifDrawable, int width, int height) {
        this.id = id;
        this.gifDrawable = gifDrawable;
        this.width = width;
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GifDrawable getGifDrawable() {
        return gifDrawable;
    }

    public void setGifDrawable(GifDrawable gifDrawable) {
        this.gifDrawable = gifDrawable;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
